package com.tek.simTrade.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.tek.simTrade.models.Sim;

@Service
public class SimService
{
	@Autowired
	private DynamoDBMapper mapper;

	@Autowired
	 private AmazonDynamoDBClient amazonDynamoDBClient;
	

	Sim sim = new Sim();
	
	public void createSimTradeTable()
	{

		CreateTableRequest createTableRequest = mapper
				.generateCreateTableRequest(Sim.class);
		// Table provision throughput is still required since it cannot be
		// specified in your POJO
		createTableRequest
				.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
		// Fire off the CreateTableRequest using the low-level client
		amazonDynamoDBClient.createTable(createTableRequest);
	}
	
	public List<Sim> displayDetails(String country)
	 {
	  
	  Sim dim=new Sim();
	    dim.setCountry(country);
	    DynamoDBQueryExpression<Sim> queryExpression = new DynamoDBQueryExpression<Sim>()
	            .withHashKeyValues(dim);
	           List<Sim> lsim=mapper.query(Sim.class, queryExpression); 
	    
	    
	    return lsim;
	   
	   
	 }
	
	public Map<String, String> countryList()
	{
		 Map< String, String > countries = new HashMap<String, String>();  
	        countries.put("US", "US");  
	        countries.put("India", "INDIA");  
	        countries.put("Canada", "CANADA");  
	        
	        return countries;
	}
	
	
}
