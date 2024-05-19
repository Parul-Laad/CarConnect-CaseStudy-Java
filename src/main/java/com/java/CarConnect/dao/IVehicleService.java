package com.java.CarConnect.dao;

import java.sql.SQLException;
import java.util.List;

import com.java.CarConnect.exception.DatabaseConnectionException;
import com.java.CarConnect.exception.InvalidInputException;
import com.java.CarConnect.model.Vehicle;

public interface IVehicleService {
	Vehicle getVehicleByID(int vehicleID) throws ClassNotFoundException, SQLException;
	List<Vehicle> getAvailableVehicles() throws SQLException, ClassNotFoundException;
	String addVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException, InvalidInputException;
	String updateVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException;
	String removeVehicle(Vehicle vehicle) throws ClassNotFoundException, SQLException, DatabaseConnectionException;
	boolean registrationNumberExists(String registrationNumber) throws ClassNotFoundException, SQLException;
}