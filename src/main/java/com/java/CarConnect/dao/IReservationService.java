package com.java.CarConnect.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.java.CarConnect.model.Reservation;

public interface IReservationService {
String createReservation(Reservation r) throws ClassNotFoundException,SQLException;
String cancelReservation(int reservationId) throws ClassNotFoundException,SQLException;
//public String updateReservation(int id,String status) throws ClassNotFoundException,SQLException;
Reservation getReservationById(int id) throws ClassNotFoundException,SQLException;
List<Reservation> getReservationByCustomerId(int customerId )throws ClassNotFoundException,SQLException;
String updateReservation(Reservation reservation) throws ClassNotFoundException,SQLException;
List<Reservation> getReservationsByVehicleId(int vehicleId) throws ClassNotFoundException, SQLException;
int getVehicleCost(int vehicleId) throws SQLException, ClassNotFoundException;
int dateDiff(Timestamp startDate, Timestamp endDate) throws SQLException, ClassNotFoundException;





}