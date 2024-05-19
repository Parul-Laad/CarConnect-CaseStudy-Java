package com.java.CarConnect.model;

import java.util.Objects;

public class Admin {
	private int adminID;
	private String firstname;
	private String lastname;
	private String email;
	private String phone;
	private String username; 
	private String password;
	private String role;
	private String joinDate;
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}
	
	@Override
	public String toString() {
		return "Admin [adminID=" + adminID + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", phone=" + phone + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", joinDate=" + joinDate + "]";
	}
	
	public Admin() {
	
		// TODO Auto-generated constructor stub
	}
	public Admin(int adminID, String firstname, String lastname, String email, String phone, String username,
			String password, String role, String joinDate) {
		
		this.adminID = adminID;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.phone = phone;
		this.username = username;
		this.password = password;
		this.role = role;
		this.joinDate = joinDate;
	}
	@Override
    public int hashCode() {
        return Objects.hash(adminID, firstname, lastname, email, phone, username, password, role, joinDate);
    }
	
	@Override
    public boolean equals(Object obj) {
        Admin admin = (Admin) obj;
        return adminID == admin.adminID &&
                Objects.equals(firstname, admin.firstname) &&
                Objects.equals(lastname, admin.lastname) &&
                Objects.equals(email, admin.email) &&
                Objects.equals(phone, admin.phone) &&
                Objects.equals(username, admin.username) &&
                Objects.equals(password, admin.password) &&
                Objects.equals(role, admin.role) &&
                Objects.equals(joinDate, admin.joinDate);
    }
	
}
