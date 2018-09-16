package com.insticator.spring.project.models.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
import com.insticator.spring.project.models.questions.matrix.Matrix;
import com.insticator.spring.project.models.questions.matrix.Matrixans;
import com.insticator.spring.project.models.questions.matrix.MatrixansId;
import com.insticator.spring.project.repository.CheckansRepository;
import com.insticator.spring.project.repository.CheckboxRepository;
import com.insticator.spring.project.repository.MatrixRepository;
import com.insticator.spring.project.repository.MatrixansRepository;
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
	
	@Autowired
	private MatrixRepository matrixService;
	
	@Autowired
	private MatrixansRepository matrixansService;
	
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
	
	@GetMapping("/users/{id}/questions")
	public Map<String, Set<String>> retrieveAllQuestions(@PathVariable int id) {
		
		Optional<User> user = service.findById(id);

		if (!user.isPresent()) {
			throw new UserNotFoundException("id-"+id);
		}
		
		Map<String, Set<String>> res = new HashMap<>();
		
		Set<Trivia> tQuestions = user.get().gettQuestions();
		Set<Poll> pQuestions = user.get().getpQuestions();
		Set<Matrix> mQuestions = user.get().getmQuestions();
		Set<Checkbox> cQuestions = user.get().getcQuestions();
		
		if(tQuestions.size() > 0) {
			for(Trivia trivia : tQuestions) {
				TriviaansId qid = new TriviaansId(id, trivia.getId());
				Optional<Triviaans> ans = triviaansService.findById(qid);
				if(!ans.isPresent()) {
					res.put("Trivia " + trivia.getId() + " " + trivia.getQuestion(), trivia.getOptions());
				}
			}
		}
		
		if(pQuestions.size() > 0) {
			for(Poll poll : pQuestions) {
				PollansId qid = new PollansId(id, poll.getId());
				Optional<Pollans> ans = pollansService.findById(qid);
				if(!ans.isPresent()) {
					res.put("Poll " + poll.getId() + " " + poll.getQuestion(), poll.getOptions());
				}
			}
		}
		
		if(mQuestions.size() > 0) {
			for(Matrix matrix : mQuestions) {
				MatrixansId qid = new MatrixansId(id, matrix.getId());
				Optional<Matrixans> ans = matrixansService.findById(qid);
				if(ans.isPresent()) {
					continue;
				}
				Set<String> list = new HashSet<>();
				int count = 0;
				StringBuilder str = new StringBuilder();
				for(String option : matrix.getOptions1()) {
					str.append(option);
					if(count != matrix.getOptions1().size() - 1) str.append(",");
					count++;
				}
				list.add(str.toString());
				count = 0;
				str = new StringBuilder();
				for(String option : matrix.getOptions2()) {
					str.append(option);
					if(count != matrix.getOptions2().size() - 1) str.append(",");
					count++;
				}
				list.add(str.toString());
				res.put("matrix " + matrix.getId(), list);
			}
		}
		
		if(cQuestions.size() > 0) {
			for(Checkbox checkbox : cQuestions) {
				CheckansId qid = new CheckansId(id, checkbox.getId());
				Optional<Checkans> ans = checkansService.findById(qid);
				if(!ans.isPresent()) {
					res.put("Checkbox " + checkbox.getId() + " " + checkbox.getQuestion(), checkbox.getOptions());
				}
			}
		}
		
		return res;
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
	
	//mapping for matrix
	@PostMapping("/users/{uId}/matrixs/{qId}")
	public void saveMatrixAnswers(@Valid @RequestBody List<String> matrixans, @PathVariable int uId, @PathVariable int qId) {
		
		Optional<User> userOptional = service.findById(uId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+uId);
		}
		
		List<Matrix> matrixs = matrixService.findByUUserAndId(userOptional.get(), qId);
		
		if (matrixs.size() == 0) {
			throw new QuestionNotFoundException("UserId: " + uId + "MatrixId: " + qId);
		}
		
		Matrix matrix = matrixs.iterator().next();
		
		MatrixansId id = new MatrixansId(uId, qId);
		
		Matrixans answer = new Matrixans(id, matrix, userOptional.get(), matrixans.get(0), matrixans.get(1));
		matrixansService.save(answer);
	}
	
	@GetMapping("/users/{uId}/matrixs/{qId}")
	public List<String> saveMatrixAnswers(@PathVariable int uId, @PathVariable int qId) {
		
		Optional<User> userOptional = service.findById(uId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+uId);
		}
		
		List<Matrix> matrixs = matrixService.findByUUserAndId(userOptional.get(), qId);
		
		if (matrixs.size() == 0) {
			throw new QuestionNotFoundException("UserId: " + uId + "MatrixId: " + qId);
		}
		
		Matrix matrix = matrixs.iterator().next();
		
		MatrixansId id = new MatrixansId(uId, qId);
		
		Optional<Matrixans> ans = matrixansService.findById(id);
		if(!ans.isPresent()) {
			throw new QuestionNotFoundException("No answer found for UserId: " + uId + ", MatrixId: " + qId);
		}
		List<String> res = new ArrayList<>();
		res.add(ans.get().getAnswer1());
		res.add(ans.get().getAnswer2());
		return res;
	}
	
	@GetMapping("/users/{uId}/matrixs")
	public List<Set<String>> checkMatrixs(@PathVariable int uId) {
		
		Optional<User> userOptional = service.findById(uId);

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("id-"+uId);
		}
		
		Set<Matrix> matrixs = userOptional.get().getmQuestions();
		List<Set<String>> res = new ArrayList<>();
		for(Matrix matrix : matrixs) {
			MatrixansId id = new MatrixansId(uId, matrix.getId());
			Optional<Matrixans> ans = matrixansService.findById(id);
			if(!ans.isPresent()) {
				res.add(matrix.getOptions1());
				res.add(matrix.getOptions2());
			}
		}
		if(res.size() == 0) {
			throw new QuestionNotFoundException("No matrix found for UserId: " + uId + " or all were answered");
		}
		return res;
	}
}
