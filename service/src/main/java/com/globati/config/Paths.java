package com.globati.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.vendor.Database;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * This file is basically a config file for globati2.1
 * To change back and fourth between production/development Do the following:
 *
 * Set image link for emails
 * Set activeStaticGlobati
 * Set activeStaticMember
 * Set activeDatabase
 *
 *
 * Create a production build
 * mvn install -DskipTests
 *
 * Do test build
 * mvn clean install
 *
 *
 * The idea is that if you just set these values, and then rebuild the project, you can switch back and fourth between development/production environemtns.
 * "jdbc:mysql://globati.cpkg3e91o3s6.eu-central-1.rds.amazonaws.com:3306/globati_db?createDatabaseIfNotExist=true"
 *
 */


public class Paths {




    private static final Logger log = LogManager.getLogger(Paths.class);

    //*********************Set these values to switch back and fourth between production/development*********************

    private static final String s3Root = "https://s3.eu-central-1.amazonaws.com/";


    //productionStaticGlobati || localStaticGlobati && localStaticMembers || productionStaticMembers
    private static String activeStaticGlobati;
    private static String activeStaticMembers;

    //I should not need to change this..... but it should be activeStaticGlobati/connect/
//    private static String activeCreateAddLink = activeStaticGlobati+"connect/";

    //The one above is how it was before, but have had problems so i just changed it to this.
    private static String activeCreateAddLink = "https://globati.com/connect/";

    //Use development links for testing.
    //productionImages || developmentImages
    private static String activeImageLink;

    //productionBucket || developmentBucket
    private static String activeS3Bucket;

//    Database configuration, use derby when running tests, development,
//    when running the app locall, and proudction for deployment

    //productionDatabase || developmentDatabase || testDatabase
    private static String activeDatabase;

    //developmentDbLogin || productionDbLogin && developmentDbPassword || productionDbPassword
    // || testDbLogin && testDbPassword
    private static String activeDbLogin;
    private static String activeDbPassword;

    //You cant set a Database object from a properties file, so the database key has to be set which indicates which
    //database configurations to load
    private static String databaseKey;


    //Dont change these for now, this is useful when using he in memory database, but derby is not working now, so use mysql
    //derbyVendor || mysqlVendor
    private static Database activeVendor;

    //derbyDatabaseDriver || mysqlDatabaseDriver
    private static String activeDriver;

    //For braintree
    private static String merchantId;

    private static String publicKey;

    private static String privateKey;


    //*********************^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^*********************


    /**
     * Returns the link for the active database connect.
     * @return
     */
    public static String getActiveDatabase(){
        return activeDatabase;
    }

    /**
     * Returns the link for the active static globati splash page site.
     * @return
     */
    public static String getActiveStaticGlobati(){
        return activeStaticGlobati;
    }

    /**
     * Returns the active link for the static member admin site.
     * @return
     */
    public static String getActiveStaticMembers(){
        return activeStaticMembers;
    }

    /**
     * Returns the active path for creating an add link.
     * @return
     */
    public static String getActiveCreateAddLink(){
        return activeCreateAddLink;
    }

    /**
     * Returns the active image source path. So this is a local url to an image bucket or the AWS bucket.
     * @return
     */
    public static String getActiveImageLink(){
        return activeImageLink;
    }

    /**
     * Returns the active S3 bucket
     * @return
     */
    public static String getActiveS3Bucket(){
        return activeS3Bucket;
    }

    /**
     * Returns the active login name for the database
     * @return
     */
    public static String getActiveDbLoginName(){
        return activeDbLogin;
    }

    /**
     * Returns the active database password
     * @return
     */
    public static String getActiveDbPassword(){
        return activeDbPassword;
    }

    /**
     * Returns the active database vendor
     * @return
     */
    public static Database getActiveVendor() {
        return activeVendor;
    }

    /**\
     * Returns the active database driver
     * @return
     */
    public static String getActiveDriver() {
        return activeDriver;
    }

    /**
     * Returns the S3 root.
     */

    public static String getS3Root(){
        return s3Root;
    }

    public static String getDatabaseKey(){
        return databaseKey;
    }

//    *********************************************


    /**
     * Returns the link for the active database connect.
     * @return
     */
    public static void setDatabaseKey(String data){
         activeDatabase=data;
    }

    /**
     * Returns the link for the active static globati splash page site.
     * @return
     */
    public static void setActiveStaticGlobati(String data){
         activeStaticGlobati = data;
    }

    /**
     * Returns the active link for the static member admin site.
     * @return
     */
    public static void setActiveStaticMembers(String data){
         activeStaticMembers = data;
    }


    /**
     * Returns the active image source path. So this is a local url to an image bucket or the AWS bucket.
     * @return
     */
    public static void setActiveImageLink(String data){
         activeImageLink = data;
    }

    /**
     * Returns the active S3 bucket
     * @return
     */
    public static void setActiveS3Bucket(String data){
         activeS3Bucket = data;
    }

    /**
     * Returns the active login name for the database
     * @return
     */
    public static void setActiveDbLoginName(String data){
         activeDbLogin=data;
    }

    /**
     * Returns the active database password
     * @return
     */
    public static void setActiveDbPassword(String data){
         activeDbPassword = data;
    }


    public static void setActiveDatabase(String data){
        activeDatabase = data;
    }

    /**
     * Returns the active database vendor
     * @return
     */
    public static void setActiveVendor(Database data) {
         activeVendor=data;
    }

    /**\
     * Returns the active database driver
     * @return
     */
    public static void setActiveDriver(String data) {
         activeDriver = data;
    }


    public static void setMerchantId(String item){
        merchantId = item;
    }

    public static String getMerchantId(){
        return merchantId;
    }

    public static void setPublicKey(String item){
        publicKey = item;
    }

    public static String getPublicKey(){
        return publicKey;
    }

    public static void setPrivateKey(String item){
         privateKey = item;
    }

    public static String getPrivateKey(){
        return privateKey;
    }

}
