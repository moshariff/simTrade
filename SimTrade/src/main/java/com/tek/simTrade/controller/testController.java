package com.tek.simTrade.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tek.simTrade.models.Sim;
import com.tek.simTrade.models.UsersNew;
import com.tek.simTrade.service.SimService;

@RestController
@RequestMapping(value = "/")

public class testController {

	// Service class

	@Autowired
	private SimService simService;

	/*
	 * The DynamoDBMapper class is the entry point to DynamoDB. It provides
	 * access to a DynamoDB endpoint and enables you to access your data in
	 * various tables, perform various CRUD operations on items, and execute
	 * queries and scans against tables.
	 */
	@Autowired
	private DynamoDBMapper mapper;

	// Testing Purpose

	@RequestMapping(value = "/display-sim-object", method = RequestMethod.GET)
	public Object scan() {

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

		List<Sim> sim = mapper.scan(Sim.class, scanExpression);
		return sim;
	}

	// Testing Purpose

	@RequestMapping(value = "/display-user-object", method = RequestMethod.GET)
	public Object scan1() {

		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

		List<UsersNew> usersNew = mapper.scan(UsersNew.class, scanExpression);
		return usersNew;
	}

	// Testing Purpose

	@RequestMapping(value = "/display-sims-US", method = RequestMethod.GET)

	@ResponseBody
	public Object displayUs(@ModelAttribute Sim simfulldetails) {
		List<Sim> lsim = simService.displayDetails("US");
		ArrayList<Map<String, String>> lofmap = new ArrayList<>();

		for (int i = 0; i < lsim.size(); i++) {
			Map<String, String> str = new HashMap<>();
			String s = lsim.get(i).getCurrentStatus();

			String a = "active";
			if (s == null) {
				System.out.println();
			} else if (s.equals(a)) {

				str.put("country", lsim.get(i).getCountry());
				str.put("expiryDate", lsim.get(i).getExpiryDate());
				str.put("simType", lsim.get(i).getSimType());
				str.put("phoneNumber", lsim.get(i).getPhoneNumber());
				str.put("plan", lsim.get(i).getPlan());
				str.put("currentStatus", lsim.get(i).getCurrentStatus());
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

		return lofmap;
	}

	// Testing Purpose
	@RequestMapping(value = "/display-sims-canada", method = RequestMethod.GET)

	@ResponseBody
	public Object displaySimCanada(@ModelAttribute Sim simfulldetails) {
		List<Sim> lsim = simService.displayDetails("CANADA");
		ArrayList<Map<String, String>> lofmap = new ArrayList<>();

		for (int i = 0; i < lsim.size(); i++) {
			Map<String, String> str = new HashMap<>();
			String s = lsim.get(i).getCurrentStatus();

			String a = "active";
			if (s == null) {
			} else if (s.equals(a)) {

				str.put("country", lsim.get(i).getCountry());
				str.put("expiryDate", lsim.get(i).getExpiryDate());
				str.put("simType", lsim.get(i).getSimType());
				str.put("phoneNumber", lsim.get(i).getPhoneNumber());
				str.put("plan", lsim.get(i).getPlan());
				str.put("currentStatus", lsim.get(i).getCurrentStatus());
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

		return lofmap;
	}
	@RequestMapping(value = "/testpost", method = RequestMethod.POST)
	public @ResponseBody void testpost(@RequestBody String obj) {

				System.out.println(obj);
	}
	@RequestMapping(value = "/testing", method = RequestMethod.GET)
	@ResponseBody
	public  String  testing() {
		return "redirect:worldWeb";
				}
}