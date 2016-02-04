package com.tek.simTrade.models;

import javax.validation.constraints.NotNull;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "simDetails")
public class SimDetails
{

	@DynamoDBRangeKey(attributeName = "userName")
	private String userName;

	@DynamoDBHashKey(attributeName = "country")
	private String country;

//	@DynamoDBIndexRangeKey(attributeName = "expiryDate", globalSecondaryIndexName="expiryDate", localSecondaryIndexName = "sort_by_expiryDate")
	@DynamoDBIndexRangeKey(attributeName = "expiryDate", localSecondaryIndexName = "sort_by_expiryDate")
	@NotNull
	private String expiryDate;

	@DynamoDBAttribute(attributeName = "simType")
	private String simType;

	@DynamoDBAttribute(attributeName = "phoneNumber")
	private String phoneNumber;

	@DynamoDBAttribute(attributeName = "plan")
	private String plan;

	@DynamoDBAttribute(attributeName = "currentStatus")
	private String currentStatus;

	@DynamoDBAttribute(attributeName = "ownerId")
	private String ownerId;

	@DynamoDBAttribute(attributeName = "expectedDateChange")
	private String expectedDateChange;

	@DynamoDBAttribute(attributeName = "rechargeDetails")
	private String rechargeDetails;

	@DynamoDBAttribute(attributeName = "currentUser")
	private String currentUser;

	@DynamoDBAttribute(attributeName = "timestamp")
	private String timestamp;

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

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

	public String getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(String ownerId)
	{
		this.ownerId = ownerId;
	}

	public String getExpectedDateChange()
	{
		return expectedDateChange;
	}

	public void setExpectedDateChange(String expectedDateChange)
	{
		this.expectedDateChange = expectedDateChange;
	}

	public String getRechargeDetails()
	{
		return rechargeDetails;
	}

	public void setRechargeDetails(String rechargeDetails)
	{
		this.rechargeDetails = rechargeDetails;
	}

	public String getCurrentUser()
	{
		return currentUser;
	}

	public void setCurrentUser(String currentUser)
	{
		this.currentUser = currentUser;
	}

	public String getTimestamp()
	{
		return timestamp;
	}

	public void setTimestamp(String timestamp)
	{
		this.timestamp = timestamp;
	}

	@Override
	public String toString()
	{
		return "SimDetails [userName=" + userName + ", country=" + country
				+ ", expiryDate=" + expiryDate + ", simType=" + simType
				+ ", phoneNumber=" + phoneNumber + ", plan=" + plan
				+ ", currentStatus=" + currentStatus + ", ownerId=" + ownerId
				+ ", expectedDateChange=" + expectedDateChange
				+ ", rechargeDetails=" + rechargeDetails + ", currentUser="
				+ currentUser + ", timestamp=" + timestamp + "]";
	}

}
