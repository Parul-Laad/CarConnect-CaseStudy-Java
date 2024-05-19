package com.java.CarConnect.main;

import java.sql.SQLException;
import java.util.List;

import com.java.CarConnect.dao.IVehicleService;
import com.java.CarConnect.dao.VehicleService;
import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.exception.VehicleNotFoundException;
import com.java.CarConnect.model.Vehicle;
import com.java.CarConnect.util.ExceptionUtils;

public class GetAvailableVehicles {
    public static void main(String[] args) {
        IVehicleService vehicleService = new VehicleService();
        try {
            List<Vehicle> availableVehicles = vehicleService.getAvailableVehicles();
            if (!availableVehicles.isEmpty()) {
                for (Vehicle vehicle : availableVehicles) {
                    System.out.println(vehicle);
                }
            } else {
            	VehicleNotFoundException ve = new VehicleNotFoundException("No available vehicles.");
            	throw ve;
            }
       
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver class not found: " + e.getMessage());
        } catch (SQLException e) {
            if (ExceptionUtils.isConnectionIssue(e)) {
                DatabaseConnectionException de = new DatabaseConnectionException("Unable to establish a connection to the database.");
                System.out.println(de.getMessage());
            } else {
                System.out.println("SQL error occurred: " + e.getMessage());
            }
        }catch (VehicleNotFoundException ve) {
            	System.out.println(ve.getMessage());
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        } 
    }
}