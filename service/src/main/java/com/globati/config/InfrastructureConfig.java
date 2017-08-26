package com.globati.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import com.braintreegateway.Environment;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Profile("running")
@EnableJpaRepositories("com.globati.repository")
@EnableTransactionManagement
@EnableScheduling
public class InfrastructureConfig  {


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
	 * This function loads the environment properties. As of now these includes links used between
	 * the envioronments, such as the angular light server paths, as well as the productions paths,
	 * db url etc... This does not load the AWS keys for accessing S3. It should probably do this as
	 * well, although it is maybe not super necessary.
	 *
	 *
	 * activeVendor is assigned in both if statements, because we might add an environment later
	 * with a different databseVendor.
	 *
	 * @param env
	 * @throws IOException
	 */

	private void  loadEnvironmentProperties(String env) throws IOException {
		Properties props = new Properties();
		String devevelopmentResource = "environment/development.properties";
		String productionResource = "environment/production.properties";
		if (env.equals("dev")) {
			try (InputStream resourceStream = InfrastructureConfig.class.getClassLoader().getResourceAsStream(devevelopmentResource)) {
				props.load(resourceStream);
				Paths.setBraintreeEnvironment(Environment.SANDBOX);
				activeVendor = Database.MYSQL;
			}
		} else if (env.equals("prod")) {
			try (InputStream resourceStream = InfrastructureConfig.class.getClassLoader().getResourceAsStream(productionResource)) {
				props.load(resourceStream);
				Paths.setBraintreeEnvironment(Environment.PRODUCTION);
				activeVendor = Database.MYSQL;
			}
		}

		String imageBucket = props.get("imageBucket").toString();
		String staticGlobatiAddress = props.get("staticGlobatiAddress").toString();
		String imagesWithDash = props.get("imagesWithDash").toString();
		String dbLogin = props.get("dbLogin").toString();
		String dbPassword = props.get("dbPassword").toString();
		String dbPath = props.get("dbPath").toString();
		String driver = props.get("driver").toString();

		//For braintree
		String merchantId=props.get("merchantId").toString();
		String publicKey=props.get("publicKey").toString();
		String privateKey=props.get("privateKey").toString();


		Paths.setActiveS3Bucket(imageBucket);
		Paths.setActiveStaticGlobati(staticGlobatiAddress);
		Paths.setActiveImageLink(imagesWithDash);
		Paths.setActiveDbLoginName(dbLogin);
		Paths.setActiveDbPassword(dbPassword);
		Paths.setActiveDatabase(dbPath);
		Paths.setActiveDriver(driver);
		Paths.setMerchantId(merchantId);
		Paths.setPublicKey(publicKey);
		Paths.setPrivateKey(privateKey);

		activeDriver = Paths.getActiveDriver();
		activeDbPassword = Paths.getActiveDbPassword();
		activeDbLogin = Paths.getActiveDbLoginName();
		databasePath = Paths.getActiveDatabase();

		System.out.println("************************ Globati server environment: "+env);

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



}