package com.insticator.spring.project.models.questions.Trivia;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.insticator.spring.project.models.questions.QuestionNotFoundException;
import com.insticator.spring.project.repository.TriviaRepository;

@RestController
public class TriviaResource {

	@Autowired
	TriviaRepository service;
	
	@GetMapping("/trivias")
	public List<Trivia> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("/trivias/{id}")
	public Trivia retrieveUser(@PathVariable int id) {
		
		Optional<Trivia> trivia = service.findById(id);

		if (!trivia.isPresent()) {
			throw new QuestionNotFoundException("id-"+id);
		}
		
		return trivia.get();
	}
	
	@GetMapping("/trivias/{id}/options")
	public Set<String> retrieveAllOptionsForOne(@PathVariable int id) {
		
		Optional<Trivia> trivia = service.findById(id);

		if (!trivia.isPresent()) {
			throw new QuestionNotFoundException("id-"+id);
		}
		
		Trivia question = trivia.get();
		
		return question.getOptions();
	}
}
