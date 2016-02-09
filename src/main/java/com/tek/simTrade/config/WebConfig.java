package com.tek.simTrade.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Configuration
@PropertySources({ @PropertySource("classpath:./properties/env.properties"),
		@PropertySource("classpath:./properties/application.properties"),
		@PropertySource("classpath:./properties/${spring.profiles.active}.properties") })
public class WebConfig extends WebMvcConfigurerAdapter {
	@Bean
	TilesViewResolver viewResolver() {
		TilesViewResolver viewResolver = new TilesViewResolver();
		return viewResolver;
	}

	@Bean
	TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		String[] s1 = { "tiles.xml", "WEB-INF/views/jsp/tiles/tiles.xml", "WEB-INF/views/jsp/tiles/products.xml" };
		tilesConfigurer.setDefinitions(s1);
		tilesConfigurer.setCheckRefresh(true);
//		tilesConfigurer
//				.setPreparerFactoryClass(org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory.class);
		return tilesConfigurer;
	}
}
