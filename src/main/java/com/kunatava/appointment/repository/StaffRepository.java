package com.kunatava.appointment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kunatava.appointment.model.Staff;

public interface StaffRepository extends CrudRepository<Staff, String> {

	List<Staff> findByOrganization(String organization);

}
