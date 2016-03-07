package com.tek.simTrade.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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

	@RequestMapping(value = "/enter-user-details", method = RequestMethod.GET)
	@ResponseBody

	public ModelAndView UserPerson() {

		User sim = new User();

		return new ModelAndView("EnterUserDetails", "user-entity", sim);

	}

	private String currentUser;
	private String currentUserPractice;
	private String currentUserId;

	/*@RequestMapping(value = "/enter-country", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView countryUser(@ModelAttribute User userDetails) {

		ModelAndView mav = new ModelAndView("ChooseCountry");

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

	}*/

	

	@RequestMapping(value = "/display-sims", method = RequestMethod.GET)
	@ResponseBody
	public Object hello(@ModelAttribute SimDetails simfulldetails) {
		List<SimDetails> lsim = simDetailsService.displayDetails("US");
		ArrayList<Map<String, String>> lofmap = new ArrayList<>();
		for (int i = 0; i < lsim.size(); i++) {
			Map<String, String> str = new HashMap<>();
			String s=lsim.get(i).getCurrentStatus();
			
	
			String a="active";
			if (s==null)
			{
				System.out.println();
			}
			else if(s.equals(a))
			{
			
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
			
			 }
		Collections.sort(lofmap, new Comparator<Map<String, String>>() {
	        public int compare(final Map<String, String> o1, final Map<String, String> o2) {
	            return o1.get("expiryDate").compareTo(o2.get("expiryDate"));
	        }
	    });
		
		Map<String, String> maps1 = lofmap.get(0);
		String username=maps1.get("userName");
		String country=maps1.get("country");
		SimDetails simUse=mapper.load(SimDetails.class, country, username);
		simUse.setCurrentStatus("passive");
		mapper.save(simUse);
		return lofmap;
	}
	@RequestMapping(value = "/booked", method = RequestMethod.POST)
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
}
