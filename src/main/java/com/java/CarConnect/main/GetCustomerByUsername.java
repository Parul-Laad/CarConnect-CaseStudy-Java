package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.CarConnect.dao.CustomerService;
import com.java.CarConnect.dao.ICustomerService;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.model.Customer;
import com.java.CarConnect.util.ExceptionUtils;

public class GetCustomerByUsername {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		
		ICustomerService customerService = new CustomerService();
		
		try {
			
			boolean isInvalidCustomer = true;
			
			do {
				System.out.print("Enter Username : ");
				String username = sc.next();
				
				Customer customer = customerService.getCustomerByUsername(username);
				if(customer!=null)
				{
					System.out.println(customer);
					isInvalidCustomer = false;
				}
				else
				{
					System.out.println("No matching Record Found.Please enter valid username");
					isInvalidCustomer = true;
				}
			}while(isInvalidCustomer);
			
			
			
		} catch (ClassNotFoundException e) {
            System.out.println("Database driver class not found. "+e.getMessage());
        } catch (SQLException e) {
        	if(ExceptionUtils.isConnectionIssue(e))
        	{
        		DatabaseConnectionException dce = new DatabaseConnectionException("Unable"
        				+ " to establish a connection with the database.");
        		System.out.println(dce.getMessage());
        	}
        	else
        	{
        		System.out.println(e.getMessage());
        	}
        }
		
	}
	
}
