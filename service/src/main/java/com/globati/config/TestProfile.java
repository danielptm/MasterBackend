package com.globati.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
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
import java.util.Properties;

/**
 * Created by daniel on 7/23/17.
 */


@Configuration
@Profile("test")
@EnableJpaRepositories("com.globati.repository")
@EnableTransactionManagement
@EnableScheduling
public class TestProfile {

    String activeDriver;
    String databasePath;
    String activeDbLogin;
    String activeDbPassword;
    Database activeVendor = Database.DERBY;

    public TestProfile() throws IOException {
        Properties props = new Properties();
        String testResource = "environment/test.properties";
        try (InputStream resourceStream = InfrastructureConfig.class.getClassLoader().getResourceAsStream(testResource)) {
            props.load(resourceStream);
            activeVendor = Database.DERBY;
        }

        String imageBucket = props.get("imageBucket").toString();
        String staticGlobatiAddress = props.get("staticGlobatiAddress").toString();
        String staticMyglobatiAdmin = props.get("staticMyglobatiAdmin").toString();
        String imagesWithDash = props.get("imagesWithDash").toString();
        String dbPath = props.get("dbPath").toString();
        String driver = props.get("driver").toString();


        Paths.setActiveS3Bucket(imageBucket);
        Paths.setActiveStaticGlobati(staticGlobatiAddress);
        Paths.setActiveImageLink(imagesWithDash);
        Paths.setActiveDatabase(dbPath);
        Paths.setActiveDriver(driver);

        activeDriver = Paths.getActiveDriver();
        databasePath = Paths.getActiveDatabase();
        activeDbPassword="";
        activeDbLogin="";



        System.out.println("************************ Globati server environment: test");
        System.out.println("Active Driver: "+activeDriver+" "+"ActiveDbPassword: "+activeDbPassword+" "+"ActiveDbLogin: "+activeDbLogin+"ActiveDbPath: "+databasePath);


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
        factory.setPackagesToScan("com.globati.dbmodel");

        return factory;
    }


}
