package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.CarConnect.exception.AdminNotFoundException;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.exception.InvalidInputException;
import com.java.CarConnect.dao.AdminService;
import com.java.CarConnect.dao.IAdminService;
import com.java.CarConnect.model.Admin;
import com.java.CarConnect.util.ExceptionUtils;

public class GetAdminById {
public static void main(String[] args) {
	int adminId = 0;
	Scanner sc=new Scanner (System.in);
	
	try {
			IAdminService adminService = new AdminService();
			
			boolean isInvalidInput = true;
			boolean isInvalidadminId = true;
		do {
				
			do {
				System.out.print("Enter the Admin ID : ");
				
					try {
						isInvalidInput = false;
						adminId=sc.nextInt();
					}catch(InputMismatchException e) {
						isInvalidInput = true;
						sc.nextLine();
						try {
							throw new InvalidInputException("Invalid Input Format. Please enter"
									+ " an Integer value (For e.g. 01)");
						} catch(InvalidInputException iie) {
							System.out.println(iie.getMessage());
						}
					}
			}while(isInvalidInput);
			// -->> to check for invalid input -> String in place of integer
			
			
			Admin admin = adminService.getAdminById(adminId);
			
			if(admin !=null) {
				isInvalidadminId = false;
				System.out.println(admin);
			}
			else {
				System.out.println("**The Admin is not Registered");
				isInvalidadminId = true;
			}
			
		}while(isInvalidadminId);	
		
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
