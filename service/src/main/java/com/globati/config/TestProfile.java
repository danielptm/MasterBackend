package com.globati.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.globati.service.PropertiesService;
import com.globati.service.dynamodb.DynamoPropertyService;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang.StringUtils;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@EnableTransactionManagement
@EnableScheduling
@PropertySource("classpath:environment/test.properties")
@EnableDynamoDBRepositories(basePackages = "com.globati.repository.dynamodb")
public class TestProfile {


    @Value("${amazon.dynamodb.endpoint}")
    private String dynamoEndpoint;
    @Value("${amazon.aws.accesskey}")
    private String amazonAWSAccessKey;
    @Value("${amazon.aws.secretkey}")
    private String amazonAWSSecretKey;

    public TestProfile() throws IOException {

        Map<String, String> SYSTEMENV = System.getenv();


        Properties props = new Properties();
        String testResource = "environment/test.properties";
        try (InputStream resourceStream = InfrastructureConfig.class.getClassLoader().getResourceAsStream(testResource)) {
            props.load(resourceStream);
        }

        System.out.println("=======================================================================");
        System.out.println("**                   GLOBATI "+SYSTEMENV.get("GLOBATI_SERVER_ENV")+" ENVIRONMENT     **");
        System.out.println("=========================================================================");
        System.out.println("=======================================================================");
        System.out.println("**                      RUNNING UNIT TESTS                          **");
        System.out.println("=======================================================================");

    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB
                = new AmazonDynamoDBClient(amazonAWSCredentials());

        if (!StringUtils.isEmpty(dynamoEndpoint)) {
            amazonDynamoDB.setEndpoint(dynamoEndpoint);
        }

        return amazonDynamoDB;
    }

    @Bean
    public AWSCredentials amazonAWSCredentials() {
        return new BasicAWSCredentials(
                amazonAWSAccessKey, amazonAWSSecretKey);
    }

    @Bean
    public DynamoPropertyService dynamoPropertyService() {
        return new DynamoPropertyService();
    }


}
