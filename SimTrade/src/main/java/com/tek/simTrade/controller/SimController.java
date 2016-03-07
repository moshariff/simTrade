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
@RequestMapping(value="/")




public class SimController
{
	@Autowired
	private SimService simService;

	
	@Autowired
	private DynamoDBMapper mapper;
	

	
	@RequestMapping(value = "/create-sim", method = RequestMethod.GET)
	@ResponseBody
	String createSimTrade()
	{
		simService.createSimTradeTable();
		return "Sim Table Created";
	}
	
	
	@RequestMapping(value = "/worldWeb", method = RequestMethod.GET)
	  @ResponseBody
	  public ModelAndView worldWeb() {
	   ModelAndView mav = new ModelAndView("newWorldWeb");
	 
	   mav.addObject("sim-entity", new Sim()); 
	   mav.addObject("user-entity", new UsersNew());
	   return mav;
	   }
	
	 @RequestMapping(value="/display-sim-details", method=RequestMethod.POST)  
	    
		 public Object env(HttpServletRequest request, @ModelAttribute Sim sim){
	          mapper.save(sim);
	          
	          
	          DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
	            
	  		List<UsersNew> lsim = mapper.scan(UsersNew.class, scanExpression );	  
	          
	  
	    	  ArrayList<Map<String, String>> lofmap = new ArrayList<>();
	    	  for (int i = 0; i < lsim.size(); i++) {
	    	   Map<String, String> str = new HashMap<>();
	    	   String userPhoneNumber =lsim.get(i).getSimPhoneNumber();
	    	   String simPhoneNumber =sim.getPhoneNumber();
	    	  
	    	  
	    	  
	    	   
	    	   if (userPhoneNumber==null)
	    	   {
	    	    System.out.println();
	    	   }
	    	   else if(userPhoneNumber.equals(simPhoneNumber))
	    	   {
	    		   lsim.get(i).setSimPhoneNumber("null");
	    		   mapper.save(lsim.get(i));
	    	   }
	    	  }
	          
	          String projectUrl="http://localhost:8080/worldWeb";
	          try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	          return new ModelAndView("redirect:" + projectUrl);
	        
	      //  return sim;
	    }  
	    
	 @RequestMapping(value="/display-user-details", method=RequestMethod.POST)  
		 public Object env1(HttpServletRequest request, @ModelAttribute UsersNew usersNew,  @RequestParam String country){  
        System.out.println("bro the country is :" +country);  
          List<Sim> lsim = simService.displayDetails(country);
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
    	  
    	  Map<String, String> maps1 = lofmap.get(0);
    	  String phoneNumber=maps1.get("phoneNumber");
    	
    	  Sim simUse=mapper.load(Sim.class, country, phoneNumber);
    	  simUse.setCurrentStatus("passive");
    	  simUse.setCurrentUser(usersNew.getUserName());
    	  
    	  mapper.save(simUse);
    	  usersNew.setSimPhoneNumber(phoneNumber);
    	  mapper.save(usersNew);
    	  
    	  try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          String projectUrl="http://localhost:8080/worldWeb";
          return new ModelAndView("redirect:" + projectUrl);
		 
		
        
        
    }  
	 
	 @RequestMapping(value="/display-sim-object", method=RequestMethod.GET)  
	    public Object scan() {  
	       
		 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		            
		List<Sim> sim = mapper.scan(Sim.class, scanExpression );	       
		 return sim;  
	    }     
	
	 @RequestMapping(value="/display-user-object", method=RequestMethod.GET)  
	    public Object scan1() {  
	       
		 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
		            
		List<UsersNew> usersNew = mapper.scan(UsersNew.class, scanExpression );	       
		 return usersNew;  
	    }  
	 
	 
	 @RequestMapping(value = "/display-sims", method = RequestMethod.GET)
	 @ResponseBody
	 public Object hello(@ModelAttribute Sim simfulldetails) {
	  List<Sim> lsim = simService.displayDetails("CANADA");
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
	  
	  /*Map<String, String> maps1 = lofmap.get(0);
	  String phoneNumber=maps1.get("phoneNumber");
	  String country=maps1.get("country");
	  Sim simUse=mapper.load(Sim.class, country, phoneNumber);
	  simUse.setCurrentStatus("passive");
	  mapper.save(simUse);*/
	  return lofmap;
	 }
	 String th;
	 int l;
	 @RequestMapping(value = "/checkAvailable", method = RequestMethod.POST)
	 public @ResponseBody void checkAvailable(@RequestBody String obj) {
		
		 obj=obj.substring(0,obj.length()-1);
		 System.out.println("country in check Available is: "+obj);
	  List<Sim> lsim = simService.displayDetails(obj);
	  int no=0;
	  for (int i = 0; i < lsim.size(); i++) {
		  String s=lsim.get(i).getCurrentStatus();
		   
			 
		   String a="active";
		   if (s==null)
		   {
		    System.out.println();
		   }
		   else if(s.equals(a))
		   {
			   System.out.println("loop once");
			   no++;
		   }
	   
	  
	  	 }
	  
	  l=no;
	 }
	 
	 @RequestMapping(value="/replyavailable", method=RequestMethod.GET)  
	 @ResponseBody  
	 public String replyAvailable() { 
		 System.out.println("length:" +l +"country is: " +th);
	 if (l==0)
		    return "empty";
		  else
			  return "available";
	 }
	 
	 
	 boolean bool=false;
	 @RequestMapping(value = "/checkName", method = RequestMethod.POST)
	 public @ResponseBody void checkName(@RequestBody String obj) {
		
		 obj=obj.substring(0,obj.length()-1);
		 obj=obj.replace('+', ' ');
		 DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
         
			List<UsersNew> Usim = mapper.scan(UsersNew.class, scanExpression );
			for(int i=0;i<Usim.size();i++)
			{
				String username=Usim.get(i).getUserName();
				System.out.println("username is:" +username);
				if(obj.equals(username))
				{
					System.out.println("entered true");
					bool=true;
					
				}
			}
	  
	 }
	 
	 @RequestMapping(value="/replyname", method=RequestMethod.GET)  
	 @ResponseBody  
	 public String replyname() { 
		 System.out.println("boolean value is: " +bool);
	 if (bool==true)
		    {bool=false;
		 return "exists";
		    
		    }
		  else{
			  return "goAhead";
	 }}
}









