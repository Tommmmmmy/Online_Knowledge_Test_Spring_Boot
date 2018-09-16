package com.insticator.spring.project.models.questions.Poll;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.insticator.spring.project.models.questions.QuestionNotFoundException;
import com.insticator.spring.project.models.questions.Checkbox.Checkbox;
import com.insticator.spring.project.repository.CheckboxRepository;
import com.insticator.spring.project.repository.PollRepository;

@RestController
public class PollResource {
	@Autowired
	private PollRepository service;
	
	@GetMapping("/polls")
	public List<Poll> retrieveAllUsers() {
		return service.findAll();
	}
	
	@GetMapping("/polls/{id}")
	public Poll retrieveUser(@PathVariable int id) {
		
		Optional<Poll> poll = service.findById(id);

		if (!poll.isPresent()) {
			throw new QuestionNotFoundException("id-"+id);
		}
		
		return poll.get();
	}
	
	@GetMapping("/polls/{id}/options")
	public Set<String> retrieveAllOptionsForOne(@PathVariable int id) {
		
		Optional<Poll> poll = service.findById(id);

		if (!poll.isPresent()) {
			throw new QuestionNotFoundException("id-"+id);
		}
		
		Poll question = poll.get();
		
		return question.getOptions();
	}

}
