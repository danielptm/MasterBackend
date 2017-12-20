package com.globati.utildb;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.globati.s3.FlightBookingRow;
import com.globati.s3.HotelBookingRow;
import com.globati.service.PropertiesService;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * TODO: 12/20/17 DRY principle. Combine these functions to reduce repeated code.
 * TODO: 12/20/17 Rename this class to S3Handler.
 * TODO: 12/20/17 Make this file have its own tests. These should unit tests which work against S3, but the entire class can be ignored.
 */
public class ImageHandler {

    @Autowired
    static PropertiesService propertiesService;

    //Value assigned during method execution
    private static String lastCreatedPath=null;

//    private static final String activePath= propertiesService.getImageBucket();

    private static final Logger log = LogManager.getLogger(ImageHandler.class);

    public static String getMostRecentImagePath(){
        return lastCreatedPath;
    }

    private static String getUUID(){
        return UUID.randomUUID().toString();
    }


    /**
     * This function used to have more functinoality, now it looks like it just sends the inputstream to getFileFromImage;
     * @param is
     * @return
     * @throws GlobatiUtilException
     * @throws IOException
     */
    public static String createNewImage(InputStream is) throws GlobatiUtilException, IOException {
        StringBuilder sb = new StringBuilder();
        String uid = getUUID();
        sb.append(uid);
        sb.append("image.jpg");
        lastCreatedPath = sb.toString();
        uploadFileToS3(is, lastCreatedPath);
        return lastCreatedPath;
    }


    /**
     * Method used by uploadFileToS3()
     * @param is
     * @return
     * @throws IOException
     */
    private static File getFileFromImage(InputStream is) throws IOException {
        String filename = "filetowrite.jpg";
        File file = new File(filename);
                OutputStream outputStream = new FileOutputStream(file);
        IOUtils.copy(is, outputStream);
        outputStream.close();

        return file;
    }

    public static void uploadFileToS3(InputStream is, String fileName) throws IOException {
        String bucketName = propertiesService.getImageBucket();

        File file = getFileFromImage(is);

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJSYT5343PVMDHCRQ",
                "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM");

        // create a client connection based on credentials
        AmazonS3 s3client = new AmazonS3Client(credentials);

        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));

    }


    public static void deleteFileFromS3(String keyName){
        String bucketName = propertiesService.getImageBucket();

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJSYT5343PVMDHCRQ",
                "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM");

        AmazonS3 s3Client = new AmazonS3Client(credentials);

        s3Client.deleteObject(new DeleteObjectRequest(bucketName, keyName));


    }

    public static boolean uploadVerifiedUsersToS3(File file){

        Date date = new Date();

        String fileName = date.toString()+"verifiedUsers.csv";

        String bucketName = "globatiimages/batchpay";


        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJSYT5343PVMDHCRQ",
                "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM");

        // create a client connection based on credentials
        AmazonS3 s3client = new AmazonS3Client(credentials);

        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));

        return true;

    }

    public static File getFlightBookingsFromS3(){
        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJSYT5343PVMDHCRQ",
                "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM");

        String bucket = "globati-flight-bookings";
        String key = "flightbookings.csv";

        // create a client connection based on credentials
        AmazonS3 s3client = new AmazonS3Client(credentials);

        S3Object item = s3client.getObject(bucket, key);

        inputStream = item.getObjectContent();

        File file = new File("flightbookings.csv");

        try {
            outputStream = new FileOutputStream(file);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public static List<FlightBookingRow> getFlightBookingRowsFromFile(File file) throws ParseException {
        BufferedReader br = null;
        FileReader fr = null;
        List<FlightBookingRow> flightBookings = new ArrayList<>();

        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine;
            String date=null;

            while ((sCurrentLine = br.readLine()) != null) {
//                System.out.println(sCurrentLine);
                if(DateTools.isValidDateFormat(sCurrentLine)){
                    date = sCurrentLine;
                }
                else{
                    String lineItems[] = sCurrentLine.split(",");
                    FlightBookingRow flightBookingRow = new FlightBookingRow(
                            date,
                            lineItems[0],
                            lineItems[1],
                            lineItems[2],
                            lineItems[3],
                            lineItems[4],
                            lineItems[5],
                            lineItems[6],
                            lineItems[7],
                            lineItems[8],
                            lineItems[9]
                    );
                    flightBookings.add(flightBookingRow);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return flightBookings;
    }

    //TODO: 12/20/17 Make this for HotelBookings

    public static File getHotelBookingsFromS3(){
        InputStream inputStream = null;
        FileOutputStream outputStream = null;

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJSYT5343PVMDHCRQ",
                "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM");

        String bucket = "globati-hotel-bookings";
        String key = "hotelbookings.csv";

        // create a client connection based on credentials
        AmazonS3 s3client = new AmazonS3Client(credentials);

        S3Object item = s3client.getObject(bucket, key);

        inputStream = item.getObjectContent();

        File file = new File("hotelbookings.csv");

        try {
            outputStream = new FileOutputStream(file);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    // outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }

    public static List<HotelBookingRow> getHotelBookingRowsFromFile(File file) throws ParseException {
        BufferedReader br = null;
        FileReader fr = null;
        List<HotelBookingRow> flightBookings = new ArrayList<>();

        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String sCurrentLine;
            String date=null;

            while ((sCurrentLine = br.readLine()) != null) {
//                System.out.println(sCurrentLine);
                if(DateTools.isValidDateFormat(sCurrentLine)){
                    date = sCurrentLine;
                }
                else{
                    String lineItems[] = sCurrentLine.split(",");
                    HotelBookingRow flightBookingRow = new HotelBookingRow(
                            date,
                            lineItems[0],
                            lineItems[1],
                            lineItems[2],
                            lineItems[3],
                            lineItems[4],
                            lineItems[5],
                            lineItems[6],
                            lineItems[7],
                            lineItems[8]
                    );
                    flightBookings.add(flightBookingRow);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return flightBookings;
    }

    public static boolean deleteBookingFileFromS3(){

            String bucketName = "globati-flight-bookings";
            String keyName = "flightbookings.csv";

            AWSCredentials credentials = new BasicAWSCredentials(
                    "AKIAJSYT5343PVMDHCRQ",
                    "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM");

            AmazonS3 s3Client = new AmazonS3Client(credentials);

            s3Client.deleteObject(new DeleteObjectRequest(bucketName, keyName));

            return true;

    }
}
