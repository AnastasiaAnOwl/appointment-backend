package com.kunatava.appointment.controller;

import java.text.SimpleDateFormat;

import org.springframework.web.bind.annotation.RestController;

import com.kunatava.appointment.repository.AppointmentRepository;
import com.kunatava.appointment.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@RestController("appointments")
@RequiredArgsConstructor
public class AppointmentController {

	private final AppointmentRepository appointmentRepository;
	private final ClientRepository clientRepository;
	private final SimpleDateFormat dateFormat;

}
