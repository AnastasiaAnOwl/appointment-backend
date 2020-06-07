package com.kunatava.appointment.model;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class ServiceInfo {

	@Id
	private String id;
	private String name;
	private BigDecimal price;
	private Integer duration;
	@DBRef
	private List<Resource> resources;
	private boolean staffRequired;

}
