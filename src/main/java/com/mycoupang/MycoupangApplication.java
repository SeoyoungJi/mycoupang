package com.mycoupang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MycoupangApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(MycoupangApplication.class, args);
	}

	
    public void App() {
        setRegisterErrorPageFilter(false); // <- this one
    }

	
	
}
