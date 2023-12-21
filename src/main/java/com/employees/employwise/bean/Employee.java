package com.employees.employwise.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

//@Document(collection = "employees")
@Entity
public class Employee {

	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
	private String employeeName;
	
	
	private String phoneNumber;
	
	@NotBlank(message = "Email cannot be blank")
	@Pattern(regexp = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
	private String email;
	
	@NotNull(message = "'reportsTo' cannot be blank")
	private int reportsTo;
	
	private String profileImage;
	
	public Employee() {
		
	}

	public Employee(int id, String employeeName, String phoneNumber, String email, int reportsTo,
			String profileImage) {
		super();
		this.id = id;
		this.employeeName = employeeName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.reportsTo = reportsTo;
		this.profileImage = profileImage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getReportsTo() {
		return reportsTo;
	}

	public void setReportsTo(int reportsTo) {
		this.reportsTo = reportsTo;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

}
