package com.java.CarConnect.main;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.CarConnect.dao.CustomerService;
import com.java.CarConnect.dao.ICustomerService;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.exception.InvalidInputException;
import com.java.CarConnect.model.Customer;
import com.java.CarConnect.util.ExceptionUtils;

public class UpdateCustomer {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Customer customer = new Customer();
		ICustomerService customerService = new CustomerService();
		try {
		
		int customerId = 0;
		
		boolean isInvalidInput = true;
		boolean isInvalidCustomerId = true;
		do {
			System.out.println("Enter Cutomer Id to be updated : ");
		do {
			try {
				customerId = sc.nextInt();
				sc.nextLine();
				isInvalidInput = false;
			}
			catch(InputMismatchException e)
			{
				sc.nextLine();
				isInvalidInput = true;
				try {
					throw new InvalidInputException("Invalid Input Format. Please enter an"
							+ " Integer value (For e.g. 01)");
				}
				catch(InvalidInputException iie){
					
					System.out.println(iie.getMessage());
				}
				
			}
		}while(isInvalidInput);
		
		customer.setCustomerId(customerId);
		
		
	
			Customer isValidCustomer = customerService.getCustomerById(customerId);
			if(isValidCustomer==null)
			{
				isInvalidCustomerId = true;
				System.out.println("No Matching record exists to Update.");
			}
			else
			{
				isInvalidCustomerId = false;
				System.out.print("Enter First Name : ");
				String firstName = sc.nextLine();
				
				 if (firstName.isEmpty()) {
					 InvalidInputException iie = new InvalidInputException("First Name cannot be empty.");
			        	throw iie ; 
				 }
				 else {
					 customer.setFirstName(firstName);
				 }
				
				System.out.print("Enter Last Name : ");
				String lastName = sc.nextLine();
				 if (lastName.isEmpty()) {
					 InvalidInputException iie = new InvalidInputException("Last Name cannot be empty.");
			        	throw iie ; 
				 }
				 else {
					 customer.setLastName(lastName);
				 }
				
				 String email = "";
					boolean isInvalidEmail = true;
					do {
						System.out.print("Enter Email (It must contain @) : ");
						email = sc.nextLine();
						if(email.contains("@"))
						{
							isInvalidEmail = false;
						}
						else
						{
							isInvalidEmail = true;
							System.out.println("Invalid email entered. "
									+ "Email must contain @");
						}
						
					}while(isInvalidEmail);
					
					customer.setEmail(email);
					
					System.out.print("Enter Phone No : ");
					customer.setPhoneNumber(sc.nextLine());
					
					System.out.print("Enter Address : ");
					customer.setAddress(sc.nextLine());
					
					String username = "";
					boolean isInvalidUsername = true;
					do {
						System.out.print("Enter UserName : ");
						username = sc.nextLine();
						isValidCustomer = customerService.getCustomerByUsername(username);
						
						if (username.isEmpty()) {
							isInvalidUsername = true;
							try {
							 InvalidInputException iie = new InvalidInputException("Username cannot be empty. Please enter a valid Username.");
					        	throw iie ; 
							}
							catch(InvalidInputException iie) {
								System.out.println(iie.getMessage());
							}
						 }
						else if(isValidCustomer!=null)
						{
							try {
								throw new InvalidInputException("Username already exists. "
										+ "Please enter a unique Username.");
							} catch(InvalidInputException iie) {
								System.out.println(iie.getMessage());
							}
						}
						else {
							
							isInvalidUsername = false;
						}
						
					}while(isInvalidUsername); 
					customer.setUserName(username);
					 
					boolean isInvalidPassword = true;
					String password = null;
					do {
						System.out.print("Enter Password : ");
						password = sc.nextLine();
					 if (password.isEmpty()) {
						 
						 isInvalidPassword = true;
						 try {
						 InvalidInputException iie = new InvalidInputException("Password cannot be empty. Please enter "
						 		+ "a valid Password.");
				        	throw iie ; 
						 } catch(InvalidInputException iie) {
							 System.out.println(iie.getMessage());
						 }
					 }
					 else {
						 
						 isInvalidPassword = false;
					 }
					}while(isInvalidPassword);
						 customer.setPassword(password);

					String dateString = null;
					boolean isInvalidDate = true;
					java.util.Date date;
					do {
					System.out.print("Enter Registration Date in (yyyy-mm-dd) format : ");
					dateString = sc.nextLine();
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	                try {
	                     date = dateFormat.parse(dateString);
	                    
	                    // Validate the date is not ahead of the current date
	                    if (date.after(Calendar.getInstance().getTime())) {
	                    	sc.nextLine();
	                    	isInvalidDate = true;
	                        throw new InvalidInputException("Registration date cannot be ahead of current date. "
	                      		+ "Please enter a valid Date. ");
	                    }
	                    else {
	                    	isInvalidDate = false;
	                    }  
	                } catch (ParseException e) {
	                    throw new InvalidInputException("Invalid date format. Please enter date in yyyy-mm-dd format.");
	                }
					}while(isInvalidDate);
					java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	                customer.setRegistrationDate(sqlDate);
		        
			}
		}while(isInvalidCustomerId);
			
			String message = customerService.updateCustomer(customer);
			System.out.println(message);
					
			
		} catch(InvalidInputException iie) {
			System.out.println(iie.getMessage());
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
