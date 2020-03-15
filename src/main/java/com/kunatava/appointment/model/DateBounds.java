package com.kunatava.appointment.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DateBounds {
	private Date start;
	private Date end;

}
