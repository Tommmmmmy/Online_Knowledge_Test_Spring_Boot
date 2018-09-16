package com.insticator.spring.project.models.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
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

import com.insticator.spring.project.models.questions.Checkans;
import com.insticator.spring.project.models.questions.CheckansId;
import com.insticator.spring.project.models.questions.Checkbox;
import com.insticator.spring.project.models.questions.QuestionNotFoundException;
import com.insticator.spring.project.repository.CheckansRepository;
import com.insticator.spring.project.repository.CheckboxRepository;
import com.insticator.spring.project.repository.UserRepository;

@RestController
public class UserResource {

	@Autowired
	private UserRepository service;
	
	@Autowired
	private CheckboxRepository checkService;

	@Autowired
	private CheckansRepository checkansService;
	
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
	
	@PostMapping("/users/{uId}/checkboxs/{qId}")
	public void saveCheckAnswers(@Valid @RequestBody Map<String, String[]> checkans, @PathVariable int uId, @PathVariable int qId) {
		
		Optional<User> userOptional = service.findById(uId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+uId);
		}
		
		List<Checkbox> checkboxs = checkService.findByUUserAndId(userOptional.get(), qId);
		
		if (checkboxs.size() == 0) {
			throw new QuestionNotFoundException("UserId: " + uId + "CheckId: " + qId);
		}
		
		Checkbox checkbox = checkboxs.iterator().next();
		
		Set<String> checkSet= new HashSet<>(Arrays.asList(checkans.get("answers"))); 
		
		CheckansId id = new CheckansId(uId, qId);
		
		Checkans answer = new Checkans(id, checkSet, checkbox, userOptional.get());
		checkansService.save(answer);
	}
	
	@GetMapping("/users/{uId}/checkboxs/{qId}")
	public Set<String> saveCheckAnswers(@PathVariable int uId, @PathVariable int qId) {
		
		Optional<User> userOptional = service.findById(uId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+uId);
		}
		
		List<Checkbox> checkboxs = checkService.findByUUserAndId(userOptional.get(), qId);
		
		if (checkboxs.size() == 0) {
			throw new QuestionNotFoundException("UserId: " + uId + "CheckId: " + qId);
		}
		
		Checkbox checkbox = checkboxs.iterator().next();
		
		CheckansId id = new CheckansId(uId, qId);
		
		Set<String> res = checkansService.findById(id).get().getAnswers();
		if(res.size() == 0) {
			throw new QuestionNotFoundException("No answer found for UserId: " + uId + "CheckId: " + qId);
		}
		return res;
	}
	
	@GetMapping("/users/{uId}/checkboxs")
	public List<String> checkChecks(@PathVariable int uId) {
		
		Optional<User> userOptional = service.findById(uId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+uId);
		}
		
		Set<Checkbox> checkboxs = userOptional.get().getcQuestions();
		List<String> res = new ArrayList<>();
		for(Checkbox checkbox : checkboxs) {
			CheckansId id = new CheckansId(uId, checkbox.getId());
			Optional<Checkans> ans = checkansService.findById(id);
			if(!ans.isPresent()) {
				res.add(checkbox.getQuestion());
			}
		}
		if(res.size() == 0) {
			throw new QuestionNotFoundException("No checkbox found for UserId: " + uId + " or all were answered");
		}
		return res;
	}
}
