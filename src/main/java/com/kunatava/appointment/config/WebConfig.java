package com.kunatava.appointment.config;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {

	@Bean
	public SimpleDateFormat dateFormat(PropsConfiguration propsConfiguration) {
		return new SimpleDateFormat(propsConfiguration.getDateFormat());
	}

}
