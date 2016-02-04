package com.tek.simTrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tek.simTrade.service.UserService;

@RestController
@RequestMapping(value="/user/")
public class UserController
{
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/create-user", method = RequestMethod.GET)
	@ResponseBody
	String createUser()
	{
		userService.createUserTable();
		return "true";
	}
}
