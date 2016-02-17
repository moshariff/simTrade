package com.tek.simTrade.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.tek.simTrade.models.SimDetails;
import com.tek.simTrade.service.SimDetailsService;

@RestController
@RequestMapping(value = "/sim-details/")
public class SimDetailsController {
	@Autowired
	private SimDetailsService simDetailsService;
	@Autowired
	private DynamoDBMapper mapper;

	SimDetails getSimDetails = new SimDetails();

	@RequestMapping(value = "/create-sim-trade", method = RequestMethod.GET)
	@ResponseBody
	String createSimTrade() {
		simDetailsService.createSimTradeTable();
		return "true";
	}

	@RequestMapping(value = "/enter-new-sim-details", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView personPage() {
		ModelAndView mav = new ModelAndView("EnterNewSimDetails");

		Map<String, String> countries = new HashMap<String, String>();
		countries.put("US", "US");
		countries.put("India", "INDIA");
		countries.put("Canada", "CANADA");

		mav.addObject("countriesMap", countries);
		mav.addObject("sim-entity", new SimDetails());

		return mav;

	}

	@RequestMapping(value = "/enter-return-sim-details", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView ret() {
		return new ModelAndView("EnterReturnSimDetails", "user-returning", new SimDetails());

	}

	@RequestMapping(value = "/display-new-sim-details", method = RequestMethod.POST)
	public ModelAndView processPerson(@ModelAttribute SimDetails simfulldetails) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("display-details");

		modelAndView.addObject("sim", simfulldetails);
		mapper.save(simfulldetails);

		return modelAndView;
	}

	@RequestMapping(value = "/deposited", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView UserReturning(@ModelAttribute SimDetails userReturnDetails) {
		ModelAndView modelAndView = new ModelAndView("ReactivateSim");

		modelAndView.addObject("sim", userReturnDetails);

		SimDetails dis = mapper.load(SimDetails.class, userReturnDetails.getCountry(), userReturnDetails.getUserName());
		dis.setCurrentStatus("active");
		dis.setCurrentUser(null);

		mapper.save(dis);
		return modelAndView;
	}
}
