package com.tek.simTrade.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDbConfig
{
	@Autowired
	private Environment env;

	/**
	 * use this only when you need to prefix the table name, according to the
	 * profile chosen
	 */
	// @Bean
	// public MethodInvokingFactoryBean methodInvokingFactoryBean()
	// {
	// MethodInvokingFactoryBean methodInvokingFactoryBean = new
	// MethodInvokingFactoryBean();
	// methodInvokingFactoryBean.setStaticMethod(
	// "com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix");
	// String[] args = { "DES-" };
	// methodInvokingFactoryBean.setArguments(args);
	// return new MethodInvokingFactoryBean();
	// }

	/** use this if you need to override the table with a name*/
//	@Bean
//	public DynamoDBMapperConfig dynamoMapperConfig()
//	{
//		DynamoDBMapperConfig dynamoMapperConfig = new DynamoDBMapperConfig(new TableNameOverride("<name to override the table with>"));
//		return dynamoMapperConfig;
//	}

	@Bean
	public BasicAWSCredentials basicAWSCredentials()
	{
		// TODO get access key and secret from properties file
		BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(
				env.getProperty("amazon.access.key"),
				env.getProperty("amazon.secret.key"));
		return basicAWSCredentials;
	}

	@Bean
	public AmazonDynamoDBClient amazonDynamoDBClient()
	{
		AmazonDynamoDBClient amazonDynamoDBClient = new AmazonDynamoDBClient(
				basicAWSCredentials());
		// TODO get endpoint from properties file
		amazonDynamoDBClient
				.setEndpoint(env.getProperty("amazon.dynamodb.endpoint"));
		return amazonDynamoDBClient;
	}

	@Bean
	public DynamoDBMapper mapper()
	{
		return new DynamoDBMapper(amazonDynamoDBClient());
	}
}
