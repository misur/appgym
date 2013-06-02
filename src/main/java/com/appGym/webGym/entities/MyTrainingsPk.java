package com.appGym.webGym.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class MyTrainingsPk implements Serializable {
	@ManyToOne
	private Training training;
	@ManyToOne
	private User user;
	
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MyTrainingsPk that = (MyTrainingsPk) o;
		if (training != null ? !training.equals(that.training)
				: that.training != null)
			return false;
		if (user != null ? !user.equals(that.user)
				: that.user != null)
			return false;
		return true;
	}
}
