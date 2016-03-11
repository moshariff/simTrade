package com.tek.simTrade.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "usersTableRemote")
public class UsersNew
{

	
	@DynamoDBHashKey(attributeName = "userName")
	private String userName;

	@DynamoDBRangeKey(attributeName = "email")
	private String email;

	@DynamoDBAttribute(attributeName = "simPhoneNumber")
	private String simPhoneNumber;

	@DynamoDBAttribute(attributeName = "timestamp")
	private String timestamp;

	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getSimPhoneNumber()
	{
		return simPhoneNumber;
	}

	public void setSimPhoneNumber(String simPhoneNumber)
	{
		this.simPhoneNumber = simPhoneNumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(String timestamp)
	{
		this.timestamp = timestamp;
	}

}
