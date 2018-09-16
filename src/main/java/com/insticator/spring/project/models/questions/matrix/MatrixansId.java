package com.insticator.spring.project.models.questions.matrix;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MatrixansId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -500580649244346680L;
	@Column(name = "user_id")
    private int userId;
    @Column(name = "matrix_id")
    private int matrixId;
	public MatrixansId(int userId, int matrixId) {
		this.userId = userId;
		this.matrixId = matrixId;
	}
	public MatrixansId() {
	}
}
