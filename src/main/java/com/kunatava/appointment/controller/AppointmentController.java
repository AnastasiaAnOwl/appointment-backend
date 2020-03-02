package com.kunatava.appointment.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kunatava.appointment.model.Appointment;
import com.kunatava.appointment.model.AppointmentStatus;
import com.kunatava.appointment.repository.AppointmentRepository;
import com.kunatava.appointment.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("appointments")
public class AppointmentController {

	private final AppointmentRepository appointmentRepository;
	private final ClientRepository clientRepository;
	private final SimpleDateFormat dateFormat;

	@GetMapping
	public ResponseEntity<Iterable<Appointment>> getAppointments(@RequestParam(required = false) List<String> staffIds,
			@RequestParam(required = false) List<String> resourceIds, @RequestParam String start,
			@RequestParam String end, @RequestParam(required = false) String organization) {

		Date startDate = null;
		Date endDate = null;
		try {
			startDate = dateFormat.parse(start);
			endDate = dateFormat.parse(end);
		} catch (ParseException ex) {
			return new ResponseEntity<>(Collections.emptyList(), HttpStatus.BAD_REQUEST);
		}
		Iterable<Appointment> appointments;
		if (!isEmpty(staffIds) && !isEmpty(resourceIds)) {
			appointments = appointmentRepository.findByStaffInAndResourceInAndStartBetween(staffIds, resourceIds,
					startDate, endDate);
		} else if (isEmpty(staffIds) && !isEmpty(resourceIds)) {
			appointments = appointmentRepository.findByResourceInAndStartBetween(resourceIds, startDate, endDate);
		} else if (!isEmpty(staffIds) && isEmpty(resourceIds)) {
			appointments = appointmentRepository.findByStaffInAndStartBetween(staffIds, startDate, endDate);
		} else {
			appointments = appointmentRepository.findByOrganizationAndStartBetween(organization, startDate, endDate);
		}
		return new ResponseEntity<Iterable<Appointment>>(appointments, HttpStatus.OK);
	}

	@GetMapping("statuses")
	public ResponseEntity<AppointmentStatus[]> getStatuses() {
		return new ResponseEntity<>(AppointmentStatus.values(), HttpStatus.OK);
	}

	@PutMapping
	public Appointment update(@RequestBody Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		appointmentRepository.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}

	private boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

}
