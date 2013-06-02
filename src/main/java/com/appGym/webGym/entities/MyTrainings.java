package com.appGym.webGym.entities;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "mytraining")
@AssociationOverrides({
		@AssociationOverride(name = "pk_training", joinColumns = @JoinColumn(name = "training_id")),
		@AssociationOverride(name = "pk_user", joinColumns = @JoinColumn(name = "user_id")) })
public class MyTrainings {
	@EmbeddedId
	private MyTrainingsPk pk = new MyTrainingsPk();

	public MyTrainingsPk getPk() {
		return pk;
	}

	public void setPk(MyTrainingsPk pk) {
		this.pk = pk;
	}

	
	public void setTrainig(Training training){
		getPk().setTraining(training);
	}

	@Transient
	public Training getTraining() {
		return getPk().getTraining();
	}

	public void setUser(User user){
		getPk().setUser(user);
	}

	@Transient
	public User getUser(){
		return getPk().getUser();
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		MyTrainings that = (MyTrainings) o;
		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}
}
