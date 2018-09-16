package com.insticator.spring.project.models.questions.matrix;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.insticator.spring.project.models.questions.Question;
import com.insticator.spring.project.models.user.User;

@Entity
@Table(name = "matrixs")
public class Matrix extends Question{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonIgnore
	private User uUser;
	
	@ElementCollection
    @CollectionTable(name = "matrix_option1", joinColumns = @JoinColumn(name = "matrix_id"))
    @Column(name = "options")
	private Set<String> options1;
	
	@ElementCollection
    @CollectionTable(name = "matrix_option2", joinColumns = @JoinColumn(name = "matrix_id"))
    @Column(name = "options")
	private Set<String> options2;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getuUser() {
		return uUser;
	}

	public void setuUser(User uUser) {
		this.uUser = uUser;
	}

	public Set<String> getOptions1() {
		return options1;
	}

	public void setOptions1(Set<String> options1) {
		this.options1 = options1;
	}

	public Set<String> getOptions2() {
		return options2;
	}

	public void setOptions2(Set<String> options2) {
		this.options2 = options2;
	}

	public Matrix(User uUser, Set<String> options1, Set<String> options2) {
		super(4);
		this.uUser = uUser;
		this.options1 = options1;
		this.options2 = options2;
	}

	public Matrix() {
		super(4);
	}
	
	@Override
	public String toString() {
		String res = "Matrix [id=" + id + "options1=";
		for(String opt : options1 ) {
			res += opt + ",";
		}
		res += " options2=";
		for(String opt : options2 ) {
			res += opt + ",";
		}
		return res;
	}
	
}
