package com.insticator.spring.project.models.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.insticator.spring.project.repository.UserRepository;

@RestController
public class UserResource {

	@Autowired
	private UserRepository service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		
		Optional<User> user = service.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		
		return user.get();
	}
	
}
