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

import com.kunatava.appointment.model.CategoryInfo;
import com.kunatava.appointment.model.ServiceInfo;
import com.kunatava.appointment.repository.CategoryRepository;
import com.kunatava.appointment.repository.ServiceRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("categories")
public class CategoryContoller {

	private final ServiceRepository serviceRepository;
	private final CategoryRepository categoryRepository;

	@GetMapping
	public Iterable<CategoryInfo> get(@RequestParam(required = false) String organization) {
		if (organization == null) {
			return categoryRepository.findAll();
		} else {
			return categoryRepository.findByOrganization(organization);
		}
	}

	@PostMapping
	public ResponseEntity<CategoryInfo> create(@RequestBody CategoryInfo category) {
		for (ServiceInfo serv : category.getServices()) {
			if (serv.getId() == null) {
				ServiceInfo service = serviceRepository.save(serv);
				serv.setId(service.getId());
			}
		}
		CategoryInfo created = categoryRepository.save(category);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@PutMapping("{id}")
	public CategoryInfo save(@PathVariable String id, @RequestBody CategoryInfo category) {
		return categoryRepository.findById(id).map(cat -> {
			cat.setName(category.getName());
			cat.setOrganization(category.getOrganization());
			ServiceInfo serv = category.getServices().get(category.getServices().size() - 1);
			if (serv.getId() == null) {
				ServiceInfo service = serviceRepository.save(serv);
				serv.setId(service.getId());
			}
			cat.setServices(category.getServices());
			return categoryRepository.save(cat);
		}).orElseGet(null);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		categoryRepository.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}

}
