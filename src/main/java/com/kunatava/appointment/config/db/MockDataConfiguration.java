package com.kunatava.appointment.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Profile("dev")
@Component
@ConfigurationProperties(prefix = "mock-data")
public class MockDataConfiguration {
	private String organizationName;
	private String address;
	private String staffName;
	private String phone;
	private String email;
	private String password;

}
