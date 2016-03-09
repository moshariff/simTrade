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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.tek.simTrade.models.Sim;

import com.tek.simTrade.models.UsersNew;
import com.tek.simTrade.service.SimService;

@RestController
@RequestMapping(value = "/")

public class SimController {

	/*
	 * Service class
	 */
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

	/*
	 * Creates the table Models/Sim
	 */
	@RequestMapping(value = "/create-sim", method = RequestMethod.GET)
	@ResponseBody
	String createSimTrade() {
		simService.createSimTradeTable();
		return "Sim Table Created";
	}

	/*
	 * Fires up the home page or the only page in our application
	 */
	@RequestMapping(value = "/worldWeb", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView worldWeb() {
		ModelAndView mav = new ModelAndView("newWorldWeb");

		mav.addObject("sim-entity", new Sim());
		mav.addObject("user-entity", new UsersNew());
		return mav;
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
				lusers.get(i).setSimPhoneNumber("");
				// save the changes
				mapper.save(lusers.get(i));
			}
		}
		// return home page
		return new ModelAndView("newWorldWeb");
	}

	/*
	 * This Controller mapping is called when the user books a Sim. The input to
	 * this controller is the model/user object that the user inputs through a
	 * form and returns the home page. It assigns the user a sim of the country
	 * he selected, which has the earliest expiryDate It also assigns the
	 * UserName to the sim object, indicating booked
	 */
	@RequestMapping(value = "/display-user-details", method = RequestMethod.POST)
	public Object env1(HttpServletRequest request, @ModelAttribute UsersNew usersNew, @RequestParam String country) {
		// call to service class to scan the Sim table and return list of
		// sims(lsim).
		List<Sim> lsim = simService.displayDetails(country);
		// convert list of sims to list of maps each map indicates a sim
		ArrayList<Map<String, String>> lofmap = new ArrayList<>();
		// loop through sims
		for (int i = 0; i < lsim.size(); i++) {
			Map<String, String> str = new HashMap<>();
			String s = lsim.get(i).getCurrentStatus();
			String a = "active";
			if (s == null) {
				System.out.println();

			}
			// load only active Sims in a map(lofmap)
			else if (s.equals(a)) {

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
		// sort the list of active sims by expiryDate
		Collections.sort(lofmap, new Comparator<Map<String, String>>() {
			public int compare(final Map<String, String> o1, final Map<String, String> o2) {
				return o1.get("expiryDate").compareTo(o2.get("expiryDate"));
			}
		});

		Map<String, String> maps1 = lofmap.get(0);
		String phoneNumber = maps1.get("phoneNumber");
		// load the first Sim, indicating least expiryDate.
		Sim simUse = mapper.load(Sim.class, country, phoneNumber);
		// book it by changing status to passive.
		simUse.setCurrentStatus("passive");
		// set the CurrenUser Field of the Sim to the userName entered
		simUse.setCurrentUser(usersNew.getUserName());
		// save the sim
		mapper.save(simUse);
		// in the user record assign him a sim by setting his SimphoneNumber
		// Field to alloted sim's number.
		usersNew.setSimPhoneNumber(phoneNumber);
		// save the user
		mapper.save(usersNew);

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("newWorldWeb");
	}

	// global counter for communication between controllers
	int counter;

	/*
	 * This controller mapping checks if sims are available by the country
	 * passed
	 */
	@RequestMapping(value = "/checkAvailable", method = RequestMethod.POST)
	public @ResponseBody void checkAvailable(@RequestBody String country) {

		country = country.substring(0, country.length() - 1);
		// scan the table of Sims to return a list of Sims
		List<Sim> lsim = simService.displayDetails(country);
		// count to maintain the number of active sims
		int count = 0;
		// loop through sims
		for (int i = 0; i < lsim.size(); i++) {

			String s = lsim.get(i).getCurrentStatus();
			String a = "active";
			if (s == null) {
				// do nothing
			}
			// if active increment count
			else if (s.equals(a)) {
				count++;
			}
		}
		counter = count;
	}

	/*
	 * This controller returns to the front End if Sims are available or not
	 * depending on the count in previous mapping
	 */
	@RequestMapping(value = "/replyavailable", method = RequestMethod.GET)
	@ResponseBody
	public String replyAvailable() {

		if (counter == 0)
			return "empty";
		else
			return "available";
	}

	// Boolean variable true if the user exists and false if not
	boolean userExist = false;

	/*
	 * This controller checks when the user is booking a sim, if he already has
	 * booked before if he has then returns true
	 */
	@RequestMapping(value = "/checkName", method = RequestMethod.POST)
	public @ResponseBody void checkName(@RequestBody String name) {
		
		name = name.substring(0, name.length() - 1);
		name = name.replace('+', ' ');
		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		// scans user table to return list of users(lUser)
		List<UsersNew> lUser = mapper.scan(UsersNew.class, scanExpression);
		// loop through users
		for (int i = 0; i < lUser.size(); i++) {
			String username = lUser.get(i).getUserName();
			
			if (name.equals(username)) {
				
				
				if (lUser.get(i).getSimPhoneNumber() != null) {
					System.out.println(lUser.get(i).getSimPhoneNumber());
					System.out.println(lUser.get(i).getUserName());
					userExist = true;
				}
				
			}
			
		}

	}

	/*
	 * This controller mapping communicates to front end if user already exists
	 */
	@RequestMapping(value = "/replyname", method = RequestMethod.GET)
	@ResponseBody
	public String replyname() {
		
		if (userExist == true) {
			userExist = false;
			return "exists";

		} else {
			return "goAhead";
		}
	}




 
  @RequestMapping(value = "/display-sim-object", method = RequestMethod.GET)
  public Object scan() {

 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

 List<Sim> sim = mapper.scan(Sim.class, scanExpression); 
 return sim;

 

  }




  @RequestMapping(value = "/display-user-object", method = RequestMethod.GET)
  public Object scan1() {
  
  DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
  
List<UsersNew> usersNew = mapper.scan(UsersNew.class, scanExpression);
return
 usersNew; 
}
 



 
  @RequestMapping(value = "/display-sims", method = RequestMethod.GET)

  @ResponseBody public Object hello(@ModelAttribute Sim simfulldetails) {
 List<Sim> lsim = simService.displayDetails("US"); ArrayList<Map<String,
 String>> lofmap = new ArrayList<>(); for (int i = 0; i < lsim.size(); i++) {
  Map<String, String> str = new HashMap<>(); String s =
  lsim.get(i).getCurrentStatus();
  
  String a = "active"; if (s == null) { System.out.println(); } else if
  (s.equals(a)) {
  
  str.put("country", lsim.get(i).getCountry()); str.put("expiryDate",
  lsim.get(i).getExpiryDate()); str.put("simType", lsim.get(i).getSimType());
  str.put("phoneNumber", lsim.get(i).getPhoneNumber()); str.put("plan",
  lsim.get(i).getPlan()); str.put("currentStatus",
  lsim.get(i).getCurrentStatus()); str.put("currentUser",
  lsim.get(i).getCurrentUser()); str.put("timestamp",
  lsim.get(i).getTimestamp()); lofmap.add(str); }
  
  } 
 
 Collections.sort(lofmap, new Comparator<Map<String, String>>() { public int
  compare(final Map<String, String> o1, final Map<String, String> o2) { return
  o1.get("expiryDate").compareTo(o2.get("expiryDate")); } });
  
  
  Map<String, String> maps1 = lofmap.get(0); String
  phoneNumber=maps1.get("phoneNumber"); String country=maps1.get("country");
  Sim simUse=mapper.load(Sim.class, country, phoneNumber);
  simUse.setCurrentStatus("passive"); mapper.save(simUse);
  
  return lofmap; }
  }
