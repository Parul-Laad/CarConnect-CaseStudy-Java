package com.java.CarConnect.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.java.CarConnect.model.Reservation;
import com.java.CarConnect.model.Status;
import com.java.CarConnect.util.DBConnUtil;
import com.java.CarConnect.util.DBPropertyUtil;

public class ReservationService implements IReservationService {
Connection connection;
PreparedStatement pst;

	@Override
	public String createReservation(Reservation r) throws ClassNotFoundException, SQLException {
		String connStr = DBPropertyUtil.connectionString("db");
		connection = DBConnUtil.getConnection(connStr);
		String command = "INSERT INTO Reservation (ReservationID, CustomerID, VehicleID, StartDate, EndDate, TotalCost, Status) "
				+ "VALUES (?,?,?,?,?,?,?)";
		pst = connection.prepareStatement(command);
		pst.setInt(1,r.getReservationId());
		pst.setInt(2,r.getCustomerId());
		pst.setInt(3,r.getVehicleId());
		pst.setTimestamp(4, new Timestamp(r.getStartDate().getTime()));
		pst.setTimestamp(5, new Timestamp(r.getEndDate().getTime()));
		pst.setInt(6,r.getTotalCost());
		//pst.setString(7, Status.pending.name());
		pst.setString(7, r.getStatus().name());
		int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            return "Reservation record inserted";
        } else {
            return "Failed to add reservation record.";
        }
	}

	@Override
	public String cancelReservation(int reservationId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String connStr = DBPropertyUtil.connectionString("db");
		connection = DBConnUtil.getConnection(connStr);
		String command="delete from reservation where reservationId=?";
		pst = connection.prepareStatement(command);
		pst.setInt(1, reservationId);

		 int rowsAffected = pst.executeUpdate();
	        if (rowsAffected > 0) {
	            return "Reservation Record Deleted";
	        } else {
	            return "Failed to remove reservation record.";
	        }		
	}


	@Override
	public Reservation getReservationById(int id) throws ClassNotFoundException, SQLException {
		String connStr = DBPropertyUtil.connectionString("db");
		connection = DBConnUtil.getConnection(connStr);
		String command="select * from reservation where reservationId=?";
		pst = connection.prepareStatement(command);
		
		pst.setInt(1,id);

		ResultSet rs=pst.executeQuery();
		//Reservation reservation = new Reservation();
		Reservation reservation = null;
		if(rs.next())
		{
			reservation = new Reservation();
			reservation.setReservationId(rs.getInt("reservationId"));
			reservation.setVehicleId(rs.getInt("vehicleId"));
			reservation.setCustomerId(rs.getInt("customerId"));
			reservation.setStartDate(rs.getTimestamp("startDate"));
			reservation.setEndDate(rs.getTimestamp("endDate"));
			reservation.setTotalCost(rs.getInt("totalCost"));
			reservation.setStatus(Status.valueOf(rs.getString("Status")));
		}
		return reservation;
		
	}

	@Override
	public List<Reservation> getReservationByCustomerId(int customerId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		List<Reservation> list =new ArrayList<>();
		String connStr = DBPropertyUtil.connectionString("db");
		connection = DBConnUtil.getConnection(connStr);
		String command="select * from reservation where customerId=?";
		pst = connection.prepareStatement(command);
		
		pst.setInt(1,customerId);

		ResultSet rs=pst.executeQuery();
		
		while(rs.next())
		{
			Reservation reservation=new Reservation();
			reservation.setReservationId(rs.getInt("reservationId"));
			reservation.setVehicleId(rs.getInt("vehicleId"));
			reservation.setCustomerId(rs.getInt("customerId"));
			reservation.setStartDate(rs.getTimestamp("startDate"));
			reservation.setEndDate(rs.getTimestamp("endDate"));
			reservation.setTotalCost(rs.getInt("totalCost"));
			reservation.setStatus(Status.valueOf(rs.getString("Status")));

			list.add(reservation);

		}
		return list;
	}

	
	
	
	@Override
	public int getVehicleCost(int vehicleId) throws SQLException,ClassNotFoundException
	{
		String connStr = DBPropertyUtil.connectionString("db");
		connection = DBConnUtil.getConnection(connStr);
		String command="select dailyRate from vehicle where vehicleId=?";
		pst = connection.prepareStatement(command);
		
		pst.setInt(1,vehicleId);
		int cost=0;
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			cost=rs.getInt("dailyRate");
		}
		return cost;
	}
	
	
	@Override
	public int dateDiff(Timestamp startDate, Timestamp endDate) throws SQLException,ClassNotFoundException
	{
		String connStr = DBPropertyUtil.connectionString("db");
		connection = DBConnUtil.getConnection(connStr);
		String command="select dateDiff(?,?) as date";
		pst = connection.prepareStatement(command);
		pst.setTimestamp(1,endDate);
		pst.setTimestamp(2, startDate);
		ResultSet rs=pst.executeQuery();
		int days=0;
		while(rs.next())
		{
			days=rs.getInt("date");
		}
		return days;
	}

	@Override
	public String updateReservation(Reservation reservation) throws ClassNotFoundException, SQLException {
		String connStr = DBPropertyUtil.connectionString("db");
        connection = DBConnUtil.getConnection(connStr);
        String command = "Update Reservation set CustomerId = ?, vehicleId = ?, startDate = ?, endDate = ?, "
        		+ "totalCost = ?, status = ? where reservationId = ? ";
        pst = connection.prepareStatement(command);
        
        pst.setInt(1, reservation.getCustomerId());
        pst.setInt(2, reservation.getVehicleId());
        pst.setTimestamp(3, reservation.getStartDate());
        pst.setTimestamp(4, reservation.getEndDate());
        pst.setInt(5, reservation.getTotalCost());
        pst.setString(6, reservation.getStatus().name());
        pst.setInt(7, reservation.getReservationId());
        int rowsAffected = pst.executeUpdate();
        if (rowsAffected > 0) {
            return "Reservation record updated successfully.";
        } else {
            return "Failed to update Reservation record.";
        }

	}
	
	public List<Reservation> getReservationsByVehicleId(int vehicleId) throws ClassNotFoundException, SQLException {
	    List<Reservation> reservations = new ArrayList<>();
	    String connStr = DBPropertyUtil.connectionString("db");
	    connection = DBConnUtil.getConnection(connStr);
	    String command = "SELECT * FROM Reservation WHERE VehicleID = ?";
	    pst = connection.prepareStatement(command);
	    pst.setInt(1, vehicleId);
	    ResultSet rs = pst.executeQuery();
	    
	    while (rs.next()) {
	        Reservation reservation = new Reservation();
	        reservation.setReservationId(rs.getInt("ReservationID"));
	        reservation.setCustomerId(rs.getInt("CustomerID"));
	        reservation.setVehicleId(rs.getInt("VehicleID"));
	        reservation.setStartDate(rs.getTimestamp("StartDate"));
	        reservation.setEndDate(rs.getTimestamp("EndDate"));
	        reservation.setTotalCost(rs.getInt("TotalCost"));
	        reservation.setStatus(Status.valueOf(rs.getString("Status")));
	        reservations.add(reservation);
	    }
	    return reservations;
	}
	

	

	

	
}