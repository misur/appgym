package com.appGym.webGym.entities;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

@Entity
@Table(name = "exercise")
public class Exercise {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@NonVisual
	@Column(name="exercise_id")
	private Long id;
	@Column(name="type")
	@Validate("required")
	private String type;
	@Column(name="description")
	private String description;
	@Column(name="videoLink")
	private String videoLink;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getVideoLink() {
		return videoLink;
	}
	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}
	@Override
	public String toString() {
		return "Exercise [id=" + id + ", type=" + type + ", description="
				+ description + ", videoLink=" + videoLink + "]";
	}
	
	

}
