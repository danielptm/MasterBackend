package com.globati.utildb;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.globati.config.Paths;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

/**
 * Created by daniel on 12/22/16.
 */
public class ImageHandler {

    //Value assigned during method execution
    private static String lastCreatedPath=null;

    private static final String activePath= Paths.getActiveImageLink();

    private static final Logger log = LogManager.getLogger(ImageHandler.class);

    public static String getMostRecentImagePath(){
        return lastCreatedPath;
    }

    private static String getUUID(){
        return UUID.randomUUID().toString();
    }

    public static boolean checkIfImageExists(String path){
        StringBuilder sb = new StringBuilder();
        String root = activePath;
        sb.append(root);
        sb.append(path);

        File file = new File(sb.toString());

        if(file.exists()){
            return true;
        }

        else{
            return false;
        }
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
        String bucketName = Paths.getActiveS3Bucket();

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
        String bucketName = Paths.getActiveS3Bucket();

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAJSYT5343PVMDHCRQ",
                "YEd/nvVixLnRyhLOYlo1iUhMLiPZ4qcjiRx7vJiM");

        AmazonS3 s3Client = new AmazonS3Client(credentials);

        s3Client.deleteObject(new DeleteObjectRequest(bucketName, keyName));


    }


    /**
     * Converts an inputstream to a buffered image, and reduces the resolution, then writes it.
     * @param is
     * @param dbimage
     * @throws IOException
     */

    public static void resizeImage(InputStream is, String dbimage) throws IOException {

        StringBuilder sb = new StringBuilder();
        sb.append(activePath);
        sb.append(dbimage);
        String path = sb.toString();
        log.debug("resize image:: "+path);

        BufferedImage bufferedImage = ImageIO.read(is);

        ImageWriter writer = (ImageWriter)ImageIO.getImageWritersByFormatName("jpg").next();
        ImageWriteParam iwp = writer.getDefaultWriteParam();
        iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        iwp.setCompressionQuality(0.2f);

        File file = new File(path);
        FileImageOutputStream output = new FileImageOutputStream(file);

        writer.setOutput(output);
        IIOImage image = new IIOImage(bufferedImage, null, null);

        writer.write(null, image, iwp);

        writer.dispose();

    }

    public static InputStream readLocalImage(String image) throws FileNotFoundException {
        return new FileInputStream(activePath+image);
    }
}
