package com.tek.simTrade.service;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.tek.simTrade.models.Sim;

@Service
public class SimService {
	/*
	 * The DynamoDBMapper class is the entry point to DynamoDB. It provides
	 * access to a DynamoDB endpoint and enables you to access your data in
	 * various tables, perform various CRUD operations on items, and execute
	 * queries and scans against tables.
	 */
	@Autowired
	private DynamoDBMapper mapper;

	@Autowired
	private AmazonDynamoDBClient amazonDynamoDBClient;

	// model/Sim object
	Sim sim = new Sim();

	/*
	 * creates a sim object
	 */
	public void createSimTradeTable() {

		CreateTableRequest createTableRequest = mapper.generateCreateTableRequest(Sim.class);
		// Table provision throughput is still required since it cannot be
		// specified in your POJO
		createTableRequest.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
		// Fire off the CreateTableRequest using the low-level client
		amazonDynamoDBClient.createTable(createTableRequest);
	}

	/*
	 * Scans the model/Sim table to return list of sims.
	 */
	public List<Sim> displayDetails(String country) {

		Sim dim = new Sim();
		dim.setCountry(country);
		DynamoDBQueryExpression<Sim> queryExpression = new DynamoDBQueryExpression<Sim>().withHashKeyValues(dim);
		List<Sim> lsim = mapper.query(Sim.class, queryExpression);

		return lsim;

	}
	
	

}
