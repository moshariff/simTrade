package com.tek.simTrade.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "users")
public class User
{
	@DynamoDBRangeKey(attributeName = "empId")
	private String empId;

	@DynamoDBHashKey(attributeName = "practice")
	private String practice;

	@DynamoDBAttribute(attributeName = "name")
	private String name;

	@DynamoDBAttribute(attributeName = "empPhoneNumber")
	private String empPhoneNumber;

	@DynamoDBAttribute(attributeName = "email")
	private String email;

	@DynamoDBAttribute(attributeName = "dateOfJoining")
	private String dateOfJoining;

	@DynamoDBAttribute(attributeName = "timestamp")
	private String timestamp;

	public String getEmpId()
	{
		return empId;
	}

	public void setEmpId(String empId)
	{
		this.empId = empId;
	}

	public String getPractice()
	{
		return practice;
	}

	public void setPractice(String practice)
	{
		this.practice = practice;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmpPhoneNumber()
	{
		return empPhoneNumber;
	}

	public void setEmpPhoneNumber(String empPhoneNumber)
	{
		this.empPhoneNumber = empPhoneNumber;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getDateOfJoining()
	{
		return dateOfJoining;
	}

	public void setDateOfJoining(String dateOfJoining)
	{
		this.dateOfJoining = dateOfJoining;
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
