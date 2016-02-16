package com.tek.simTrade.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.tek.simTrade.models.SimDetails;

@Service
public class SimDetailsService
{
	@Autowired
	private DynamoDBMapper mapper;

	@Autowired
	 private AmazonDynamoDBClient amazonDynamoDBClient;
	

	SimDetails sim = new SimDetails();
	private SimDetails getSimDetails = new SimDetails();
	
	public void createSimTradeTable()
	{

		CreateTableRequest createTableRequest = mapper
				.generateCreateTableRequest(SimDetails.class);
		// Table provision throughput is still required since it cannot be
		// specified in your POJO
		createTableRequest
				.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
		// Fire off the CreateTableRequest using the low-level client
		amazonDynamoDBClient.createTable(createTableRequest);
	}
	
	public List<SimDetails> displayDetails(String country)
	{
		/*
		sim.setUserName("suresh");
		sim.setCountry("Canada");
		sim.setExpiryDate("20/09/2016");
		sim.setSimType("Prepaid");
		
		mapper.save(sim);*/
		
		//getSimDetails= mapper.load(SimDetails.class, "US", "Pratheeth" );
		
	/*	
		ScanRequest scanRequest = new ScanRequest().withTableName("simDetails");
		  ScanResult result = amazonDynamoDBClient.scan(scanRequest);
		 Map<String,AttributeValue> e1 = 
		       new HashMap<String, AttributeValue>();
		  for (Map<String, AttributeValue> item : result.getItems())
		  {
		     e1=item;
		  } 
		  return e1;*/
		
		SimDetails dim=new SimDetails();
		  dim.setCountry(country);
		  DynamoDBQueryExpression<SimDetails> queryExpression = new DynamoDBQueryExpression<SimDetails>()
		          .withHashKeyValues(dim);
		         List<SimDetails> lsim=mapper.query(SimDetails.class, queryExpression); 
		  
		  
		  return lsim;
		 
		 
	}
}
