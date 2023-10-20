package com.org.springilmiofotoalbum.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.springilmiofotoalbum.auth.dtos.UserDTO;
import com.org.springilmiofotoalbum.auth.pojos.Role;
import com.org.springilmiofotoalbum.auth.pojos.User;
import com.org.springilmiofotoalbum.auth.services.RoleService;
import com.org.springilmiofotoalbum.auth.services.UserService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired 
	RoleService roleService;

	private SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

	@GetMapping("/login")
	public String login() {
		return "auth/login.html";
	}

	@PostMapping("/logout")
	public void requestLogout(Authentication authentication, HttpServletRequest request,
			HttpServletResponse response) {
		this.logoutHandler.logout(request, response, authentication);
	}
	
	@GetMapping("/forbidden")
	public String forbidden() {
		return "auth/forbidden.html";
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userDTO", new UserDTO());

		return "auth/registration.html";
	}

	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute UserDTO userDTO, BindingResult bindingResult, HttpServletRequest request) {
		String result = "auth/registration.html";

		if(!bindingResult.hasErrors()) {
			Role role = roleService.findByName(Role.USER);
			userService.save(new User(userDTO, role));
			result = "redirect:/photos";

			try {
				request.login(userDTO.getUsername(), userDTO.getPassword());
			}
			catch (ServletException e) {
				System.err.println("Error while login.");
			}
		}

		return result;
	}
}
