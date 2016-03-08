package com.tek.simTrade.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tek.simTrade.models.Sim;
import com.tek.simTrade.models.UsersNew;

import com.tek.simTrade.service.UsersNewService;
@RestController
@RequestMapping(value="/")
public class depositSim {
	
	
	@Autowired
	private UsersNewService usersService;
	
	/*
	 * The DynamoDBMapper class is the entry point to DynamoDB. It provides
	 * access to a DynamoDB endpoint and enables you to access your data in
	 * various tables, perform various CRUD operations on items, and execute
	 * queries and scans against tables.
	 */
	@Autowired
	private DynamoDBMapper mapper;
	/*
	 * creates a model/User table
	 */
	@RequestMapping(value = "/create-users", method = RequestMethod.GET)
	@ResponseBody
	String createUser()
	{
		usersService.createUserTable();
		return "User Table Created";
	}
	
	/*
	 * This controller mapping is fired when the user adds a sim into the
	 * application. It verifies if the sim already exists by comparing the
	 * incoming sims phone number with the existing record of sim's phone
	 * numbers, if it exists, It sets the particular users phone number field to
	 * null indicating that he returned the sim
	 */
	@RequestMapping(value = "/display-sim-details", method = RequestMethod.POST)
	public Object env(HttpServletRequest request, @ModelAttribute Sim sim) {

		mapper.save(sim);
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		// scans the users table to return list of users
		List<UsersNew> lusers = mapper.scan(UsersNew.class, scanExpression);
		// phone number submiited in the form
		String simPhoneNumber = sim.getPhoneNumber();
		// loop through each user
		for (int i = 0; i < lusers.size(); i++) {
			// sim phone number used by each user
			String userPhoneNumber = lusers.get(i).getSimPhoneNumber();

			if (userPhoneNumber == null) {

			}
			// if both numbers match, the sim is being returned
			else if (userPhoneNumber.equals(simPhoneNumber)) {
				// free the user's phoneNumber field indicating he returned
				lusers.get(i).setSimPhoneNumber(null);
				// save the changes
				mapper.save(lusers.get(i));
			}
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url="http://localhost:8080/worldWeb";
		return new ModelAndView("redirect:" +url);
		}
	
	


}
