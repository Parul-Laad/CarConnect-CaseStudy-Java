package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.java.CarConnect.dao.IVehicleService;
import com.java.CarConnect.dao.VehicleService;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.exception.InvalidInputException;
import com.java.CarConnect.exception.VehicleNotFoundException;
import com.java.CarConnect.model.Vehicle;
import com.java.CarConnect.util.ExceptionUtils;

public class AddVehicle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IVehicleService vehicleService = new VehicleService();

        System.out.println("Enter vehicle details:");

        // Get vehicle details from the user
        Vehicle vehicle = new Vehicle();
        
        try {
	        System.out.println("Enter Vehicle Id : ");
			int vehicleId = scanner.nextInt();
			vehicle.setVehicleId(vehicleId);
			scanner.nextLine();
			if (vehicleId < 0) {
	            throw new InvalidInputException("Invalid input. Please enter a non-negative integer.");
	        }

			Vehicle isValidVehicle = vehicleService.getVehicleByID(vehicleId);
			
			if(isValidVehicle!=null)
			{
            	VehicleNotFoundException ve = new VehicleNotFoundException("Vehicle Id already exists.");
            	throw ve;
			}
			else
			{
		        System.out.print("Enter Model: ");
		        vehicle.setModel(scanner.nextLine());

		        System.out.print("Enter Make: ");
		        vehicle.setMake(scanner.nextLine());

		        boolean validYear = false;
		        int year = 0;

		        do {
		            System.out.print("Enter Year: ");
		            String yearInput = scanner.nextLine();

		            try {
		                year = Integer.parseInt(yearInput);
		                validYear = (year > 0 && year <= Calendar.getInstance().get(Calendar.YEAR));

		                if (!validYear) {
		                    throw new InvalidInputException("Invalid year. Please enter a valid year.");	               
		                }

		                vehicle.setYear(year);
		            } catch (NumberFormatException e) {
		                System.out.println("Invalid input type for year. Please enter a valid year.");
		                scanner.nextLine();
		            } catch (InvalidInputException iie) {
		                System.out.println(iie.getMessage());
		            }
		            
		        } while (!validYear);

		        System.out.print("Enter Color: ");
		        vehicle.setColor(scanner.nextLine());
		       
		        
		        String regNumber = "";
		        do {
                    System.out.print("Enter Registration Number: ");
                    regNumber = scanner.nextLine();

                    if (regNumber.isEmpty()) {
                        InvalidInputException iie = new InvalidInputException("Registration number cannot be empty.");
                        throw iie;
                    }

                    vehicle.setRegistrationNumber(regNumber);

                    if (vehicleService.registrationNumberExists(regNumber)) {
                        System.out.println("Registration number already exists. Please enter a unique registration number.");
                    }

                } while(vehicleService.registrationNumberExists(regNumber));

		        

		        
		        System.out.print("Enter Availability (TRUE/FALSE): ");
		        String availabilityInput = scanner.nextLine().toLowerCase(); // Convert input to lowercase for case-insensitive comparison
		        if (availabilityInput.equals("true")) {
		            vehicle.setAvailability(true);
		        } else if (availabilityInput.equals("false")) {
		            vehicle.setAvailability(false);
		        } else {
		            InvalidInputException iie =  new InvalidInputException("Invalid input for Availability. Please enter 'true' or 'false'.");
		            throw iie;
		        }
		        
		        System.out.print("Enter Daily Rate: ");
		        double dailyRate = scanner.nextDouble();
		        if (dailyRate <= 0) {
		            InvalidInputException iie = new InvalidInputException("Daily rate must be greater than 0.");
		            throw iie;
		        }
		        vehicle.setDailyRate(dailyRate);
		        
		      try {
		            String result = vehicleService.addVehicle(vehicle);
		            System.out.println(result);
			
			} catch (SQLException e) {
		        if (ExceptionUtils.isConnectionIssue(e)) {
		        	DatabaseConnectionException de = new DatabaseConnectionException("Unable to establish a connection to the database.");
	                System.out.println(de.getMessage());
	                }
		        else {
		            System.out.println("SQL error occurred: " + e.getMessage());
		        }
		    }
			}
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.nextLine();
        } catch (ClassNotFoundException e) {
            System.out.println("Database driver class not found: " + e.getMessage());
        } catch (InvalidInputException iie) {
        	System.out.println(iie.getMessage());
        } catch (SQLException e) {
        	if (ExceptionUtils.isConnectionIssue(e)) {
	        	DatabaseConnectionException de = new DatabaseConnectionException("Unable to establish a connection to the database.");
                System.out.println(de.getMessage());
                }
	        else {
	            System.out.println("SQL error occurred: " + e.getMessage());
	        }
		}catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } 
    }
}