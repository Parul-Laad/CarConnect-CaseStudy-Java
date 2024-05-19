package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.CarConnect.exception.AdminNotFoundException;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.exception.InvalidInputException;
import com.java.CarConnect.model.Admin;
import com.java.CarConnect.dao.AdminService;
import com.java.CarConnect.dao.IAdminService;
import com.java.CarConnect.util.ExceptionUtils;

public class DeleteAdmin {
public static void main(String[] args){
	int adminId=0;
	IAdminService  adminService = new AdminService();
	Scanner sc=new Scanner (System.in);
	
	try {
		
		boolean isInvalidAdminId = true;
		do { 
			boolean isInvalidInput = true;	
			do {
					try {
						isInvalidInput = false;
						System.out.print("Enter the Admin ID you want to delete : ");
						adminId = sc.nextInt();
					} catch(InputMismatchException e) {
						isInvalidInput = true;
						sc.nextLine();
						try {
							throw new InvalidInputException("Invalid Input Format entered. "
									+ "Please enter a valid Admin Id (in Integer e.g. 20)");
						} catch(InvalidInputException iie) {
							System.out.println(iie.getMessage());
						}
					}
				} while(isInvalidInput);
				
				Admin admin = adminService.getAdminById(adminId);
				
				if(admin!=null)
				{
					isInvalidAdminId = false;
					String message = adminService.deleteAdmin(adminId);
					System.out.println(message);
				}
				else {
					isInvalidAdminId = true;
					try {
						throw new AdminNotFoundException("Admin Id does not exists. Please enter a valid "
								+ "Admin Id.");
					} catch(AdminNotFoundException e) {
						System.out.println(e.getMessage());
					}
				}
		}while(isInvalidAdminId);
		adminService.deleteAdmin(adminId);
			
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
    } catch (AdminNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
