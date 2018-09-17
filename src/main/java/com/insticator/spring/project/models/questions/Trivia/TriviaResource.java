package com.insticator.spring.project.models.questions.Trivia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.insticator.spring.project.models.questions.QuestionNotFoundException;
import com.insticator.spring.project.models.questions.Poll.Poll;
import com.insticator.spring.project.repository.TriviaRepository;

@RestController
public class TriviaResource {

	@Autowired
	TriviaRepository service;
	
	@GetMapping("/trivias")
	public Map<String, Set<String>> retrieveAllTrivia() {
		List<Trivia> trivias = service.findAll();
		
		if (trivias.size() == 0) {
			throw new QuestionNotFoundException("trivia");
		}
		
		Map<String, Set<String>> map = new HashMap<>();
		
		for(Trivia trivia : trivias) {
			map.put(trivia.getQuestion(), trivia.getOptions());
		}
		return map;
	}
	
	@GetMapping("/trivias/{id}")
	public Map<String, Set<String>> retrieveTrivia(@PathVariable int id) {
		
		Optional<Trivia> trivia = service.findById(id);

		if (!trivia.isPresent()) {
			throw new QuestionNotFoundException("id-"+id);
		}
		
		Map<String, Set<String>> map = new HashMap<>();
		
		map.put(trivia.get().getQuestion(), trivia.get().getOptions());
		
		return map;
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
