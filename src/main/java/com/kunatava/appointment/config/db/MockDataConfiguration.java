package com.kunatava.appointment.config.db;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.kunatava.appointment.model.Authority;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "mock-data")
public class MockDataConfiguration {
	private String organizationName;
	private String address;
	private String staffName;
	private String phone;
	private String email;
	private String password;
	private List<Authority> authorities;

}
