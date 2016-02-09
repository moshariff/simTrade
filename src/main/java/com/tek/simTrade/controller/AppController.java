package com.tek.simTrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tek.simTrade.models.SimDetails;
import com.tek.simTrade.service.AppService;

@RestController
public class AppController
{
	@Autowired
	private AppService appService;

	@Autowired
	private Environment env;

	@RequestMapping("/properties-list")
	@ResponseBody
	String propertiesList()
	{
		return appService.getPropertyFileAndProperties();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	String home()
	{
		return "Welcome To Tek Sim Trade Application!";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	String list()
	{
		List<SimDetails> simNos = appService.getAllSimDetails();
		return simNos.toString();
	}

}
