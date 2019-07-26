package com.globati.utildb;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class HostelSyncS3Client {

    private static final BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA56GAXAIF4QXYNU6C", "TDOrbu8Osp0jzdqrxXY1Sg/WtTFDe17B4ph1VdLU");
    private static final AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .build();

    private static String response;


    public static String readFile() {
        if (response == null) {
            response = getResponse();
        }
        return response;
    }

    private static String getResponse() {
        S3Object s3Object = s3Client.getObject("hostelsync.com", "static/hostels.json");
        InputStream inputStream = s3Object.getObjectContent();

        StringWriter writer = new StringWriter();
        try {
            IOUtils.copy(inputStream, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String theString = writer.toString();

        return theString;


    }
}
