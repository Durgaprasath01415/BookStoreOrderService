package com.bookstore.main.configuration;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfiguration {
	
	private static MessageSourceAccessor messageSourceAccessor;

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	@Bean
	@LoadBalanced
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }
	
	@PostConstruct
	private void initMessageSourceAccessor() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:messages/errormessages", "classpath:messages/successmessages");
		messageSourceAccessor = new MessageSourceAccessor(messageSource, Locale.getDefault());

	}

	public static MessageSourceAccessor getMessageAccessor() {
		return messageSourceAccessor;
	}
}