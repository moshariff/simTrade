package com.tek.simTrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.tek.simTrade.service.UsersNewService;

@RestController
@RequestMapping(value="/users/")
public class UsersNewController
{
	@Autowired
	private UsersNewService usersService;
	@Autowired
	private DynamoDBMapper mapper;
	
	@RequestMapping(value = "/create-users", method = RequestMethod.GET)
	@ResponseBody
	String createUser()
	{
		usersService.createUserTable();
		return "User Table Created";
	}
	

   
}
