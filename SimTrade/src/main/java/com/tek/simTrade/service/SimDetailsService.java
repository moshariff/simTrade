package com.tek.simTrade.service;

import java.util.HashMap;
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
		
		SimDetails dim=new SimDetails();
		  dim.setCountry(country);
		  DynamoDBQueryExpression<SimDetails> queryExpression = new DynamoDBQueryExpression<SimDetails>()
		          .withHashKeyValues(dim);
		         List<SimDetails> lsim=mapper.query(SimDetails.class, queryExpression); 
		  
		  
		  return lsim;
		 
		 
	}
	public Map<String, String> getCountriesList()
	{
		Map<String, String> countries = new HashMap<String, String>();
		countries.put("US", "US");
		countries.put("India", "INDIA");
		countries.put("Canada", "CANADA");
		return countries;
	}
	public void saveobject(SimDetails obj )
	{
		mapper.save(obj);
	}
	
	public SimDetails loadObject(Class<SimDetails> class1,String hashkey,String rangekey)
	{
		SimDetails sim=mapper.load(class1,hashkey,rangekey);
		return sim;
	}
}
