package com.globati.service;

import com.globati.dbmodel.*;
import com.globati.enums.GlobatiPaymentStatus;
import com.globati.enums.Verified;
import com.globati.s3.HotelBookingRow;
import com.globati.service.exceptions.ServiceException;
import com.globati.service_beans.guest.EmployeeAndItems;
import com.globati.utildb.DateTools;
import com.globati.utildb.ImageHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * Created by daniel on 1/19/17.
 *
 *
 * Format for the csv file to send the masspayment
 *
 *
 */

@Service
public class PayService {

    @Autowired
    DealService dealService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeInfoService employeeInfoService;

    @Autowired
    HotelBookingService hotelBookingService;

    @Autowired
    FlightBookingService flightBookingService;

    private static final Logger log = LogManager.getLogger(PayService.class);


    /**
     * 12/20/17
     *
     * @throws ServiceException
     */
    @Scheduled(cron = "0 16 17 * * ?")
    public void uploadVerifiedUsersToS3() throws ServiceException {
        log.info("** Globati batch pay initializing **");
        List<File> files = createCSVFileOfVerifiedUsersForBookings();
        try{
            File massPay = files.get(0);
            File flightBookings = files.get(1);
            File hotelBookings = files.get(2);

            ImageHandler.uploadVerifiedUsersToS3(massPay, System.currentTimeMillis()+"masspay.csv");
            ImageHandler.uploadVerifiedUsersToS3(flightBookings, System.currentTimeMillis()+"flights.csv");
            ImageHandler.uploadVerifiedUsersToS3(hotelBookings, System.currentTimeMillis()+"hotels.csv");

        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException("Could not upload the verified users csv file to S3", e);
        }
    }

