package com.insticator.spring.project.models.questions.Poll;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.insticator.spring.project.models.questions.Checkbox.CheckansId;
import com.insticator.spring.project.models.questions.Checkbox.Checkbox;
import com.insticator.spring.project.models.user.User;

@Entity
@Table(name = "pollanss")
public class Pollans {
	@EmbeddedId
    private PollansId id;
	
	@ManyToOne
    @JoinColumn(name="poll_id", insertable=false, updatable=false)
	private Poll poll;
	
	@ManyToOne
    @JoinColumn(name="user_id", insertable=false, updatable=false)
	private User user;
	
	@ElementCollection
    @CollectionTable(name = "user_poll_answers", joinColumns = {@JoinColumn(name = "poll_id"), @JoinColumn(name = "user_id")})
    @Column
	private String answer;

	public PollansId getId() {
		return id;
	}

	public void setId(PollansId id) {
		this.id = id;
	}

	public Poll getPoll() {
		return poll;
	}

	public void setPoll(Poll poll) {
		this.poll = poll;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Pollans(PollansId id, Poll poll, User user, String answer) {
		this.id = id;
		this.poll = poll;
		this.user = user;
		this.answer = answer;
	}

	public Pollans() {
	}

}
