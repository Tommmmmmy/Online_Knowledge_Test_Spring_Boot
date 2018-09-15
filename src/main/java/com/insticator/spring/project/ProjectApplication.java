package com.insticator.spring.project;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.insticator.spring.project.models.questions.Checkbox;
import com.insticator.spring.project.models.user.User;
import com.insticator.spring.project.repository.CheckboxRepository;
import com.insticator.spring.project.repository.UserRepository;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private CheckboxRepository checkboxRepository;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
	@Transactional
	@Override
    public void run(String... args) throws Exception {
        User user = new User("Tommy");
        userRepository.save(user);
        Set<String> options = new HashSet<>();
        options.add("Red");
        options.add("Blue");
        options.add("Yellow");
        options.add("Green");
        options.add("Black");
        options.add("Purple");
        Checkbox check1 = new Checkbox("What are the colors do you like?", user, options);
        Set<Checkbox> questionSet = new HashSet<>();
        questionSet.add(check1);
        user.setcQuestions(questionSet);
        checkboxRepository.save(check1);
        for(Checkbox checkbox : checkboxRepository.findAll()) {
        	System.out.println(checkbox.toString());
        }
    }
}
