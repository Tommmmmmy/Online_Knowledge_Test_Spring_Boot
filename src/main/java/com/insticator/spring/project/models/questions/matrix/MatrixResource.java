package com.insticator.spring.project.models.questions.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.insticator.spring.project.models.questions.QuestionNotFoundException;
import com.insticator.spring.project.models.questions.Checkbox.Checkbox;
import com.insticator.spring.project.repository.CheckboxRepository;
import com.insticator.spring.project.repository.MatrixRepository;

@RestController
public class MatrixResource {
	@Autowired
	private MatrixRepository service;
	
	@GetMapping("/matrixs")
	public Set<Set<String>> retrieveAllMatrix() {
		Set<Set<String>> res = new HashSet<>();
		List<Matrix> list = service.findAll();
		if (list.size() == 0) {
			throw new QuestionNotFoundException("matrix");
		}
		for(Matrix matrix : list) {
			res.add(retrieveMatrix(matrix.getId()));
		}
		return res;
	}
	
	@GetMapping("/matrixs/{id}")
	public Set<String> retrieveMatrix(@PathVariable int id) {
		
		Optional<Matrix> matrixOptional = service.findById(id);

		if (!matrixOptional.isPresent()) {
			throw new QuestionNotFoundException("id-"+id);
		}
		
		Matrix matrix = matrixOptional.get();
		
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
		return list;
	}
	
	@GetMapping("/matrixs/{id}/options")
	public Set<Set<String>> retrieveAllOptionsForOne(@PathVariable int id) {
		
		Optional<Matrix> matrix = service.findById(id);

		if (!matrix.isPresent()) {
			throw new QuestionNotFoundException("id-"+id);
		}
		
		Matrix question = matrix.get();
		
		Set<Set<String>> res = new HashSet<>();
		res.add(question.getOptions1());
		res.add(question.getOptions2());
		
		return res;
	}
}
