package com.kunatava.appointment.repository;

import org.springframework.data.repository.CrudRepository;

import com.kunatava.appointment.model.Appointment;

public interface AppointmentRepository extends CrudRepository<Appointment, String> {

}