    @Scheduled(cron = "0 37 8 * * ?")
    public void processPayments(){
        log.info("** Initializing globati processed-payments handler **");
        List<File> files = ImageHandler.getProcessedPaymentFile();

        BufferedReader br = null;
        FileReader fr = null;

        try {
            for(File file: files){
                //br = new BufferedReader(new FileReader(FILENAME));
                fr = new FileReader(file);
                br = new BufferedReader(fr);

                String sCurrentLine = br.readLine();
                String[] lineParts = sCurrentLine.split(",");
                String bookingType = lineParts[0];

                while((sCurrentLine = br.readLine()) != null) {
                    String[] bookingRow = sCurrentLine.split(",");
                    log.info("lines");
                    log.info(sCurrentLine);
                    log.info("array");
                    log.info(bookingRow);
                    log.info("bookingType");
                    log.info(bookingType);
                    String bookingId = bookingRow[0];
                    String employeeId = bookingRow[1];
                    String paidStatus = bookingRow[4].toUpperCase();
                    if(bookingType.equals("HotelBookingId")){
                        HotelBooking booking = hotelBookingService.getHotelBookingById(Long.parseLong(bookingId));
                        booking.setGlobatiPaymentStatus(GlobatiPaymentStatus.valueOf(paidStatus));
                        hotelBookingService.updateHotelBooking(booking);
                    }
                    else if(bookingType.equals("FlightBookingId")){
                        FlightBooking booking = flightBookingService.getFlightBookingById(Long.parseLong(bookingId));
                        booking.setGlobatiPaymentStatus(GlobatiPaymentStatus.valueOf(paidStatus));
                        flightBookingService.updateFlightBooking(booking);
                    }
                    else{
                        throw new ServiceException("There was an error parsing a process payments file.");
                    }

                }

            }
        } catch (ServiceException e1) {
            log.warn("** Globati service exception in PayService: processPayments()");
            e1.printStackTrace();
        } catch (FileNotFoundException e1) {
            log.warn("** Globati service exception in PayService: processPayments()");
            e1.printStackTrace();
        } catch (IOException e1) {
            log.warn("** Globati service exception in PayService: processPayments()");
            e1.printStackTrace();
        }
        finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     *
     * If a user has been verified, then they are added to the list.
     * A csv file is created in the same way that is is in the above functions
     * with the same format. The comission is retrieved by calling
     * calculateSumOfFlightBookingsForEmployee(List<FlightBooking> bookings)
     *
     *
     * @return
     */
    public List<File> createCSVFileOfVerifiedUsersForBookings() throws ServiceException {

        List<File> files = new ArrayList<>();

        //Get all employee infos that are verified
        List<EmployeeInfo> employeeInfos = employeeInfoService.getAllEmployeesByVerified(Verified.STANDARD);

        List<EmployeeAndItems> employees = new ArrayList<>();
        //Get all employees for their employeeInfos
        employeeInfos.forEach((info)-> {
            try {
                EmployeeAndItems employee = employeeService.getItemsForEmployee(employeeService.getEmployeeById(info.getEmployeeId()).getGlobatiUsername());
                if(employee.getEmployee().getFlights().size() > 0 || employee.getEmployee().getHotels().size() > 0){
                    employees.add(employee);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        });


        log.trace("Employees size: "+employees.size());

        File massPay = new File("massPayment.csv");
        File flightBookings =  new File("flightbookings.csv");
        File hotelBookings = new File("hotelbookings.csv");


        try(BufferedWriter bw = new BufferedWriter(new FileWriter(massPay))) {
            String header = "PayPal email,Payment,currency,id";
            bw.write(header);
            bw.newLine();
            for (EmployeeAndItems employee : employees) {

                String line = employee.getEmployee().getPaypalEmail() +
                        ","
                        +
                        calculateSumOfFlightBookingsForEmployee(employee.getEmployee().getFlights()) + calculateSumOfHoteltBookingsForEmployee(employee.getEmployee().getHotels())
                        +
                        "," + "EUR" +
                        "," + employee.getEmployee().getId();
                bw.write(line);
                bw.newLine();
            }
            files.add(massPay);
        } catch (IOException e) {
            log.warn("There was a problem when createing a mass pay file:: in PayService.createCSVFileOfVerifiedUsersForBookings()");
            e.printStackTrace();
        }

        try(BufferedWriter flightsWriter = new BufferedWriter(new FileWriter(flightBookings))){
            String header = "FlightBookingId,EmployeeId,Price,EmployeeComission";
            flightsWriter.write(header);
            flightsWriter.newLine();
            for (EmployeeAndItems employee : employees) {
                for(FlightBooking flightBooking: employee.getEmployee().getFlights()){
                    if(flightBooking.getGlobatiPaymentStatus().equals(GlobatiPaymentStatus.NOT_PAID)){
                        String line = flightBooking.getId()+","+employee.getEmployee().getId()+","
                                +flightBooking.getCostOfTicket()+","+flightBooking.getEmployeeComission();
                        flightsWriter.write(line);
                        flightsWriter.newLine();
                    }
                }
            }
            files.add(flightBookings);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter bookingsWriter = new BufferedWriter(new FileWriter(hotelBookings))){
            String header = "HotelBookingId,EmployeeId,Price,EmployeeComission";
            bookingsWriter.write(header);
            bookingsWriter.newLine();
            for(EmployeeAndItems employee: employees){
                for( HotelBooking hotel: employee.getEmployee().getHotels() ){
                    String line = hotel.getId()+","+employee.getEmployee().getId()+","
                            +hotel.getCostOfTicket()+","+hotel.getEmployeeComission();
                    bookingsWriter.write(line);
                    bookingsWriter.newLine();
                }
            }
            files.add(hotelBookings);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }


    /**
     * Calculates the sum of globati comission of all flight bookings in the list.
     *
     * This is not done yet. It needs to take List<HotelBooking> as well.
     *
     * @return
     */
    public Double calculateSumOfFlightBookingsForEmployee(List<FlightBooking> bookings) throws ServiceException {
        try{
            Double amount = new Double(0);
            for(FlightBooking booking: bookings){
                amount += booking.getEmployeeComission();
            }
            return amount;
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException("Could not calculate the globati comission for list of flight bookings. ");
        }
    }

    public Double calculateSumOfHoteltBookingsForEmployee(List<HotelBooking> bookings) throws ServiceException {
        try{
            Double amount = new Double(0);
            for(HotelBooking booking: bookings){
                amount += booking.getEmployeeComission();
            }
            return amount;
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException("Could not calculate the globati comission for list of flight bookings. ");
        }
    }


}
