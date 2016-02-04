package com.tek.simTrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tek.simTrade.service.SimDetailsService;

@RestController
@RequestMapping(value="/sim-details/")
public class SimDetailsController
{
	@Autowired
	private SimDetailsService simDetailsService;
	
	@RequestMapping(value = "/create-sim-trade", method = RequestMethod.GET)
	@ResponseBody
	String createSimTrade()
	{
		simDetailsService.createSimTradeTable();
		return "true";
	}
}
