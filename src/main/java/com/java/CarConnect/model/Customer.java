package com.java.CarConnect.model;

import java.sql.Date;
import java.util.Objects;

//import com.google.common.base.Objects;


public class Customer {

	private int customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String address;
	private String userName;
	private String password;
	private Date registrationDate;
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", userName=" + userName
				+ ", password=" + password + ", registrationDate=" + registrationDate + "]";
	}
	public Customer() {

	}
	public Customer(int customerId, String firstName, String lastName, String email, String phoneNumber, String address,
			String userName, String password, Date registrationDate) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.userName = userName;
		this.password = password;
		this.registrationDate = registrationDate;
	}
	@Override
	public int hashCode() {
        return Objects.hash(address, customerId, email, firstName, lastName, password, phoneNumber, registrationDate, userName);
    }
	@Override
	public boolean equals(Object obj) {
		Customer customer = (Customer) obj;
		if (customer.getCustomerId() == customerId && customer.getFirstName() == firstName 
				&& customer.getLastName() == lastName && customer.getEmail() == email 
				&& customer.getPhoneNumber() == phoneNumber && customer.getAddress() == address
				&& customer.getUserName() == userName && customer.getPassword() == password
				&& customer.getRegistrationDate() == registrationDate)
			return true;
		return false;
	}
}