package com.tek.simTrade.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.tek.simTrade.models.SimDetails;

@Service
public class AppService
{
	@Autowired
	private Environment env;

	@Autowired
	private DynamoDBMapper mapper;

	@SuppressWarnings("rawtypes")
	public String getPropertyFileAndProperties()
	{
		StringBuilder builder = new StringBuilder();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Iterator it = ((AbstractEnvironment) env).getPropertySources()
				.iterator(); it.hasNext();)
		{
			PropertySource propertySource = (PropertySource) it.next();
			if (propertySource instanceof ResourcePropertySource)
			{
				String propertySourceName = propertySource.getName();
				builder.append(propertySourceName);
				builder.append(" <br/>");
				map.putAll(((MapPropertySource) propertySource).getSource());
				builder.append(
						((MapPropertySource) propertySource).getSource());
				builder.append(" <br/>");
			}
		}

		return builder.toString();
	}
	
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
