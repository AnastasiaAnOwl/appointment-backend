package com.kunatava.appointment.repository;

import org.springframework.data.repository.CrudRepository;

import com.kunatava.appointment.model.ServiceInfo;

public interface ServiceRepository extends CrudRepository<ServiceInfo, String> {

}
