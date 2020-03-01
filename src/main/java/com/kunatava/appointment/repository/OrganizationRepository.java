package com.kunatava.appointment.repository;

import org.springframework.data.repository.CrudRepository;

import com.kunatava.appointment.model.Organization;

public interface OrganizationRepository extends CrudRepository<Organization, String> {

}
