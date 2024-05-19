package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.java.CarConnect.dao.IReservationService;

import com.java.CarConnect.dao.ReservationService;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.exception.VehicleNotFoundException;
import com.java.CarConnect.model.Reservation;
import com.java.CarConnect.util.ExceptionUtils;

public class GetReservationById {
	public static void main(String[] args)
	{
		Reservation reservation = new Reservation();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter reservation id");
		int reservationId = sc.nextInt();
		IReservationService reservationService =new ReservationService();

		try {
			reservation = reservationService.getReservationById(reservationId);
			
			if(reservation!=null)
			{
				
				System.out.println(reservation);
			}
			else
			{
				System.out.println("No reservation exists with this Reservation ID.");

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