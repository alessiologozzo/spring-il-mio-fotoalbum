package com.org.springilmiofotoalbum.auth.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.org.springilmiofotoalbum.auth.pojos.Role;
import com.org.springilmiofotoalbum.auth.services.UserService;

@Configuration
public class AuthConfiguration {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http)
		throws Exception {
		return http.authorizeHttpRequests(auth -> 
			auth
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
			.requestMatchers("/", "/login", "/registration", "/error", "/forbidden", "/api/**").permitAll()
			.requestMatchers("/photos/**").hasAnyAuthority(Role.USER, Role.ADMIN)
			.requestMatchers("/admin/**").hasAuthority(Role.ADMIN)

			)
			.formLogin(form -> form.loginPage("/login"))
			.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/"))
			.exceptionHandling(ex -> ex.accessDeniedPage("/forbidden"))
			.csrf(csrf -> csrf.ignoringRequestMatchers("/**"))
			.build();
	}
	
	@Bean
	UserService userDetailsService() {
		return new UserService();
	}

    @Bean
    PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
    
    @Bean
    DaoAuthenticationProvider authenticationProvider() {
      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
   
      authProvider.setUserDetailsService(userDetailsService());
      authProvider.setPasswordEncoder(passwordEncoder());
   
      return authProvider;
    }
}
