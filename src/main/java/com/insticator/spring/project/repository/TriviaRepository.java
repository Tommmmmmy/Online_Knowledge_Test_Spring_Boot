package com.insticator.spring.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insticator.spring.project.models.questions.Checkbox.Checkbox;
import com.insticator.spring.project.models.questions.Trivia.Trivia;
import com.insticator.spring.project.models.user.User;

@Repository
public interface TriviaRepository extends JpaRepository<Trivia, Integer>{
	List<Trivia> findByUUserAndId(User user, int id);
}
