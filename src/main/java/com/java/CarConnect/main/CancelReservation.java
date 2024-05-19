package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.CarConnect.dao.IReservationService;
import com.java.CarConnect.dao.ReservationService;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.exception.InvalidInputException;
import com.java.CarConnect.model.Reservation;
import com.java.CarConnect.util.ExceptionUtils;


public class CancelReservation {
public static void main(String[] args)
{

	Scanner scanner =new Scanner(System.in);
	IReservationService reservationService = new ReservationService();

	try {
		System.out.println("Enter reservation id");
		int reservationId = scanner.nextInt();
		if (reservationId < 0) {
	        throw new InvalidInputException("Invalid input. Please enter a non-negative integer.");
	    }
		
		
		
		Reservation reservation = reservationService.getReservationById(reservationId);
		if(reservation!=null) {
			String message = reservationService.cancelReservation(reservationId);
			System.out.println(message);
		}
		else {
			System.out.println("The reservation Id "+ reservationId +" does not exist.");
		}
		
	
	
	    
    } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please enter an integer.");
        scanner.nextLine();
	 } catch (InvalidInputException iie) {
            System.out.println(iie.getMessage());
    } catch (ClassNotFoundException e) {
        System.out.println("Database driver class not found: " + e.getMessage());
    } catch (SQLException e) {
        if (ExceptionUtils.isConnectionIssue(e)) {
            DatabaseConnectionException de = new DatabaseConnectionException("Unable to establish a connection to the database.");
            System.out.println(de.getMessage());
        } else {
            System.out.println("SQL error occurred: " + e.getMessage());
        }

    }
}
}