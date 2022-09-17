package it.epicode.be.energy.security.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class RequestRegisterUser {
	
	private String name;
	private String surname;
	private String userName;
	private String password;
	private String email;
	private Set<String> roles = new HashSet<>();

}
