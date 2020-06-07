package com.kunatava.appointment.controller;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

import com.kunatava.appointment.model.Client;
import com.kunatava.appointment.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("clients")
public class ClientController {

	private final ClientRepository clientRepository;

	@GetMapping("page")
	public Page<Client> get(Pageable pageable) {
		if (pageable != null) {
			return clientRepository.findAll(pageable);
		} else {
			return clientRepository.findAll(Pageable.unpaged());
		}
	}

	@GetMapping
	public Iterable<Client> get() {
		return clientRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<Client> create(@RequestBody Client client) {
		Client created = clientRepository.save(client);
		return new ResponseEntity<>(created, HttpStatus.CREATED);
	}

	@PutMapping
	public Client update(@RequestBody Client client) {
		return clientRepository.save(client);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable String id) {
		clientRepository.deleteById(id);
		return new ResponseEntity<>(id, HttpStatus.NO_CONTENT);
	}

	@GetMapping("search/findPagedByNameOrPhone")
	public Page<Client> findPagedByNameOrPhone(@RequestParam String query, Pageable pageable) {
		return findClients(query, pageable);
	}

	@GetMapping("search/findByNameOrPhone")
	public Iterable<Client> findByNameOrPhone(@RequestParam String query) {
		return findClients(query, PageRequest.of(0, 5)).getContent();
	}

	private Page<Client> findClients(String query, Pageable pageable) {
		return clientRepository.findAll(example(query), pageable != null ? pageable : Pageable.unpaged());
	}

	private Example<Client> example(String query) {
		ExampleMatcher caseInsensitiveMatcher = ExampleMatcher.matchingAny().withIgnoreCase().withIgnoreNullValues();
		return Example.of(new Client(null, query, query, null), caseInsensitiveMatcher);
	}

}
