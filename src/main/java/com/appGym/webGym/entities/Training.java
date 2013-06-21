package com.appGym.webGym.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

@Entity
@Table(name = "training")
public class Training {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	@Column(name = "training_id")
	private Long id;

	@Validate("required")
	@Column(name = "name", nullable = false)
	private String name;

	@Validate("required")
	@Column(name = "description", nullable = false, columnDefinition="LONGTEXT" ,length=1000)
	private String description;

	@Validate("required")
	@Column(name = "type", nullable = false)
	private String type;
	
	@Validate("required")
	@Column(name = "rating", nullable = false)
	private int rating;
	
	@Validate("required")
	@Column(name = "duration", nullable = false)
	private int duration ;
	

	@Validate("required")
	@Column(name = "price", nullable = false)
	private double price;

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Validate("required")
	@ManyToOne(optional = false)
	@JoinColumn(name = "trainer_id")
	private User trainer;

	public User getTrainer() {
		return trainer;
	}

	public void setTrainer(User trainer) {
		this.trainer = trainer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Training [id=" + id + ", name=" + name + ", description="
				+ description + ", type=" + type + ", rating=" + rating
				+ ", trainer=" + trainer + "]";
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	

}
