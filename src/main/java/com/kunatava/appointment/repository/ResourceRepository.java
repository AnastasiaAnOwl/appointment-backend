package com.kunatava.appointment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kunatava.appointment.model.Resource;

public interface ResourceRepository extends CrudRepository<Resource, String> {

	List<Resource> findByOrganization(String organization);

}
