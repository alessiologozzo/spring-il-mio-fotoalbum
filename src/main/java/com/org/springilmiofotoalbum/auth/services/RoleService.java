package com.org.springilmiofotoalbum.auth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.springilmiofotoalbum.auth.pojos.Role;
import com.org.springilmiofotoalbum.auth.repositories.RoleRepository;

@Service
public class RoleService {
	@Autowired
	private RoleRepository roleRepository;
	
	public void save(Role role) {
		roleRepository.save(role);
	}
	
	public void saveAll(List<Role> roles) {
		roleRepository.saveAll(roles);
	}

	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}
}
