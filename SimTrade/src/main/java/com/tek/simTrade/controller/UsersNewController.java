/*package com.tek.simTrade.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class UsersNewController
{
	@Autowired
	private UsersNewService usersService;
	@Autowired
	private DynamoDBMapper mapper;
	
	
	 * creates a model/User table
	 
	@RequestMapping(value = "/create-users", method = RequestMethod.GET)
	@ResponseBody
	String createUser()
	{
		usersService.createUserTable();
		return "User Table Created";
	}
	
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
				lusers.get(i).setSimPhoneNumber("null");
				// save the changes
				mapper.save(lusers.get(i));
			}
		}
		// return home page
		
		String url="http://localhost:8080/worldWeb";
		return new ModelAndView("redirect:" +url);}
   
}
*/