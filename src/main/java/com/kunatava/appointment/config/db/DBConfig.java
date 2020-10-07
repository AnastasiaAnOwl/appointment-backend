package com.kunatava.appointment.config.db;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.kunatava.appointment.model.Organization;
import com.kunatava.appointment.model.Staff;
import com.kunatava.appointment.repository.OrganizationRepository;
import com.kunatava.appointment.repository.StaffRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class DBConfig {

	@Autowired
	private MockDataConfiguration mockDataConfig;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean
	public InitializingBean initDatabase(OrganizationRepository orgRepo, StaffRepository staffRepo) {
		return () -> {
			log.info("Initializing database");
			if (noOrganizations(orgRepo)) {
				log.info("No organizations found. Creating the default one");
				Organization org = orgRepo.save(getDefaultOrganization());
				log.info("Creating default user");
				staffRepo.save(getDefaultStaff(org.getId()));
			}
		};
	}

	private Organization getDefaultOrganization() {
		return new Organization(null, mockDataConfig.getOrganizationName(), mockDataConfig.getAddress());
	}

	/**
	 * Creating default adimin user with data from application.yml.
	 */
	private Staff getDefaultStaff(String organizationId) {
		return new Staff(null, mockDataConfig.getStaffName(), mockDataConfig.getPhone(), mockDataConfig.getEmail(),
				passwordEncoder.encode(mockDataConfig.getPassword()), organizationId, mockDataConfig.getAuthorities());
	}

	private boolean noOrganizations(OrganizationRepository orgRepo) {
		return orgRepo.count() == 0;
	}

}
