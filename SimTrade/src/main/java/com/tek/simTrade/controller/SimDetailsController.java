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

@RestController
@RequestMapping(value="/sim-details/")
public class SimDetailsController
{
	@Autowired
	private SimDetailsService simDetailsService;
	@Autowired
	private DynamoDBMapper mapper;
	
	SimDetails getSimDetails = new SimDetails();
	
	@RequestMapping(value = "/create-sim-trade", method = RequestMethod.GET)
	@ResponseBody
	String createSimTrade()
	{
		simDetailsService.createSimTradeTable();
		return "true";
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	 @ResponseBody
	 public ModelAndView homes()
	 {
	  return new ModelAndView("Home");
	 }
	
/*	
	@RequestMapping(value = "/display-sim-trade", method = RequestMethod.GET)
	@ResponseBody
	Object displaySimDetails()
	{
		return simDetailsService.displayDetails("US");
		
	}
	
	@RequestMapping(value = "/display-table-test", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView hello()
	{
		
	ModelAndView mav = new ModelAndView();
	mav.setViewName("test");
	List<SimDetails> e1=new ArrayList<SimDetails>();
	 e1=simDetailsService.displayDetails("US");
	
	 AttributeValue[] val = new AttributeValue[4];
	  val[0]=e1.get("userName");
	  val[1]=e1.get("country");
	  val[2]=e1.get("expiryDate");
	  val[3]=e1.get("simType");
	  
	  String[] s=new String[4];
			 s[0]= val[0].getS();
			 s[1]= val[1].getS();
			 s[2]= val[2].getS();
			 s[3]= val[3].getS();
	
	 mav.addObject("name", s[0]);
	 mav.addObject("country", s[1]);
	 mav.addObject("edate", s[2]);
	 mav.addObject("simtype", s[3]);
	return mav;
	}
	*/
	

	 @RequestMapping(value="/sim-form",method=RequestMethod.GET) 
	    @ResponseBody
	    public ModelAndView personPage() {  
	    	 ModelAndView mav = new ModelAndView("enter-details");  
	         
		        Map< String, String > countries = new HashMap<String, String>();  
		        countries.put("US", "US");  
		        countries.put("India", "INDIA");  
		        countries.put("Canada", "CANADA");  
		        
		        mav.addObject("countriesMap", countries);  
		        mav.addObject("sim-entity", new SimDetails());  
		          
		        return mav;  
				
	    }  
	 
     
    @RequestMapping(value="/display-sim-details", method=RequestMethod.POST)  
    public ModelAndView processPerson(@ModelAttribute SimDetails simfulldetails) {  
        ModelAndView modelAndView = new ModelAndView();  
        modelAndView.setViewName("display-details");  
          
        modelAndView.addObject("sim", simfulldetails);  
        mapper.save(simfulldetails);
          
        return modelAndView;  
    }     
    
   
  
    @RequestMapping(value = "/book-a-sim", method = RequestMethod.GET)
    @ResponseBody

    public ModelAndView UserPerson() {
     
     User sim =new User();
     /*sim.setEmpId("1234");
       sim.setName("salman");
       sim.setPractice("development");
       sim.setEmpPhoneNumber("122221");
       mapper.save(sim);
       */
     /*  User use=mapper.load(User.class, sim.getPractice(), sim.getEmpId());
       currentUser=use.getName(); 
      */
     
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
   //User use=mapper.load(User.class, userDetails.getPractice());
     User use=mapper.load(User.class, userDetails.getPractice(), userDetails.getEmpId());
     currentUserPractice=use.getPractice();
     currentUser=use.getName();
     currentUserId=use.getEmpId();
     return mav;

    }
    @RequestMapping(value = "/display-table", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView  hello(@ModelAttribute SimDetails simfulldetails) {
     List<SimDetails> lsim=simDetailsService.displayDetails(simfulldetails.getCountry());
     ArrayList<Map<String,String>> lofmap=new ArrayList<>();
     for(int i=0; i <lsim.size();i++)
     {
     Map<String,String> str=new HashMap<>();
     str.put("userName",lsim.get(i).getUserName() );
     str.put("country",lsim.get(i).getCountry() );
     str.put("expiryDate",lsim.get(i).getExpiryDate() );
     str.put("simId",lsim.get(i).getSimId() );
     str.put("simType",lsim.get(i).getSimType() );
     str.put("phoneNumber",lsim.get(i).getPhoneNumber() );
     str.put("plan",lsim.get(i).getPlan() );
     str.put("currentStatus",lsim.get(i).getCurrentStatus() );
     str.put("ownerId",lsim.get(i).getOwnerId() );
     str.put("expectedDateChange",lsim.get(i).getExpectedDateChange() );
     str.put("rechargeDetails",lsim.get(i).getRechargeDetails() );
     str.put("currentUser",lsim.get(i).getCurrentUser());
     str.put("timestamp",lsim.get(i).getTimestamp() );
     lofmap.add(str);
     }
     return new ModelAndView("hello", "simDetails",lofmap);
    }
    
 
    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public Object env(HttpServletRequest request){

       // logger.info("parameter is "+request.getParameter("env"));
  String s3=request.getParameter("env");
  
  
  String[] country=s3.split("_");
  

 SimDetails use=mapper.load(SimDetails.class, country[1], country[0]);

 
  
  use.setCurrentStatus("passive");
  use.setCurrentUser(currentUser);
  mapper.save(use);
  
 
  User simUse=mapper.load(User.class, currentUserPractice, currentUserId);
  simUse.setSimId(use.getSimId());
  mapper.save(simUse);
  return new ModelAndView("booked", "envselection", use);

    }
    
   /* 
    @RequestMapping(value = "/reserve", method = RequestMethod.POST)
    @ResponseBody
    Object book(@ModelAttribute Object simDetails)
    { 
    	 ModelAndView modelAndView = new ModelAndView();  
         modelAndView.setViewName("test");  

           
         modelAndView.addObject("sim", simDetails);  
      
         return modelAndView;  
    }*/
	 
	/*@RequestMapping(value="/country", method=RequestMethod.GET)  
	@ResponseBody 
		public ModelAndView countryPage() {
		
		 ModelAndView mav = new ModelAndView("countrydropdown");  
         
	        Map< String, String > countries = new HashMap<String, String>();  
	        countries.put("US", "US");  
	        countries.put("India", "INDIA");  
	        countries.put("Canada", "CANADA");  
	        
	        mav.addObject("countriesMap", countries);  
	        mav.addObject("simdetails-entity", new SimDetails());  
	          
	        return mav;  
			
    }  
      
    @RequestMapping(value="/country-result",  method=RequestMethod.POST)  
    private ModelAndView processCountry(@ModelAttribute SimDetails simDetails) {  
        ModelAndView mav = new ModelAndView("country-result");  
        mav.addObject("sim", simDetails);   
        return mav;  
    }  
    
    @RequestMapping(value="/country-list-result",  method=RequestMethod.POST)  
    private List<SimDetails> processListCountry(@ModelAttribute SimDetails simDetails) {   
       return simDetailsService.displayDetails(simDetails.getCountry());
        
    } */
    
   
 
    @RequestMapping(value = "/return-a-sim", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView ret()
    {
     return new ModelAndView("returnSim","user-returning", new SimDetails());
      
    }
    
    @RequestMapping(value = "/user-details", method = RequestMethod.POST)
    @ResponseBody
    public SimDetails UserReturning(@ModelAttribute SimDetails userReturnDetails){
      ModelAndView modelAndView = new ModelAndView();  
         //modelAndView.setViewName("userReturnDetails");  
           
         modelAndView.addObject("sim", userReturnDetails);  
         
         
         SimDetails dis = mapper.load(SimDetails.class,userReturnDetails.getCountry(), userReturnDetails.getUserName());
         dis.setCurrentStatus("active");
         dis.setCurrentUser(null);
         
         mapper.save(dis);
         return dis;
    }
    
   
 
}









