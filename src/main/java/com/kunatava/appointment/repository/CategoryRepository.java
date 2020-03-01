package com.kunatava.appointment.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kunatava.appointment.model.CategoryInfo;

public interface CategoryRepository extends CrudRepository<CategoryInfo, String> {

	List<CategoryInfo> findByOrganization(String organization);

}
