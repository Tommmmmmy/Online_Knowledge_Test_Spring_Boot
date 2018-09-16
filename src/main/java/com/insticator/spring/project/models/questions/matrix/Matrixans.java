package com.insticator.spring.project.models.questions.matrix;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.insticator.spring.project.models.questions.Poll.Poll;
import com.insticator.spring.project.models.questions.Poll.PollansId;
import com.insticator.spring.project.models.user.User;

@Entity
@Table(name = "matrixanss")
public class Matrixans {
	@EmbeddedId
    private MatrixansId id;
	
	@ManyToOne
    @JoinColumn(name="matrix_id", insertable=false, updatable=false)
	private Matrix matrix;
	
	@ManyToOne
    @JoinColumn(name="user_id", insertable=false, updatable=false)
	private User user;
	
	private String answer1;
	
	private String answer2;

	public MatrixansId getId() {
		return id;
	}

	public void setId(MatrixansId id) {
		this.id = id;
	}

	public Matrix getMatrix() {
		return matrix;
	}

	public void setMatrix(Matrix matrix) {
		this.matrix = matrix;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public Matrixans(MatrixansId id, Matrix matrix, User user, String answer1, String answer2) {
		this.id = id;
		this.matrix = matrix;
		this.user = user;
		this.answer1 = answer1;
		this.answer2 = answer2;
	}

	public Matrixans() {
	}
	
	
}
