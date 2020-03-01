package com.kunatava.appointment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "props")
public class PropsConfiguration {
	private String dateFormat;
}
