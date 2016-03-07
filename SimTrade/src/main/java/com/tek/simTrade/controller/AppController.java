package com.tek.simTrade.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.tek.simTrade.service.AppService;

@RestController
@RequestMapping(value = "/hi/")
public class AppController {
	@Autowired
	private AppService appService;

	@Autowired
	private Environment env;
	Object obj;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	String list() {
		return "Welcome To Tek Sim Trade Application!";
	}

	
	@RequestMapping(value = "/gettable", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView gettable() {
		
		/*ModelAndView mav=new ModelAndView("Home");
		mav.addObject("name", "salman");
		return mav;*/
		HashMap<String, String> map=new HashMap<>();
		map.put("name", "salman");
		map.put("age", "12");
		String projectUrl="http://localhost:8080/home";
		return new ModelAndView("redirect:" + projectUrl);
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView homes() {
		return new ModelAndView("Home");
	}
	@RequestMapping(value = "/popup", method = RequestMethod.GET)
	  @ResponseBody
	  public ModelAndView globes() {
	   return new ModelAndView("popup");
	   }
	/*@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView countryUser(@ModelAttribute User userDetails) {

		ModelAndView mav = new ModelAndView("Home");

		Map<String, String> countries = new HashMap<String, String>();
		countries.put("US", "US");
		countries.put("India", "INDIA");
		countries.put("Canada", "CANADA");
		mav.addObject("countriesMap", countries);
		mav.addObject("simdetails-entity", new SimDetails());
	//	mapper.save(userDetails);
		User use = mapper.load(User.class, userDetails.getPractice(), userDetails.getEmpId());
		currentUserPractice = use.getPractice();
		currentUser = use.getName();
		currentUserId = use.getEmpId();
		return mav;

	}
*/
	@RequestMapping(value="/select-country-angjs",method=RequestMethod.GET) 
    @ResponseBody
    public String countrySelect() {  
    
        // Map< String, String > countries = simDetailsService.countryList(); 
     
         return "salman";  
       
   
 }
	@RequestMapping(value="/example",method=RequestMethod.GET) 
    @ResponseBody
    public String   example(@ModelAttribute Object x,BindingResult result) {  
    
		System.out.println("Value Recd from form : "+x.toString() +"this is it");
		System.out.println("Value Recd from form : "+x);
		obj=x;
		return "hello";
		
		// Map< String, String > countries = simDetailsService.countryList(); 
     
         }
	@RequestMapping(value="/form",method=RequestMethod.GET) 
    @ResponseBody
    public Object   forms() {
		RestTemplate restTemplate = new RestTemplate();
		String s=restTemplate.getForObject("http://localhost:8080/example",String.class);
System.out.println("the value recieved is:"+s);
		return s;
	}
    /*
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	  @ResponseBody
	  public ModelAndView test()
	  {
	   return new ModelAndView("test");
	  }*/
	String a;
    @RequestMapping(value = "/PostFormDataByMap", method = RequestMethod.POST)
    public @ResponseBody String PostFormDataByMap(@RequestBody String obj) {   
           
    a=obj;
    
    
    
    
    
    
    
           return obj;   
    }
    @RequestMapping(value = "/fest", method = RequestMethod.GET)
	  @ResponseBody
	  public String fest()
	  {
	   return a;
	  }
}
