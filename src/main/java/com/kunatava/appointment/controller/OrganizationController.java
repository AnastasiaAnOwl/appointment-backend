package com.kunatava.appointment.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kunatava.appointment.model.Organization;
import com.kunatava.appointment.repository.OrganizationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController("organizations")
public class OrganizationController {

	/**
	 * Spring will inject {@link OrganizationRepository} bean while instantiating
	 * {@link OrganizationController}.<br/>
	 * Constructor for this final field will be created automatically by
	 * {@link RequiredArgsConstructor} annotation.
	 */
	private final OrganizationRepository organizationRepository;

	/**
	 * @return collection of all {@link Organization} from database.
	 */
	@GetMapping
	public Iterable<Organization> get() {
		return organizationRepository.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Organization> getOne(@PathVariable String id) {
		Optional<Organization> org = organizationRepository.findById(id);
		if (org.isPresent()) {
			return new ResponseEntity<>(org.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Organization> create(@RequestBody Organization organization) {
		Organization org = organizationRepository.save(organization);
		return new ResponseEntity<>(org, HttpStatus.CREATED);
	}

	@PutMapping
	public Organization update(@RequestBody Organization organization) {
		return organizationRepository.save(organization);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		organizationRepository.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}

}
