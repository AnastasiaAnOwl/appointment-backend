package com.kunatava.appointment.security.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kunatava.appointment.model.Staff;
import com.kunatava.appointment.repository.StaffRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BasicUserDetailsService implements UserDetailsService {
	private final StaffRepository staffRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final Staff user = staffRepository.findByEmail(username);
		if (user != null) {
			return user;
		} else {
			throw new UsernameNotFoundException("User with username " + username + " not found");
		}
	}

}
