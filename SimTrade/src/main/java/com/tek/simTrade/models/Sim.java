package com.tek.simTrade.models;

import javax.validation.constraints.NotNull;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "simTable")
public class Sim
{

	

	@DynamoDBHashKey(attributeName = "country")
	private String country;

//	@DynamoDBIndexRangeKey(attributeName = "expiryDate", globalSecondaryIndexName="expiryDate", localSecondaryIndexName = "sort_by_expiryDate")
	@DynamoDBRangeKey(attributeName = "phoneNumber")
	@NotNull
	private String phoneNumber;
	
	@DynamoDBAttribute(attributeName = "simType")
	private String simType;

	@DynamoDBAttribute(attributeName = "expiryDate")
	private String expiryDate;

	@DynamoDBAttribute(attributeName = "plan")
	private String plan;

	@DynamoDBAttribute(attributeName = "currentStatus")
	private String currentStatus;

	@DynamoDBAttribute(attributeName = "currentUser")
	private String currentUser;

	@DynamoDBAttribute(attributeName = "timestamp")
	private String timestamp;
	
	

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getExpiryDate()
	{
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate)
	{
		this.expiryDate = expiryDate;
	}

	public String getSimType()
	{
		return simType;
	}

	public void setSimType(String simType)
	{
		this.simType = simType;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public String getPlan()
	{
		return plan;
	}

	public void setPlan(String plan)
	{
		this.plan = plan;
	}

	public String getCurrentStatus()
	{
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus)
	{
		this.currentStatus = currentStatus;
	}


	public String getCurrentUser()
	{
		return currentUser;
	}

	public void setCurrentUser(String currentUser)
	{
		this.currentUser = currentUser;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString()
	{
		return "SimDetails [ country=" + country
				+ ", expiryDate=" + expiryDate + ", simType=" + simType
				+ ", phoneNumber=" + phoneNumber + ", plan=" + plan
				+ ", currentStatus=" + currentStatus
				+ ", currentUser="	+ currentUser + ", timestamp=" + timestamp + "]";
	}

}
