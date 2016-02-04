package com.tek.simTrade.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({ @PropertySource("classpath:./properties/env.properties"),
		@PropertySource("classpath:./properties/application.properties"),
		@PropertySource("classpath:./properties/${spring.profiles.active}.properties") })
public class WebConfig
{

}
