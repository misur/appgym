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
@Table(name = "more")
public class MoreInformation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	@Column(name = "moreinformation_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@Column(name = "weight")
	private int weight;
	
	@Column(name = "height")
	private int height;
	
	@Column(name = "fatPercentage")
	private int fatPercentage;

	@Column(name = "percentageOfMuscleMass")
	private int percentageOfMuscleMass;
	
	@Column(name = "volumeOfArms")
	private int volumeOfArms;
	
	@Column(name = "extentOfLegs")
	private int extentOfLegs;
	
	@Column(name = "vaist")
	private int vaist;

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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getFatPercentage() {
		return fatPercentage;
	}

	public void setFatPercentage(int fatPercentage) {
		this.fatPercentage = fatPercentage;
	}


	public int getVolumeOfArms() {
		return volumeOfArms;
	}

	public void setVolumeOfArms(int volumeOfArms) {
		this.volumeOfArms = volumeOfArms;
	}

	public int getExtentOfLegs() {
		return extentOfLegs;
	}

	public void setExtentOfLegs(int extentOfLegs) {
		this.extentOfLegs = extentOfLegs;
	}

	public int getVaist() {
		return vaist;
	}

	public void setVaist(int vaist) {
		this.vaist = vaist;
	}

	public int getPercentageOfMuscleMass() {
		return percentageOfMuscleMass;
	}

	public void setPercentageOfMuscleMass(int percentageOfMuscleMass) {
		this.percentageOfMuscleMass = percentageOfMuscleMass;
	}
	
	
	
}
