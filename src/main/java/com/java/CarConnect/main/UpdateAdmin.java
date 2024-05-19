package com.java.CarConnect.main;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.CarConnect.dao.AdminService;
import com.java.CarConnect.dao.IAdminService;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.exception.InvalidInputException;
import com.java.CarConnect.model.Admin;
import com.java.CarConnect.util.ExceptionUtils;

public class UpdateAdmin {
	public static void main(String[] args) 
	{
		IAdminService adminService = new AdminService();
		try {
	
			Admin admin = new Admin();
			Scanner sc=new Scanner(System.in);
			boolean isInvalidAdminId = true;
			boolean isInvalidInput = true;
			int adminId=0;
			do {
				do {
					try {
						isInvalidInput = false;
						System.out.print("Enter Admin Id : ");
						adminId = sc.nextInt();
					} catch(InputMismatchException e) {
						isInvalidInput = true;
						sc.nextLine();
						try {
							throw new InvalidInputException("Invalid input format entered."
									+ " Please enter valid Admin Id in Integer e.g. 40");
						} catch(InvalidInputException iie) {
							System.out.println(iie.getMessage());
						}
					}
				}while(isInvalidInput);
				
				Admin isValidAdmin = adminService.getAdminById(adminId);
				if(isValidAdmin==null) {
					isInvalidAdminId = true;
					System.out.println("No Matching records found. Please enter "
							+ "a valid Admin Id.");
				}
				else {
					isInvalidAdminId = false;
				}
			}while(isInvalidAdminId);
			admin.setAdminID(adminId);
			
			System.out.print("Enter Firstname : ");
			admin.setFirstname(sc.next());
			
			System.out.print("Enter Lastname : ");
			admin.setLastname(sc.next());
			
			String email="";
			isInvalidInput = true;
			do {
				System.out.print("Enter Email  (must contain @) : ");
				email = sc.next();
				if(email.contains("@")) {
					isInvalidInput = false;
				}
				else {
					isInvalidInput = true;
					System.out.println("Invalid email Id (must contain @).");
				}
			} while(isInvalidInput);
			admin.setEmail(email);
			
			System.out.print("Enter PhoneNumber : ");
			admin.setPhone(sc.next());
			
			isInvalidInput = true;
			String username = "";
			do {
				
				System.out.print("Enter Username : ");
				username = sc.next();
				Admin isValidAdmin = adminService.getAdminByUsername(username);
				
				if(isValidAdmin!=null) {
					isInvalidInput = true;
					System.out.println("Username already exists. Please enter a unique"
							+ " username.");
				}
				else {
					isInvalidInput = false;
				}
			}while(isInvalidInput);
			admin.setUsername(username);
			
			System.out.print("Enter Password : ");
			admin.setPassword(sc.next());
			
			String role = "";
			sc.nextLine();
			isInvalidInput = true;
			do {
				System.out.print("Enter Role (Super_admin / Fleet manager) : ");
				role = sc.nextLine();
				
				if(role.toLowerCase().equals("super_admin") || 
						role.toLowerCase().equals("fleet manager")) {
					isInvalidInput = false;
				}
				else {
					isInvalidInput = true;
					System.out.println("Invalid Role entered. Admin role must be "
							+ "SuperAdmin or fleet manager.");
				}
					
			} while(isInvalidInput);
			admin.setRole(role);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String joinDateStr = "";
			Date joinDate;
			do {
				try {
					isInvalidInput = false;
					System.out.print("Enter JoiningDate (in yyyy-mm-dd form) : ");
					joinDateStr = sc.next();

					joinDate = sdf.parse(joinDateStr);
					
					if(joinDate.after(new Date())) {
						isInvalidInput = true;
						throw new InvalidInputException("Entered Date cannot be ahead of current Date.");
					}
				} catch (ParseException e) {
					isInvalidInput = true;
					System.out.println("Invalid Date Format entered. Please enter the "
							+ "date in yyyy-mm-dd format.");
				} catch (InvalidInputException iie) {
					System.out.println(iie.getMessage());
				}
				
			} while(isInvalidInput);
			admin.setJoinDate(joinDateStr);
			
			
			String message = adminService.updateAdmin(admin);
			System.out.println(message);
			
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
