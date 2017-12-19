package com.globati.service;

import com.globati.dbmodel.Deal;
import com.globati.dbmodel.Employee;
import com.globati.dbmodel.EmployeeInfo;
import com.globati.dbmodel.FlightBooking;
import com.globati.enums.Verified;
import com.globati.service.exceptions.ServiceException;
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

    private static final Logger log = LogManager.getLogger(PayService.class);

    /**
     * Returns a List<> of all Receptionists(Employees) and their recruitments/deals that were created
     * for that mont
     * @return
     */
//    public List<Employee> getAllreceptionistsAndDealsForMonth() throws ServiceException {
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.MONTH, -1);
//
//        Integer month = c.get(Calendar.MONTH)+1;
//        Integer year = c.get(Calendar.YEAR);
//
//        List<Employee> employees = new ArrayList<>();
//
//        Iterable<Employee> employeeIterable = employeeService.getAllEmployees();
//
//        Iterator itr = employeeIterable.iterator();
//
//        while( itr.hasNext() ){
//            Employee employee = (Employee) itr.next();
//            employee.setDeals(dealService.getDealsCreatedByMonth(month, year, employee.getId()));
//            employees.add(employee);
//        }
//        return employees;
//    }

    /**
     * Returns a list of 2 csv files, 1 for recepionists who have a paypal account and 1 for receptionists who do not have paypal account.
     *
     *     Recipient ID 	Payment 	Currency 	Customer ID 	Note
     *   mbrown@myco.com 	10.00 	      USD            ID001 	   Thanks for your business!
     *
     *
     * @return
     */

//    public List<File> createCSVFileOfPayments() throws ServiceException, IOException {
//
//        Calendar c = Calendar.getInstance();
//        c.setTime(new Date());
//        c.add(Calendar.MONTH, -1);
//
//        List<Employee> employees = getAllreceptionistsAndDealsForMonth();
//        List<Employee> employeesWithNoPaypal = new ArrayList<>();
//        List<File> files = new ArrayList<>();
//
//        File file = new File("receptionists.csv");
//        File file2;
//
//        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
//            String header = "Recipient ID,"+"Payment,"+"Currency,"+"Year"+ c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR);
//            bw.write(header);
//            bw.newLine();
//            for (Employee employee : employees) {
//                Double amountToPay = 0.0;
//                Integer numberOfDeals = 0;
//                for (Deal deal : employee.getDeals()) {
//                    amountToPay += deal.getCost();
//                    numberOfDeals++;
//                }
//                if(employee.getPaypalEmail()!=null) {
//                    String line = employee.getPaypalEmail() + "," + amountToPay + "," + "EUR";
//                    bw.write(line);
//                    bw.newLine();
//                }
//                else{
//                    employeesWithNoPaypal.add(employee);
//                }
//            }
//            file2 = createCSVFileOfRecruitmentsWithNoPayPal(employeesWithNoPaypal);
//        }
//        files.add(file);
//        files.add(file2);
//        return files;
//    }

    /**
     * Returns a csv file of receptionists who have made recruitments, but do not have a paypal account.
     * @param employeesWithNoPaypal
     * @return
     * @throws IOException
     */

    private File createCSVFileOfRecruitmentsWithNoPayPal(List<Employee> employeesWithNoPaypal) throws IOException {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);

        File file = new File("nopaypal.csv");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            String header = "Recipient ID,"+"Payment,"+"Currency,"+"Customer ID,"+"Number of recruitments for month "+ c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR);
            bw.write(header);
            bw.newLine();
            for (Employee employee : employeesWithNoPaypal) {
                Double amountToPay = 0.0;
                Integer numberOfDeals = 0;
                for (Deal deal : employee.getDeals()) {
                    amountToPay += deal.getCost();
                    numberOfDeals++;
                }
                String line = employee.getPaypalEmail() + "," + amountToPay + "," + "EUR" + "," + employee.getId() + ","+ numberOfDeals;
                bw.write(line);
                bw.newLine();
            }
        }
        return file;
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
    public File createCSVFileOfVerifiedUsersForBookings() throws ServiceException {

        List<EmployeeInfo> employeeInfos = employeeInfoService.getAllEmployeesByVerified(Verified.STANDARD);

        File file = new File("verifiedUsers.csv");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            String header = "PayPal email,Payment (Fill this in manually in excel),currency,id,Verified";
            bw.write(header);
            bw.newLine();
            for (EmployeeInfo info : employeeInfos) {
                Employee employee1 = employeeService.getEmployeeById(info.getEmployeeId());
                String line = employee1.getPaypalEmail() + ","+calculateSumOfFlightBookingsForEmployee(employee1.getFlights())
                        +"," + "EUR" + "," + employee1.getId() + ","+info.get_verified();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
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
                amount += booking.getGlobatiCommission();
            }
            return amount;
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException("Could not calculate the globati comission for list of flight bookings. ");
        }
    }


    @Scheduled(cron = "0 40 4 * * ?")
    public void uploadVerifiedUsersToS3() throws ServiceException {
        try{
            ImageHandler.uploadVerifiedUsersToS3(createCSVFileOfVerifiedUsersForBookings());
        }catch(Exception e){
            e.printStackTrace();
            throw new ServiceException("Could not upload the verified users csv file to S3", e);
        }
    }

}
