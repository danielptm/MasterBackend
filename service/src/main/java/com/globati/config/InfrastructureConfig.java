package com.globati.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.braintreegateway.Environment;
import com.globati.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
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

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

@Configuration
@Profile("production")
@EnableJpaRepositories("com.globati.repository")
@EnableTransactionManagement
@EnableScheduling
@PropertySource("classpath:environment/${GLOBATI_SERVER_ENV}.properties")
public class InfrastructureConfig  {

	@Autowired
	org.springframework.core.env.Environment environment;

	@Autowired
	PropertiesService propertiesService;


	private static  String activeDbLogin;
	private static  String activeDbPassword;
	private static  Database activeVendor;
	private static  String activeDriver;
	private static String databasePath;


	public InfrastructureConfig() throws IOException {
		Map<String, String> env = System.getenv();
		loadEnvironmentProperties(env.get("GLOBATI_SERVER_ENV"));
	}

	/**
	 * This functions loads some properties from the properties files. PropertiesService cannot be injected
	 * and used in this class because it is a config class and the values for PropertiesService have not
	 * been initialized yet, so the required values are read in directly from the files.
	 *
	 * @param env
	 * @throws IOException
	 */

	private void  loadEnvironmentProperties(String env) throws IOException {
		Map<String, String> SYSTEMENV = System.getenv();

		System.out.println("======================================================");
		System.out.println("WELCOME TO AWS CONTINUOUS DELIVERTY VIA PIPELINE");
		System.out.println("======================================================");

		Properties props = new Properties();
		String devevelopmentResource = "environment/development.properties";
		String productionResource = "environment/production.properties";
		if (env.equals("development")) {
			try (InputStream resourceStream = InfrastructureConfig.class.getClassLoader().getResourceAsStream(devevelopmentResource)) {
				props.load(resourceStream);
//				Paths.setBraintreeEnvironment(Environment.SANDBOX);
				activeVendor = Database.MYSQL;
			}
		} else if(env.equals("production")) {
			try (InputStream resourceStream = InfrastructureConfig.class.getClassLoader().getResourceAsStream(productionResource)) {
				props.load(resourceStream);
//				Paths.setBraintreeEnvironment(Environment.PRODUCTION);
				activeVendor = Database.MYSQL;
			}
		}
		else{
			System.out.println("======================================================");
			System.out.println("NO ENVIRONMENT VARIABLE SET..... SERVER CANNOT START");
			System.out.println("======================================================");
		}

//		String imageBucket = props.get("imageBucket").toString();
//		String staticGlobatiAddress = props.get("staticGlobatiAddress").toString();
//		String imagesWithDash = props.get("imagesWithDash").toString();
		String dbLogin = props.get("dbLogin").toString();
		String dbPassword = props.get("dbPassword").toString();
		String dbPath = props.get("dbPath").toString();
		String driver = props.get("driver").toString();

		activeDriver = driver;
		activeDbPassword = dbPassword;
		activeDbLogin = dbLogin;
		databasePath = dbPath;


		System.out.println("=======================================================================");
		System.out.println("**    GLOBATI "+SYSTEMENV.get("GLOBATI_SERVER_ENV")+" ENVIRONMENT   **");
		System.out.println("=========================================================================");

	}


	/**
	 * For production awesome database needs to be changed to a real username and password
	 * @return
     */
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

	@Bean
	public String sayHi(){
		return "hello world";
	}


	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		return propertySourcesPlaceholderConfigurer;
	}
}