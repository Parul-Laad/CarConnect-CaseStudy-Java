package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.CarConnect.dao.IVehicleService;
import com.java.CarConnect.dao.VehicleService;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.exception.InvalidInputException;
import com.java.CarConnect.exception.VehicleNotFoundException;
import com.java.CarConnect.model.Vehicle;
import com.java.CarConnect.util.ExceptionUtils;

public class RemoveVehicle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {

        try {
        System.out.println("Enter Vehicle ID to delete: ");
        int vehicleId = scanner.nextInt();
        
        if (vehicleId < 0) {
            throw new InvalidInputException("Invalid input. Please enter a non-negative integer.");
        }
        
        IVehicleService vehicleService = new VehicleService();
        

            Vehicle vehicle = vehicleService.getVehicleByID(vehicleId);
            
            if (vehicle != null) {

                    String message = vehicleService.removeVehicle(vehicle);
                    System.out.println(message);
                    break;

            } else {
                VehicleNotFoundException ve = new VehicleNotFoundException("No matching record exists to remove.");
                throw ve;
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
        } catch (VehicleNotFoundException ve) {
            System.out.println(ve.getMessage());
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
        }
    }
}