package com.tek.simTrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.tek.simTrade.models.UsersNew;

@Service
public class UsersNewService
{

	@Autowired
	private DynamoDBMapper mapper;

	@Autowired
	private AmazonDynamoDBClient amazonDynamoDBClient;
/*
 * create a model/Users table
 */
	public void createUserTable()
	{

		CreateTableRequest createTableRequest = mapper
				.generateCreateTableRequest(UsersNew.class);
		// Table provision throughput is still required since it cannot be
		// specified in your POJO
		createTableRequest
				.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
		// Fire off the CreateTableRequest using the low-level client
		amazonDynamoDBClient.createTable(createTableRequest);
	}
	
	
	
}
