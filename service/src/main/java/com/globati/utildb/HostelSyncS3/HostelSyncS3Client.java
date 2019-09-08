package com.globati.utildb.HostelSyncS3;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globati.util.Mapper;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HostelSyncS3Client {

    private static final Logger LOGGER = LogManager.getLogger(HostelSyncS3Client.class);

    private static final BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIA56GAXAIF4QXYNU6C", "TDOrbu8Osp0jzdqrxXY1Sg/WtTFDe17B4ph1VdLU");
    private static final AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .build();

    private static String response;
    private static Set<Location> locations;
    private static List<Hostel> hostels;


    private static void readFile() {
        ObjectMapper objectMapper = Mapper.getMapper();
        try {
            hostels = objectMapper.readValue(getResponse(), new TypeReference<ArrayList<Hostel>>(){});
            locations = new HashSet<>();
            for (Hostel hostel: hostels) {
                Location location = new Location(hostel.getCity(), hostel.getCountry());
                locations.add(location);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public static Set<Location> getLocations() {
        if (locations == null) {
            readFile();
        }
        return locations;
    }

    public static List<Hostel> getHostels() {
        if (locations == null) {
            readFile();
        }
        return hostels;
    }

    /**
     * Run this every 30 minutes.
     */
    @Scheduled(fixedDelay = 1800000)
    private static void reloadCache() {
        readFile();
    }

}
