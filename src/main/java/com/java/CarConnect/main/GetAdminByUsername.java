package com.java.CarConnect.main;

import java.sql.SQLException;

import java.util.Scanner;

import com.java.CarConnect.dao.AdminService;
import com.java.CarConnect.dao.IAdminService;
import com.java.CarConnect.exception.AdminNotFoundException;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.model.Admin;
import com.java.CarConnect.util.ExceptionUtils;

public class GetAdminByUsername {
	public static void main(String[] args) throws AdminNotFoundException {
		 String username;
		Scanner sc=new Scanner (System.in);
		
		
		IAdminService  adminService = new AdminService();
		 try {
			 boolean isInvalidAdminId = true;
			 do {
				 	System.out.print("Enter the Username : ");
					username=sc.next();
					
					Admin admin = adminService.getAdminByUsername(username);
					
					if(admin !=null) {
						isInvalidAdminId = false;
						System.out.println(admin);
					}
					else {
						isInvalidAdminId = true;
						System.out.println("No such username exists. Please enter valid Username");
					}
			 }while(isInvalidAdminId);
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

