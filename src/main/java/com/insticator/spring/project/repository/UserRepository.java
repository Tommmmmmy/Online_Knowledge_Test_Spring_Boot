package com.insticator.spring.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.insticator.spring.project.models.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
}
