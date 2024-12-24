package com.health.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
public class Patient {
	 @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Long pid;
	    
	    @NotBlank(message = "Name is mandatory")
	    private String name;
	    
	    @Email(message = "Invalid email address")
	    private String email;
	    
	    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
	    private String number;
	    
	    @NotBlank(message = "Address is mandatory")
	    private String address;
	    
	    @NotBlank(message="password is mandatory")
	    private String password;

		public Patient() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Patient(Long pid, @NotBlank(message = "Name is mandatory") String name,
				@Email(message = "Invalid email address") String email,
				@Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits") String number,
				@NotBlank(message = "Address is mandatory") String address,
				@NotNull(message = "password is mandatory") String password) {
			super();
			this.pid = pid;
			this.name = name;
			this.email = email;
			this.number = number;
			this.address = address;
			this.password = password;
		}

		public Long getPid() {
			return pid;
		}

		public void setPid(Long pid) {
			this.pid = pid;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Override
		public String toString() {
			return "Patient [pid=" + pid + ", name=" + name + ", email=" + email + ", number=" + number + ", address="
					+ address + ", password=" + password + "]";
		}
	    
}
