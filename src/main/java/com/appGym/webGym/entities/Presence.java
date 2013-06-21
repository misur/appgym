package com.appGym.webGym.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;

@Entity
@Table(name = "presence")
public class Presence {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	@Column(name = "presence_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "timetable")
	private TimetableE	 timetable;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name="checkpresence")
	private boolean checkPresence =false;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public TimetableE getTimetable() {
		return timetable;
	}
	public void setTimetable(TimetableE timetable) {
		this.timetable = timetable;
	}
	public boolean isCheckPresence() {
		return checkPresence;
	}
	public void setCheckPresence(boolean checkPresence) {
		this.checkPresence = checkPresence;
	}

	
	
	
}
