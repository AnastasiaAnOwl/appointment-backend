package com.kunatava.appointment.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kunatava.appointment.model.Appointment;

public interface AppointmentRepository extends PagingAndSortingRepository<Appointment, String> {

	List<Appointment> findByStaffInAndResourceInAndStartBetween(List<String> staffIds, List<String> resourceIds,
			Date start, Date end);

	List<Appointment> findByStaffInAndStartBetween(List<String> staffIds, Date start, Date end);

	List<Appointment> findByResourceInAndStartBetween(List<String> resourceIds, Date start, Date end);

	List<Appointment> findByOrganizationAndStartBetween(String organization, Date start, Date end);

}
