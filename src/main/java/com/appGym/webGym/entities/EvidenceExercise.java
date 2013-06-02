package com.appGym.webGym.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table(name = "evidenceExercise")
public class EvidenceExercise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	@Column(name = "evidenceExercise_id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "training_id")
	private Training training;
	@ManyToOne
	@JoinColumn(name = "exercise_id")
	private Exercise exercise;
	@Column(name = "day")
	private Day day;

	public Day getDay() {
		return day;
	}

	public void setDay(Day day) {
		this.day = day;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;

		return true;
	}

	@Override
	public String toString() {
		return "EvidenceExercise [id=" + id + ", training=" + training
				+ ", exercise=" + exercise + ", day=" + day + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

}
