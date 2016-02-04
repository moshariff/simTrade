package com.tek.simTrade.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.tek.simTrade.models.SimDetails;

@Service
public class AppService
{

	@Autowired
	private DynamoDBMapper mapper;

	public List<SimDetails> getAllSimDetails()
	{
		Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":country", new AttributeValue().withS("US"));

		DynamoDBQueryExpression<SimDetails> queryExpression = new DynamoDBQueryExpression<SimDetails>()
				.withKeyConditionExpression("country = :country")
				.withExpressionAttributeValues(eav);

		List<SimDetails> simDetails = mapper.query(SimDetails.class,
				queryExpression);

		return simDetails;
	}

}
