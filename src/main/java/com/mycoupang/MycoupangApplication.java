package com.mycoupang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MycoupangApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycoupangApplication.class, args);
	}

	@Bean      
	public ErrorPageFilter errorPageFilter() {     
	      return new ErrorPageFilter();     
	}   


	@Bean  
	public FilterRegistrationBean DisabledErrorPageFilter(ErrorPageFilter filter) {  
	      FilterRegistrationBean filterRegistration = new FilterRegistrationBean<>();  
	      filterRegistration.setFilter(filter);  
	      filterRegistration.setName("disabledErrorPageFilter");  
	      filterRegistration.setEnabled(false);  
	      return filterRegistration;  
	}

	
}
