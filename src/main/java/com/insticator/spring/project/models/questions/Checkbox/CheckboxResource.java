package com.insticator.spring.project.models.questions.Checkbox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.insticator.spring.project.models.questions.QuestionNotFoundException;
import com.insticator.spring.project.models.questions.Poll.Poll;
import com.insticator.spring.project.models.user.User;
import com.insticator.spring.project.models.user.UserNotFoundException;
import com.insticator.spring.project.repository.CheckboxRepository;

@RestController
public class CheckboxResource {

	@Autowired
	private CheckboxRepository service;
	
	@GetMapping("/checkboxs")
	public Map<String, Set<String>> retrieveAllCheckboxs() {
		List<Checkbox> checkboxs = service.findAll();
		
		if (checkboxs.size() == 0) {
			throw new QuestionNotFoundException("checkbox");
		}
		
		Map<String, Set<String>> map = new HashMap<>();
		
		for(Checkbox checkbox : checkboxs) {
			map.put(checkbox.getQuestion(), checkbox.getOptions());
		}
		return map;
	}
	
	@GetMapping("/checkboxs/{id}")
	public Map<String, Set<String>> retrieveCheckbox(@PathVariable int id) {
		
		Optional<Checkbox> checkbox = service.findById(id);

		if (!checkbox.isPresent()) {
			throw new QuestionNotFoundException("id-"+id);
		}
		
		Map<String, Set<String>> map = new HashMap<>();
		
		map.put(checkbox.get().getQuestion(), checkbox.get().getOptions());
		return map;
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
