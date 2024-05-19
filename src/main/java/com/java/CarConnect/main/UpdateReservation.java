package com.java.CarConnect.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.*;

import com.java.CarConnect.dao.CustomerService;
import com.java.CarConnect.dao.IReservationService;
import com.java.CarConnect.dao.ICustomerService;
import com.java.CarConnect.dao.IVehicleService;
import com.java.CarConnect.dao.VehicleService;
import com.java.CarConnect.dao.ReservationService;
import com.java.CarConnect.model.Customer;
import com.java.CarConnect.model.Reservation;
import com.java.CarConnect.model.Status;
import com.java.CarConnect.model.Vehicle;
import com.java.CarConnect.util.ExceptionUtils;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.exception.InvalidInputException;
import com.java.CarConnect.exception.ReservationException;
import com.java.CarConnect.exception.VehicleNotFoundException;

import java.sql.Date;

public class UpdateReservation {
    public static void main(String[] args) {
        IReservationService reservationService = new ReservationService();
        ICustomerService customerService = new CustomerService();
        IVehicleService vehicleService = new VehicleService();

        Reservation reservation = new Reservation();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter reservation details:");

            int reservationId = 0;
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Enter reservation Id to update:");
                try {
                    reservationId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    if(reservationId < 0)
                    {
                    	throw new InvalidInputException("Invalid input. Please enter a valid integer greateer than  0 for reservation ID.");             
                    }
                    validInput = true;
                } catch (InputMismatchException e) {
                    scanner.nextLine(); // Clear the invalid input
                    System.out.println("Invalid input. Please enter a valid integer for reservation ID.");
                }catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }
            reservation.setReservationId(reservationId);

            Reservation isValidReservation = reservationService.getReservationById(reservationId);
            if (isValidReservation == null) {
                System.out.println("Reservation Id " + reservationId + " does not exist. Enter a valid ReservationId.");
                return;
            }

            int customerId = 0;
            Customer customer = null;
            do {
                validInput = false;
                while (!validInput) {
                    System.out.println("Enter customer Id");
                    try {
                        customerId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        validInput = true;
                    } catch (InputMismatchException e) {
                        scanner.nextLine(); // Clear the invalid input
                        throw new InvalidInputException("Invalid input. Please enter a valid integer for customer ID.");
                    }
                }
                try {
                    customer = customerService.getCustomerById(customerId);
                    if (customer == null) {
                        System.out.println("Customer with ID " + customerId + " does not exist. Please enter a valid customer ID.");
                    }
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
            } while (customer == null);
            reservation.setCustomerId(customerId);

            int vehicleId = 0;
            Vehicle vehicle = null;

            while (true) {
                validInput = false;
                while (!validInput) {
                    System.out.println("Enter vehicle Id:");
                    try {
                        vehicleId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        validInput = true;
                    } catch (InputMismatchException e) {
                        scanner.nextLine(); // Clear the invalid input
                        throw new InvalidInputException("Invalid input. Please enter a valid integer for vehicle ID.");
                    }
                }
                try {
                    vehicle = vehicleService.getVehicleByID(vehicleId);
                    if (vehicle == null) {
                        throw new VehicleNotFoundException("Vehicle with ID " + vehicleId + " does not exist. Please enter a valid vehicle ID.");
                    }
                    else if(!vehicle.isAvailability()) {
                        System.out.println("The vehicle with vehicle Id  " + vehicleId + " exists but is currently unavailable for reservation.");
                        continue;
                    }

                    List<Reservation> vehicleReservations = reservationService.getReservationsByVehicleId(vehicleId);

                    System.out.println("Enter start date (yyyy-MM-dd HH:mm:ss):");
                    String startDateTimeStr = scanner.nextLine();
                    System.out.println("Enter end date (yyyy-MM-dd HH:mm:ss):");
                    String endDateTimeStr = scanner.nextLine();

                    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date startDate = new Date(dateTimeFormat.parse(startDateTimeStr).getTime());
                    Date endDate = new Date(dateTimeFormat.parse(endDateTimeStr).getTime());

                    if (startDate.after(endDate)) {
                        System.out.println("Start date cannot be after end date.");
                        continue; // Prompt for date input again
                    }
                                       
                  boolean isAvailable = true;
                  for (Reservation res : vehicleReservations) {
                      if (!(endDate.before(res.getStartDate()) || startDate.after(res.getEndDate()))) {
                          if (res.getStatus() == Status.confirmed) {
                        	  isAvailable = false;
                              throw new ReservationException("Vehicle with ID " + vehicleId + " is already reserved during the selected period.");
                          }
                          else {

                        	  continue;
                          } 
                       } 
                   }
                    
                    

                    if (isAvailable) {
                        reservation.setVehicleId(vehicleId);
                        reservation.setStartDate(new Timestamp(startDate.getTime()));
                        reservation.setEndDate(new Timestamp(endDate.getTime()));
                        System.out.println("Vehicle with ID " + vehicleId + " is available for reservation.");
                        break; // Exit the loop if reservation is successful
                    }

                } catch (ReservationException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please enter a different vehicle ID or change the dates for reservation.");
                } catch (ParseException e) {
                    System.out.println("Invalid date-time format: " + e.getMessage());
                    System.out.println("Enter correct date-time format.");
                } catch (ClassNotFoundException e) {
                    System.out.println("Database driver class not found: " + e.getMessage());
                    break;
                } catch (SQLException e) {
                    if (ExceptionUtils.isConnectionIssue(e)) {
                        DatabaseConnectionException de = new DatabaseConnectionException("Unable to establish a connection to the database.");
                        System.out.println(de.getMessage());
                    } else {
                        System.out.println("SQL error occurred: " + e.getMessage());
                    }
                    break;
                } catch (VehicleNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }

            int cost;
            try {
                cost = reservationService.getVehicleCost(vehicleId);
                int days = reservationService.dateDiff(reservation.getStartDate(), reservation.getEndDate());
                reservation.calculateTotalCost(days, cost);
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

            while(true) {
	         	   try {       
			          System.out.println("Enter reservation status (pending, confirmed, completed):");
			          String statusStr = scanner.nextLine();
			
			          if (statusStr.equalsIgnoreCase("pending") || statusStr.equalsIgnoreCase("confirmed") || statusStr.equalsIgnoreCase("completed")) {
			              Status status = Status.valueOf(statusStr.toLowerCase());
			              reservation.setStatus(status);
			              break;
			          } else {
			              throw new InvalidInputException("Invalid reservation status. Please enter 'pending', 'confirmed', or 'completed'.");
			          }
	         	  } catch (InvalidInputException e) {
	                   System.out.println(e.getMessage());
	                   continue;
	               }
            }
	           
		
		            String result = reservationService.updateReservation(reservation);
		            System.out.println(result);
          

        } catch (ClassNotFoundException e) {
            System.out.println("Database driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            if (ExceptionUtils.isConnectionIssue(e)) {
                DatabaseConnectionException de = new DatabaseConnectionException("Unable to establish a connection to the database.");
                System.out.println(de.getMessage());
            } else {
                System.out.println("SQL error occurred: " + e.getMessage());
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        } finally {
            scanner.close();
        }
    }

}