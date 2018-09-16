package com.insticator.spring.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insticator.spring.project.models.questions.Checkans;
import com.insticator.spring.project.models.questions.CheckansId;
import com.insticator.spring.project.models.questions.Checkbox;
import com.insticator.spring.project.models.user.User;

@Repository
public interface CheckansRepository extends JpaRepository<Checkans, CheckansId>{
}
