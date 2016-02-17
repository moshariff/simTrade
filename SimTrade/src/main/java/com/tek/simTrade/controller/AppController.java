package com.tek.simTrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tek.simTrade.service.AppService;

@RestController
@RequestMapping()
public class AppController {
	@Autowired
	private AppService appService;

	@Autowired
	private Environment env;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	String list() {
		return "Welcome To Tek Sim Trade Application!";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView homes() {
		return new ModelAndView("Home");
	}

}
