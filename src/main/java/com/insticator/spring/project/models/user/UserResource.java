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

import com.insticator.spring.project.models.questions.QuestionNotFoundException;
import com.insticator.spring.project.models.questions.Checkbox.Checkans;
import com.insticator.spring.project.models.questions.Checkbox.CheckansId;
import com.insticator.spring.project.models.questions.Checkbox.Checkbox;
import com.insticator.spring.project.models.questions.Poll.Poll;
import com.insticator.spring.project.models.questions.Poll.Pollans;
import com.insticator.spring.project.models.questions.Poll.PollansId;
import com.insticator.spring.project.models.questions.Trivia.Trivia;
import com.insticator.spring.project.models.questions.Trivia.Triviaans;
import com.insticator.spring.project.models.questions.Trivia.TriviaansId;
import com.insticator.spring.project.repository.CheckansRepository;
import com.insticator.spring.project.repository.CheckboxRepository;
import com.insticator.spring.project.repository.PollRepository;
import com.insticator.spring.project.repository.PollansRepository;
import com.insticator.spring.project.repository.TriviaRepository;
import com.insticator.spring.project.repository.TriviaansRepository;
import com.insticator.spring.project.repository.UserRepository;

@RestController
public class UserResource {

	@Autowired
	private UserRepository service;
	
	@Autowired
	private CheckboxRepository checkService;

	@Autowired
	private CheckansRepository checkansService;
	
	@Autowired
	private TriviaRepository triviaService;
	
	@Autowired
	private TriviaansRepository triviaansService;
	
	@Autowired
	private PollRepository pollService;
	
	@Autowired
	private PollansRepository pollansService;
	
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
		
		Optional<Checkans> ans = checkansService.findById(id);
		if(!ans.isPresent()) {
			throw new QuestionNotFoundException("No answer found for UserId: " + uId + ", CheckId: " + qId);
		}
		return ans.get().getAnswers();
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
	
	//mapping for trivias
	@PostMapping("/users/{uId}/trivias/{qId}")
	public String saveTriviaAnswers(@Valid @RequestBody Map<String, String> trivaans, @PathVariable int uId, @PathVariable int qId) {
		
		Optional<User> userOptional = service.findById(uId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+uId);
		}
		
		List<Trivia> trivias = triviaService.findByUUserAndId(userOptional.get(), qId);
		
		if (trivias.size() == 0) {
			throw new QuestionNotFoundException("UserId: " + uId + "TriviaId: " + qId);
		}
		
		Trivia trivia = trivias.iterator().next();
		
		String userAnswer= trivaans.get("answers"); 
		
		TriviaansId id = new TriviaansId(uId, qId);
		
		Triviaans answer = new Triviaans(id, trivia, userOptional.get(), userAnswer);
		triviaansService.save(answer);
		
		if(trivia.getCorrectAns().equals(userAnswer)) {
			return "Correct Answer!";
		}
		else {
			return "Wrong Answer!";
		}
	}
	
	@GetMapping("/users/{uId}/trivias/{qId}")
	public String saveTriviaAnswers(@PathVariable int uId, @PathVariable int qId) {
		
		Optional<User> userOptional = service.findById(uId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+uId);
		}
		
		List<Trivia> trivias = triviaService.findByUUserAndId(userOptional.get(), qId);
		
		if (trivias.size() == 0) {
			throw new QuestionNotFoundException("UserId: " + uId + "TriviaId: " + qId);
		}
		
		Trivia trivia = trivias.iterator().next();
		
		TriviaansId id = new TriviaansId(uId, qId);
		
		Optional<Triviaans> ans = triviaansService.findById(id);
		if(!ans.isPresent()) {
			throw new QuestionNotFoundException("No answer found for UserId: " + uId + ", TriviaId: " + qId);
		}
		return ans.get().getAnswer();
	}
	
	@GetMapping("/users/{uId}/trivias")
	public List<String> checkTrivias(@PathVariable int uId) {
		
		Optional<User> userOptional = service.findById(uId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+uId);
		}
		
		Set<Trivia> trivias = userOptional.get().gettQuestions();
		List<String> res = new ArrayList<>();
		for(Trivia trivia : trivias) {
			TriviaansId id = new TriviaansId(uId, trivia.getId());
			Optional<Triviaans> ans = triviaansService.findById(id);
			if(!ans.isPresent()) {
				res.add(trivia.getQuestion());
			}
		}
		if(res.size() == 0) {
			throw new QuestionNotFoundException("No trivia found for UserId: " + uId + " or all were answered");
		}
		return res;
	}
	
	//mapping for polls
	@PostMapping("/users/{uId}/polls/{qId}")
	public void savePollAnswers(@Valid @RequestBody Map<String, String> pollans, @PathVariable int uId, @PathVariable int qId) {
		
		Optional<User> userOptional = service.findById(uId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+uId);
		}
		
		List<Poll> polls = pollService.findByUUserAndId(userOptional.get(), qId);
		
		if (polls.size() == 0) {
			throw new QuestionNotFoundException("UserId: " + uId + "PollId: " + qId);
		}
		
		Poll poll = polls.iterator().next();
		
		String userAnswer= pollans.get("answers"); 
		
		PollansId id = new PollansId(uId, qId);
		
		Pollans answer = new Pollans(id, poll, userOptional.get(), userAnswer);
		pollansService.save(answer);
	}
	
	@GetMapping("/users/{uId}/polls/{qId}")
	public String savePollAnswers(@PathVariable int uId, @PathVariable int qId) {
		
		Optional<User> userOptional = service.findById(uId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+uId);
		}
		
		List<Poll> polls = pollService.findByUUserAndId(userOptional.get(), qId);
		
		if (polls.size() == 0) {
			throw new QuestionNotFoundException("UserId: " + uId + "pollId: " + qId);
		}
		
		Poll poll = polls.iterator().next();
		
		PollansId id = new PollansId(uId, qId);
		
		Optional<Pollans> ans = pollansService.findById(id);
		if(!ans.isPresent()) {
			throw new QuestionNotFoundException("No answer found for UserId: " + uId + ", PollId: " + qId);
		}
		return ans.get().getAnswer();
	}
	
	@GetMapping("/users/{uId}/polls")
	public List<String> checkPolls(@PathVariable int uId) {
		
		Optional<User> userOptional = service.findById(uId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+uId);
		}
		
		Set<Poll> polls = userOptional.get().getpQuestions();
		List<String> res = new ArrayList<>();
		for(Poll poll : polls) {
			PollansId id = new PollansId(uId, poll.getId());
			Optional<Pollans> ans = pollansService.findById(id);
			if(!ans.isPresent()) {
				res.add(poll.getQuestion());
			}
		}
		if(res.size() == 0) {
			throw new QuestionNotFoundException("No poll found for UserId: " + uId + " or all were answered");
		}
		return res;
	}
}
