package com.globati.utildb;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
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

    public static boolean uploadVerifiedUsersToS3(File file, String name){

        Date date = new Date();

        String fileName = name;

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

    public static List<File> getProcessedPaymentFile(){
        List<File> processedFiles = new ArrayList<>();

        InputStream flightStream = null;
        InputStream hotelStream = null;
        FileOutputStream outputStream = null;

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJSYT5343PVMDHCRQ",
                "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM");

        String bucket = "processed-payments";
        String key = "processed-flights.csv";
        String key2 = "processed-hotels.csv";

        // create a client connection based on credentials
        AmazonS3 s3client = new AmazonS3Client(credentials);

        S3Object flightItem = s3client.getObject(bucket, key);
        S3Object hotelsItem = s3client.getObject(bucket, key2);

        flightStream = flightItem.getObjectContent();
        hotelStream = hotelsItem.getObjectContent();

        File flightsFile = new File("processed-flights.csv");
        File hotelsFile = new File("processed-hotels.csv");

        try {
            outputStream = new FileOutputStream(flightsFile);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = flightStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (flightStream != null) {
                try {
                    flightStream.close();
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
        processedFiles.add(flightsFile);

        try {
            outputStream = new FileOutputStream(hotelsFile);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = hotelStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (hotelStream != null) {
                try {
                    hotelStream.close();
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

        processedFiles.add(hotelsFile);

        return processedFiles;

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

    public static boolean deleteHotelBookingFileFromS3(){
        String bucketName = "globati-hotel-bookings";
        String keyName = "hotelbookings.csv";

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJSYT5343PVMDHCRQ",
                "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM");

        AmazonS3 s3Client = new AmazonS3Client(credentials);

        s3Client.deleteObject(new DeleteObjectRequest(bucketName, keyName));

        return true;
    }

    private static File getPropertyDataFromFileLocatedInS3(){
        System.out.println("** GLOBATI: Getting properties file from S3.");

        FileOutputStream outputStream = null;


        String bucketName = "globati-backend-files/properties";
        String keyName = "properties.csv";

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJSYT5343PVMDHCRQ",
                "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM");

        AmazonS3 s3client = new AmazonS3Client(credentials);


        AmazonS3 s3Client = new AmazonS3Client(credentials);

        S3Object propertiesItem = s3client.getObject(bucketName, keyName);

        InputStream propertiesStream= propertiesItem.getObjectContent();

        File propertiesFile = new File("properties.csv");


        try {
            outputStream = new FileOutputStream(propertiesFile);

            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = propertiesStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (propertiesStream != null) {
                try {
                    propertiesStream.close();
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

        return propertiesFile;

    }

}
