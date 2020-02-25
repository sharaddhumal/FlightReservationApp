package com.flightreservation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Passenger extends AbstractEntity {

	@Column(name = "FIRST_NAME")
	private String fisrtName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Column(name = "MIDDLE_NAME")
	private String midName;
	@Column(name = "EMAIL")
	private String mail;
	@Column(name = "PHONE")
	private String phone;

	public String getFisrtName() {
		return fisrtName;
	}

	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMidName() {
		return midName;
	}

	public void setMidName(String midName) {
		this.midName = midName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
