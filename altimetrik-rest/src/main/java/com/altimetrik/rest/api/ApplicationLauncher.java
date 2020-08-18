package com.altimetrik.rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.altimetrik.rest.api.model.User;
import com.altimetrik.rest.api.repository.UserRepository;

@SpringBootApplication
public class ApplicationLauncher {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(ApplicationLauncher.class, args);
		
		User user = new User();
		user.setPassword("kamesh");
		user.setUsername("kamesh");
		user.setRoles("USER_ROLE,ADMIN_ROLE");
		run.getBean(UserRepository.class).save(user);
	}

}

