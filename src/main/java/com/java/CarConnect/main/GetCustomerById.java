package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.CarConnect.dao.CustomerService;
import com.java.CarConnect.dao.ICustomerService;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.model.Customer;
import com.java.CarConnect.util.ExceptionUtils;
import com.java.CarConnect.exception.InvalidInputException;

public class GetCustomerById {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		boolean isInvalidCustomerId = true;
		try {
		do {
		
		System.out.print("Enter Customer Id : ");
		int customerId = 0;
		
		boolean isInvalidInput = true;
		do {
			try {
				customerId = sc.nextInt();
				System.out.println(customerId);
				isInvalidInput = false;
			}
			catch(InputMismatchException e)
			{
				isInvalidInput = true;
				try {
					throw new InvalidInputException("Invalid Input Format. Please enter an"
							+ " Integer value (For e.g. 01)");
				}
				catch(InvalidInputException iie){
					
					System.err.println(iie.getMessage());
				}
			}
		}while(isInvalidInput);
		
		
		ICustomerService customerService = new CustomerService();
		
		
			Customer customer = customerService.getCustomerById(customerId);
			
			if(customer!=null)
			{
				System.out.println(customer);
				isInvalidCustomerId = false;
			}
			else
			{
				System.out.println("No matching Record Found.Please enter a valid "
						+ "Customer Id.");
				isInvalidCustomerId = true;
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
