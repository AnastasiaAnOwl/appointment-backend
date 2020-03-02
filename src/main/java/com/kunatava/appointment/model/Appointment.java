package com.kunatava.appointment.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Appointment {

	@Id
	private String id;
	private Date start;
	private AppointmentStatus status;
	private String organization;
	@DBRef
	private Staff staff;
	@DBRef
	private Client client;
	@DBRef
	private ServiceInfo service;
	private Integer duration;
	@DBRef
	private Resource resource;

}
