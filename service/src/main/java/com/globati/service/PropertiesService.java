package com.globati.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * This is for the application properties, not related to a Property class.
 */
@Service
public class PropertiesService {

    @Value("${imageBucket}")
    String imageBucket;
    @Value("${staticGlobatiAddress}")
    String staticGlobatiAddress;
    @Value("${imagesWithDash}")
    String imagesWithDash;
    @Value("${s3Root}")
    String s3Root;
    @Value("${dbLogin}")
    String dbLogin;
    @Value("${dbPassword}")
    String dbPassword;
    @Value("${dbPath}")
    String dbPath;
    @Value("${driver}")
    String driver;
    @Value("${vendor}")
    String vendor;
    @Value("${merchantId}")
    String merchantId;
    @Value("${publicKey}")
    String publicKey;
    @Value("${privateKey}")
    String privateKey;
    @Value("${jwtSecret}")
    String jwtSecret;
    @Value("${amazon.dynamodb.endpoint}")
    private String amazonDynamoDBEndpoint;
    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;
    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    String activeCreateAddLink = "https://globati.com/connect/";

    public String getImageBucket(){
        return imageBucket;
    }

    public String getStaticGlobatiAddress(){
        return staticGlobatiAddress;
    }

    public String getImagesWithDash(){
        return imagesWithDash;
    }

    public String getS3Root(){
        return s3Root;
    }

    public String getDbLogin(){
        return dbLogin;
    }

    public String getDbPassword(){
        return dbPassword;
    }

    public String getDbPath(){
        return dbPath;
    }

    public String getDriver(){
        return driver;
    }

    public String getVendor(){
        return vendor;
    }

    public String getPublicKey(){
        return publicKey;
    }

    public String getPrivateKey(){
        return privateKey;
    }

    public String getActiveCreateAddLink(){
        return activeCreateAddLink;
    }

    public String getJwtSecret() {
        return jwtSecret;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public String getAmazonDynamoDBEndpoint() {
        return amazonDynamoDBEndpoint;
    }

    public String getAmazonAWSAccessKey() {
        return amazonAWSAccessKey;
    }

    public String getAmazonAWSSecretKey() {
        return amazonAWSSecretKey;
    }
}
