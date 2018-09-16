package com.insticator.spring.project.models.questions.Poll;

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
import com.insticator.spring.project.models.questions.Checkbox.Checkbox;
import com.insticator.spring.project.repository.CheckboxRepository;
import com.insticator.spring.project.repository.PollRepository;

@RestController
public class PollResource {
	@Autowired
	private PollRepository service;
	
	@GetMapping("/polls")
	public Map<String, Set<String>> retrieveAllUsers() {
		List<Poll> polls = service.findAll();
		
		if (polls.size() == 0) {
			throw new QuestionNotFoundException("polls");
		}
		
		Map<String, Set<String>> map = new HashMap<>();
		
		for(Poll poll : polls) {
			map.put(poll.getQuestion(), poll.getOptions());
		}
		return map;
	}
	
	@GetMapping("/polls/{id}")
	public Map<String, Set<String>> retrieveUser(@PathVariable int id) {
		
		Optional<Poll> poll = service.findById(id);

		if (!poll.isPresent()) {
			throw new QuestionNotFoundException("id-"+id);
		}
		
		Map<String, Set<String>> map = new HashMap<>();
		
		map.put(poll.get().getQuestion(), poll.get().getOptions());
		return map;
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
