package com.kunatava.appointment.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kunatava.appointment.model.CategoryInfo;
import com.kunatava.appointment.model.ServiceInfo;
import com.kunatava.appointment.repository.CategoryRepository;
import com.kunatava.appointment.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ServiceController {

	private final ServiceRepository serviceRepository;
	private final CategoryRepository categoryRepository;

	@PostMapping("category/{id}/service")
	public ResponseEntity<CategoryInfo> create(@PathVariable String id, @RequestBody ServiceInfo service) {
		return categoryRepository.findById(id).map(category -> {
			ServiceInfo saved = serviceRepository.save(service);
			category.getServices().add(saved);
			CategoryInfo updated = categoryRepository.save(category);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PutMapping("service")
	public ServiceInfo update(@RequestBody ServiceInfo service) {
		return serviceRepository.save(service);
	}

	@DeleteMapping("service/{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		serviceRepository.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}

}
