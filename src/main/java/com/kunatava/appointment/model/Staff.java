package com.kunatava.appointment.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Staff implements UserDetails {

	@Id
	private String id;
	private String name;
	private String phone;
	private String email;
	private String password;
	private String organization;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean isEnabled;
	private List<Authority> authorities;

	public Staff(String id, String name, String phone, String email, String password, String organization,
			List<Authority> authorities) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.organization = organization;
		this.accountNonExpired = true;
		this.accountNonLocked = true;
		this.credentialsNonExpired = true;
		this.isEnabled = true;
		this.authorities = authorities;
	}

	@Override
	public String getUsername() {
		return email;
	}

}
