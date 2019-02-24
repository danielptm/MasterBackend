package com.globati.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.globati.service.PropertiesService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang.StringUtils;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Created by daniel on 7/23/17.
 */


@Configuration
@Profile("test")
@EnableJpaRepositories("com.globati.repository.mysql")
@EnableTransactionManagement
@EnableScheduling
@PropertySource("classpath:environment/test.properties")
@EnableDynamoDBRepositories(basePackages = "com.globati.repository.dynamodb")
public class TestProfile {

    @Autowired
    PropertiesService propertiesService;

    String activeDriver;
    String databasePath;
    String activeDbLogin;
    String activeDbPassword;
    Database activeVendor = Database.DERBY;

    public TestProfile() throws IOException {

        Map<String, String> SYSTEMENV = System.getenv();


        Properties props = new Properties();
        String testResource = "environment/test.properties";
        try (InputStream resourceStream = InfrastructureConfig.class.getClassLoader().getResourceAsStream(testResource)) {
            props.load(resourceStream);
            activeVendor = Database.DERBY;
        }
        String dbPath = props.get("dbPath").toString();
        String driver = props.get("driver").toString();

        activeDriver = driver;
        databasePath = dbPath;
        activeDbPassword="";
        activeDbLogin="";

        System.out.println("=======================================================================");
        System.out.println("**                   GLOBATI "+SYSTEMENV.get("GLOBATI_SERVER_ENV")+" ENVIRONMENT     **");
        System.out.println("=========================================================================");
        System.out.println("Active Driver: "+activeDriver+" "+"ActiveDbPassword: "+activeDbPassword+" "+"ActiveDbLogin: "+activeDbLogin+"ActiveDbPath: "+databasePath);
        System.out.println("=======================================================================");
        System.out.println("**                      RUNNING UNIT TESTS                          **");
        System.out.println("=======================================================================");

    }

    @Bean
    public DataSource dataSource(){
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(activeDriver);
        config.setJdbcUrl(databasePath);
        config.setUsername(activeDbLogin);
        config.setPassword(activeDbPassword);
        return new HikariDataSource(config);
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {

        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(activeVendor);
        adapter.setGenerateDdl(true);

        return adapter;
    }

    //Make sure that the right datasource is being set
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource( dataSource() ); //Get data source config here!
        factory.setJpaVendorAdapter(jpaVendorAdapter());
        factory.setPackagesToScan("com.globati.mysql.dbmodel");

        return factory;
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB
                = new AmazonDynamoDBClient(amazonAWSCredentials());

        if (!StringUtils.isEmpty(propertiesService.getAmazonDynamoDBEndpoint())) {
            amazonDynamoDB.setEndpoint(propertiesService.getAmazonDynamoDBEndpoint());
        }

        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                propertiesService.getAmazonAWSAccessKey(), propertiesService.getAmazonAWSSecretKey());
    }


}
