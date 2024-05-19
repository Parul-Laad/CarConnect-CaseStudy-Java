package com.java.CarConnect.main;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.java.CarConnect.util.ExceptionUtils;


import com.java.CarConnect.dao.IReservationService;

import com.java.CarConnect.dao.ReservationService;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.model.Reservation;

public class GetReservationByCustomerId {
	public static void main(String[] args)
	{
		
		IReservationService reservationService=new ReservationService();
		List<Reservation> reservationList =new ArrayList<>();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter customerId id");
		int i=sc.nextInt();
		try {
			reservationList= reservationService.getReservationByCustomerId(i);
			if(reservationList.size()==0)
			{
				System.out.println("Customer has not done any reservation");
			}
			else
			{
				System.out.println("Total number of reservation customer made : " + reservationList.size());
				
				for (Reservation reservation : reservationList) {
                    System.out.println(reservation);
                }

			}
			
		} catch (ClassNotFoundException e) {
            System.out.println("Database driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            if (ExceptionUtils.isConnectionIssue(e)) {
                DatabaseConnectionException de = new DatabaseConnectionException("Unable to establish a connection to the database.");
                System.out.println(de.getMessage());
                de.printStackTrace();  // Optionally print the stack trace for debugging
            } else {
                System.out.println("SQL error occurred: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }finally {
            sc.close();
        }
    }
}