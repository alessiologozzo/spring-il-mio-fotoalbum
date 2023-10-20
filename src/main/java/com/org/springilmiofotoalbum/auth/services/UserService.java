package com.org.springilmiofotoalbum.auth.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.org.springilmiofotoalbum.auth.pojos.User;
import com.org.springilmiofotoalbum.auth.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService{
	@Autowired
	UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
	}
	
	public void saveAll(List<User> users) {
		userRepository.saveAll(users);
	}
	
	public User findByUsername(String name) {
		return userRepository.findByUsername(name);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}
}
