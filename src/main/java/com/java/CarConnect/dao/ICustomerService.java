package com.java.CarConnect.dao;

import java.sql.SQLException;

import com.java.CarConnect.model.Customer;

public interface ICustomerService {

	Customer getCustomerById(int customerId) throws ClassNotFoundException, SQLException;
	Customer getCustomerByUsername(String username) throws ClassNotFoundException, SQLException;
	String registerCustomer(Customer customer) throws ClassNotFoundException, SQLException;
	String updateCustomer(Customer customerData) throws ClassNotFoundException, SQLException;
	String deleterCustomer(int customerId) throws ClassNotFoundException, SQLException;
	int authenticateCustomer(String user, String password) throws ClassNotFoundException, SQLException;
}
