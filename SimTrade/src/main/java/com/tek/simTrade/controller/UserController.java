package com.tek.simTrade.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.tek.simTrade.models.SimDetails;
import com.tek.simTrade.models.User;
import com.tek.simTrade.service.SimDetailsService;
import com.tek.simTrade.service.UserService;

@RestController
@RequestMapping(value = "/user/")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private DynamoDBMapper mapper;
	@Autowired
	private SimDetailsService simDetailsService;

	@RequestMapping(value = "/create-user", method = RequestMethod.GET)
	@ResponseBody
	String createUser() {
		userService.createUserTable();
		return "true";
	}

	@RequestMapping(value = "/book-a-sim", method = RequestMethod.GET)
	@ResponseBody

	public ModelAndView UserPerson() {

		User sim = new User();

		return new ModelAndView("SimBook", "user-entity", sim);

	}

	private String currentUser;
	private String currentUserPractice;
	private String currentUserId;

	@RequestMapping(value = "/user-country", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView countryUser(@ModelAttribute User userDetails) {

		ModelAndView mav = new ModelAndView("countrydropdown");

		Map<String, String> countries = new HashMap<String, String>();
		countries.put("US", "US");
		countries.put("India", "INDIA");
		countries.put("Canada", "CANADA");
		mav.addObject("countriesMap", countries);
		mav.addObject("simdetails-entity", new SimDetails());
		mapper.save(userDetails);
		User use = mapper.load(User.class, userDetails.getPractice(), userDetails.getEmpId());
		currentUserPractice = use.getPractice();
		currentUser = use.getName();
		currentUserId = use.getEmpId();
		return mav;

	}

	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public Object env(HttpServletRequest request) {

		String s3 = request.getParameter("env");

		String[] country = s3.split("_");

		SimDetails use = mapper.load(SimDetails.class, country[1], country[0]);

		use.setCurrentStatus("passive");
		use.setCurrentUser(currentUser);
		mapper.save(use);

		User simUse = mapper.load(User.class, currentUserPractice, currentUserId);
		simUse.setSimId(use.getSimId());
		mapper.save(simUse);
		return new ModelAndView("booked", "envselection", use);

	}

	@RequestMapping(value = "/display-table", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView hello(@ModelAttribute SimDetails simfulldetails) {
		List<SimDetails> lsim = simDetailsService.displayDetails(simfulldetails.getCountry());
		ArrayList<Map<String, String>> lofmap = new ArrayList<>();
		for (int i = 0; i < lsim.size(); i++) {
			Map<String, String> str = new HashMap<>();
			str.put("userName", lsim.get(i).getUserName());
			str.put("country", lsim.get(i).getCountry());
			str.put("expiryDate", lsim.get(i).getExpiryDate());
			str.put("simId", lsim.get(i).getSimId());
			str.put("simType", lsim.get(i).getSimType());
			str.put("phoneNumber", lsim.get(i).getPhoneNumber());
			str.put("plan", lsim.get(i).getPlan());
			str.put("currentStatus", lsim.get(i).getCurrentStatus());
			str.put("ownerId", lsim.get(i).getOwnerId());
			str.put("expectedDateChange", lsim.get(i).getExpectedDateChange());
			str.put("rechargeDetails", lsim.get(i).getRechargeDetails());
			str.put("currentUser", lsim.get(i).getCurrentUser());
			str.put("timestamp", lsim.get(i).getTimestamp());
			lofmap.add(str);
		}
		return new ModelAndView("hello", "simDetails", lofmap);
	}
}
