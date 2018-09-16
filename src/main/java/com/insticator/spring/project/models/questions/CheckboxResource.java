package com.insticator.spring.project.models.questions;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insticator.spring.project.models.user.User;
import com.insticator.spring.project.models.user.UserNotFoundException;
import com.insticator.spring.project.repository.CheckboxRepository;

@RestController
public class CheckboxResource {

	@Autowired
	private CheckboxRepository service;
	
	@GetMapping("/checkboxs")
	public List<Checkbox> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("/checkboxs/{id}")
	public Checkbox retrieveUser(@PathVariable int id) {
		
		Optional<Checkbox> checkbox = service.findById(id);

		if (!checkbox.isPresent()) {
			throw new QuestionNotFoundException("id-"+id);
		}
		
		return checkbox.get();
	}
	
	@GetMapping("/checkboxs/{id}/options")
	public Set<String> retrieveAllOptionsForOne(@PathVariable int id) {
		
		Optional<Checkbox> checkbox = service.findById(id);

		if (!checkbox.isPresent()) {
			throw new QuestionNotFoundException("id-"+id);
		}
		
		Checkbox question = checkbox.get();
		
		return question.getOptions();
	}

}
