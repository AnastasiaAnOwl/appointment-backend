package com.kunatava.appointment.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kunatava.appointment.model.Appointment;
import com.kunatava.appointment.model.AppointmentStatus;
import com.kunatava.appointment.model.Client;
import com.kunatava.appointment.model.DateBounds;
import com.kunatava.appointment.propertyeditor.DateBoundsPropertyEditor;
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

	@GetMapping("search/findByStaffAndResources")
	public ResponseEntity<Iterable<Appointment>> findByStaffAndResources(@RequestParam List<String> staffIds,
			@RequestParam List<String> resourceIds, @RequestParam DateBounds dateBounds) {
		Iterable<Appointment> appointments = appointmentRepository.findByStaffInAndResourceInAndStartBetween(staffIds,
				resourceIds, dateBounds.getStart(), dateBounds.getEnd());
		return new ResponseEntity<Iterable<Appointment>>(appointments, HttpStatus.OK);
	}

	@GetMapping("search/findByResources")
	public ResponseEntity<Iterable<Appointment>> findByResources(@RequestParam List<String> resourceIds,
			@RequestParam DateBounds dateBounds) {
		Iterable<Appointment> appointments = appointmentRepository.findByResourceInAndStartBetween(resourceIds,
				dateBounds.getStart(), dateBounds.getEnd());
		return new ResponseEntity<Iterable<Appointment>>(appointments, HttpStatus.OK);
	}

	@GetMapping("search/findByStaff")
	public ResponseEntity<Iterable<Appointment>> findByStaff(@RequestParam List<String> staffIds,
			@RequestParam DateBounds dateBounds) {
		Iterable<Appointment> appointments = appointmentRepository.findByStaffInAndStartBetween(staffIds,
				dateBounds.getStart(), dateBounds.getEnd());
		return new ResponseEntity<Iterable<Appointment>>(appointments, HttpStatus.OK);
	}

	@GetMapping("search/findByOrganization")
	public ResponseEntity<Iterable<Appointment>> findByOrganization(@RequestParam String organization,
			@RequestParam DateBounds dateBounds) {
		Iterable<Appointment> appointments = appointmentRepository.findByOrganizationAndStartBetween(organization,
				dateBounds.getStart(), dateBounds.getEnd());
		return new ResponseEntity<Iterable<Appointment>>(appointments, HttpStatus.OK);
	}

	@GetMapping("page")
	public Page<Appointment> page(Pageable pageable) {
		if (pageable != null) {
			return appointmentRepository.findAll(pageable);
		} else {
			return appointmentRepository.findAll(Pageable.unpaged());
		}
	}

	@GetMapping("statuses")
	public ResponseEntity<AppointmentStatus[]> getStatuses() {
		return new ResponseEntity<>(AppointmentStatus.values(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
		Client client = appointment.getClient();
		if (client.getId() == null) {
			Client existing = clientRepository.findByPhone(client.getPhone());
			if (existing == null) {
				existing = clientRepository.save(client);
			}
			appointment.setClient(existing);
		}
		if (appointment.getStatus() == null) {
			appointment.setStatus(AppointmentStatus.CREATED);
		}
		if (appointment.getDuration() == null) {
			appointment.setDuration(appointment.getService().getDuration());
		}
		Appointment saved = appointmentRepository.save(appointment);
		return new ResponseEntity<>(saved, HttpStatus.CREATED);
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

	/**
	 * We initialize custom editor for {@link DateBounds} class to tell Spring how
	 * to convert custom String to {@link DateBounds} object.
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(DateBounds.class, new DateBoundsPropertyEditor(dateFormat));
	}

}
