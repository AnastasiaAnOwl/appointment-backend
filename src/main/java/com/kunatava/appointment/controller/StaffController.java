package com.kunatava.appointment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kunatava.appointment.model.Staff;
import com.kunatava.appointment.repository.StaffRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("staff")
public class StaffController {

	private final StaffRepository staffRepository;

	@GetMapping
	public Iterable<Staff> get(@RequestParam(required = false) String organization) {
		if (organization == null) {
			return staffRepository.findAll();
		} else {
			return staffRepository.findByOrganization(organization);
		}
	}

	@PostMapping
	public ResponseEntity<Staff> create(@RequestBody Staff staff) {
		Staff created = staffRepository.save(staff);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@PutMapping
	public Staff update(@RequestBody Staff staff) {
		return staffRepository.save(staff);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		staffRepository.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}

}
