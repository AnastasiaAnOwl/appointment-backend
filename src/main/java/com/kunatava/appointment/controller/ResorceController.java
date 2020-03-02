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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kunatava.appointment.model.Resource;
import com.kunatava.appointment.repository.ResourceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("resources")
public class ResorceController {

	private final ResourceRepository resourceRepository;

	@GetMapping
	public Iterable<Resource> get() {
		return resourceRepository.findAll();
	}

	@GetMapping("{id}")
	public ResponseEntity<Resource> getOne(@PathVariable String id) {
		Optional<Resource> res = resourceRepository.findById(id);
		if (res.isPresent()) {
			return new ResponseEntity<>(res.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Resource> create(@RequestBody Resource resource) {
		Resource res = resourceRepository.save(resource);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@PutMapping
	public Resource update(@RequestBody Resource resource) {
		return resourceRepository.save(resource);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		resourceRepository.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}

}
