package com.insticator.spring.project;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.insticator.spring.project.models.questions.Checkbox.Checkbox;
import com.insticator.spring.project.models.questions.Poll.Poll;
import com.insticator.spring.project.models.questions.Trivia.Trivia;
import com.insticator.spring.project.models.questions.matrix.Matrix;
import com.insticator.spring.project.models.user.User;
import com.insticator.spring.project.repository.CheckboxRepository;
import com.insticator.spring.project.repository.MatrixRepository;
import com.insticator.spring.project.repository.PollRepository;
import com.insticator.spring.project.repository.TriviaRepository;
import com.insticator.spring.project.repository.UserRepository;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private CheckboxRepository checkboxRepository;
	
	@Autowired
    private TriviaRepository triviaRepository;
	
	@Autowired
    private PollRepository pollRepository;
	
	@Autowired
    private MatrixRepository matrixRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
	@Transactional
	@Override
    public void run(String... args) throws Exception {
		//add a user
        User user1 = new User("Tommy");
        userRepository.save(user1);
        
        //add a checkbox question
        Set<String> options = new HashSet<>();
        options.add("Red");
        options.add("Blue");
        options.add("Yellow");
        options.add("Green");
        options.add("Black");
        options.add("Purple");
        Checkbox check1 = new Checkbox("What are the colors do you like?", user1, options);
        Set<Checkbox> questionSet = new HashSet<>();
        questionSet.add(check1);
        user1.setcQuestions(questionSet);
        checkboxRepository.save(check1);
        
        //add a trivia question "Which team won the 2017 super bowl?"
        options = new HashSet<>();
        options.add("Falcons");
        options.add("Patriots");
        Trivia trivia = new Trivia("Which team won the 2017 super bowl?", user1, options, "Patriots");
        Set<Trivia> tquestionSet = new HashSet<>();
        tquestionSet.add(trivia);
        user1.settQuestions(tquestionSet);
        triviaRepository.save(trivia);
        
        //add a poll question "What's your favorite car brand?"
        options = new HashSet<>();
        options.add("Nissan");
        options.add("Honda");
        options.add("Audi");
        options.add("BMW");
        Poll poll = new Poll("What's your favorite car brand?", user1, options);
        Set<Poll> pquestionSet = new HashSet<>();
        pquestionSet.add(poll);
        user1.setpQuestions(pquestionSet);
        pollRepository.save(poll);
        
        //add a matrix question
        Set<String> options1 = new HashSet<>();
        options1.add("<18");
        options1.add("18 to 35");
        options1.add("35 to 55");
        options1.add("> 55");
        Set<String> options2 = new HashSet<>();
        options2.add("Male");
        options2.add("Female");
        Matrix matrix = new Matrix(user1, options1, options2);
        Set<Matrix> mquestionSet = new HashSet<>();
        mquestionSet.add(matrix);
        user1.setmQuestions(mquestionSet);
        matrixRepository.save(matrix);
        
        for(Checkbox checkbox : checkboxRepository.findAll()) {
        	System.out.println(checkbox.toString());
        }
    }
}
