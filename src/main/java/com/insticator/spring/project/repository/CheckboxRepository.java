package com.insticator.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insticator.spring.project.models.questions.Checkbox;
import com.insticator.spring.project.models.questions.Question;

@Repository
public interface CheckboxRepository extends JpaRepository<Checkbox, Integer>{
}
