package com.globati.config;

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
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

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

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
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