package com.nagarro.hr.manager.api.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Table(name = "employees")
public class Employee {

	@Id
	private long code;
	private String name;
	private String Location;
	private String email;
	private LocalDate dateOfBirth;
	@ManyToMany(mappedBy = "employees", cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.PERSIST })
	@JsonIgnore
	private List<HrManager> hrManagers = new ArrayList<>();

	public long getCode() {
		return code;
	}

	public void setCode(long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<HrManager> getHrManagers() {
		return hrManagers;
	}

	public void setHrManagers(List<HrManager> hrManagers) {
		this.hrManagers = hrManagers;
	}
	
	public Employee fetchThisObject() {
		return this;
	} 

	@Override
	public String toString() {
		return "Employee [code=" + code + ", name=" + name + ", Location=" + Location + ", email=" + email
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}

}
