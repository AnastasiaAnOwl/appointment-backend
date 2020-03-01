package com.kunatava.appointment.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Resource {
	
	@Id
	private String id;
	private String name;
	private String organization;

}
