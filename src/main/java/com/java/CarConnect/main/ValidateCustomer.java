package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.CarConnect.exception.AuthenticationException;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.util.ExceptionUtils;
import com.java.CarConnect.dao.CustomerService;

public class ValidateCustomer {
	
	public static void main(String[] args) {
	
		String username, password;
		Scanner sc = new Scanner(System.in);
		
		try {
			boolean isInvalidCredentials = true;
			do {
		System.out.print("Enter UserName : ");
		username = sc.next();
		
		System.out.print("Enter Password : ");
		password = sc.next();
		
		CustomerService customerService = new CustomerService();
		
			int count = customerService.authenticateCustomer(username, password);
			try {
			if(count==1)
			{
				isInvalidCredentials = false;
				System.out.println("Correct Credentials.");

			}	
			else
			{
				isInvalidCredentials = true;
				AuthenticationException ae = new AuthenticationException("Invalid Credentails.");
				throw ae;
			}
			} catch (AuthenticationException ae) {
	            System.out.println(ae.getMessage());
			} 
			}while(isInvalidCredentials);    
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
