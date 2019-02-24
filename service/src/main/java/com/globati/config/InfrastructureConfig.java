package com.globati.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.globati.service.PropertiesService;
import org.apache.commons.lang.StringUtils;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.context.annotation.Profile;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile("production")
@EnableJpaRepositories("com.globati.repository.dynamodb")
@EnableTransactionManagement
@EnableScheduling
@PropertySource("classpath:environment/${GLOBATI_SERVER_ENV}.properties")
@EnableDynamoDBRepositories(basePackages = "com.globati.repository.dynamodb")
public class InfrastructureConfig  {


	@Autowired
	PropertiesService propertiesService;

	/**
	 * For production awesome database needs to be changed to a real username and password
	 * @return
     */
	@Bean
	public DataSource dataSource(){
		String dblogin = propertiesService.getDbLogin();
		String driver = propertiesService.getDriver();
		String path = propertiesService.getDbPath();
		String dbpassword = propertiesService.getDbPassword();


		HikariConfig config = new HikariConfig();
		config.setDriverClassName(driver);
		config.setJdbcUrl(path);
		config.setUsername(dblogin);
		config.setPassword(dbpassword);
		return new HikariDataSource(config);
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {

		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.MYSQL);
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