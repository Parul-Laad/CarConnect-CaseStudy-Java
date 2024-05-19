package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.CarConnect.dao.CustomerService;
import com.java.CarConnect.dao.ICustomerService;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.model.Customer;
import com.java.CarConnect.util.ExceptionUtils;

public class DeleteCustomer {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		
		ICustomerService customerService = new CustomerService();
		
		try {
			
			boolean isInvalidCustomerId = true;
			
			do {
				System.out.print("Enter Customer Id : ");
				int customerId = sc.nextInt();
				Customer customer = customerService.getCustomerById(customerId);
				
				if(customer!= null)
				{
					isInvalidCustomerId = false;
					String message = customerService.deleterCustomer(customerId);
					System.out.println(message);
				}
				else
				{
					isInvalidCustomerId = true;
					System.out.println("No matching Records found to delete.");
				}
			}while(isInvalidCustomerId);
		} catch (ClassNotFoundException e) {
            System.err.println("Database driver class not found. "+e.getMessage());
        } catch (SQLException e) {
        	if(ExceptionUtils.isConnectionIssue(e))
        	{
        		DatabaseConnectionException dce = new DatabaseConnectionException("Unable"
        				+ " to establish a connection with the database.");
        		System.err.println(dce.getMessage());
        	}
        	else
        	{
        		System.err.println(e.getMessage());
        	}
        }
		
	}
}
