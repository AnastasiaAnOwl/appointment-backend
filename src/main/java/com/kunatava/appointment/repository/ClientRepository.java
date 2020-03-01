package com.kunatava.appointment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kunatava.appointment.model.Client;

public interface ClientRepository extends MongoRepository<Client, String> {

	Client findByPhone(String phone);

}
