package com.insticator.spring.project.models.questions.Checkbox;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CheckansId implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1282975732533521289L;
	@Column(name = "user_id")
    private int userId;
    @Column(name = "checkbox_id")
    private int checkboxId;
	public CheckansId(int userId, int checkboxId) {
		this.userId = userId;
		this.checkboxId = checkboxId;
	}
	public CheckansId() {
	}
    
    
}
