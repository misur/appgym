package com.appGym.webGym.entities;

import java.util.Date;

import javax.annotation.Nonnull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;

//import com.appGym.webGym.services.UserRole;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonVisual
	@Column(name = "user_id")
	private Long id;
	@Validate("required")
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Validate("required")
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "name")
	private String name;
	@Column(name = "surname")
	private String surname;
	@Column(name = "gender")
	private Gender gender;
	@Column(name = "email")
	private String email;
	@Column(name = "dateOfBirth")
	private Date dateOfBirth;
//	@Column(nullable = false, name = "role")
//	private UserRole role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", surname=" + surname
				+ ", gender=" + gender + ", email=" + email + ", dateOfBirth="
				+ dateOfBirth + "]";
	}

//	public boolean isAdmin() {
//		if (role == UserRole.ADMIN) {
//			return true;
//		}
//		return false;
//	}
//
//	public boolean isEmployee() {
//		if (role == UserRole.EMPLOYEE) {
//			return true;
//		}
//		return false;
//	}

}
