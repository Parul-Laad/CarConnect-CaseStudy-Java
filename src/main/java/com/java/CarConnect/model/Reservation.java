package com.java.CarConnect.model;


import java.sql.Timestamp;
import java.util.Objects;

public class Reservation {
private int reservationId;
private int customerId;
private int vehicleId;
private int totalCost;
private Status status;
private Timestamp startDate;
private Timestamp endDate;




public Reservation(int reservationId, int customerId, int vehicleId, int totalCost, Status status, Timestamp startDate,
		Timestamp endDate) {
	this.reservationId = reservationId;
	this.customerId = customerId;
	this.vehicleId = vehicleId;
	this.totalCost = totalCost;
	this.status = status;
	this.startDate = startDate;
	this.endDate = endDate;
}
public Reservation()
{
	
}

public void calculateTotalCost(int days,int cost)
{
	this.totalCost=days*cost;
	this.setTotalCost(totalCost);
}

public int getReservationId() {
	return reservationId;
}

public void setReservationId(int reservationId) {
	this.reservationId = reservationId;
}


@Override
public String toString() {
	return "Reservation [reservationId=" + reservationId + ", customerId=" + customerId + ", vehicleId=" + vehicleId
			+ ", totalCost=" + totalCost + ", status=" + status + ", startDate=" + startDate + ", endDate=" + endDate
			+ "]";
}

public int getCustomerId() {
	return customerId;
}
public void setCustomerId(int customerId) {
	this.customerId = customerId;
}
public int getVehicleId() {
	return vehicleId;
}
public void setVehicleId(int vehicleId) {
	this.vehicleId = vehicleId;
}
public int getTotalCost() {
	return totalCost;
}
public void setTotalCost(int totalCost) {
	this.totalCost = totalCost;
}
public Timestamp getStartDate() {
	return startDate;
}
public void setStartDate(Timestamp startDate) {
	this.startDate = startDate;
}

public Timestamp getEndDate() {
	return endDate;
}
public void setEndDate(Timestamp endDate) {
	this.endDate = endDate;
}

public Status getStatus() {
    return status;
}

public void setStatus(Status status) {
    this.status = status;
}

@Override
public int hashCode() {
	return Objects.hash(reservationId, customerId,vehicleId,totalCost,status, startDate,endDate);
}

@Override
public boolean equals(Object obj) {
	Reservation reservation = (Reservation)obj;
	if (reservation.getReservationId() == reservationId && reservation.getCustomerId() == customerId 
			&& reservation.getVehicleId() == vehicleId && reservation.getTotalCost() == totalCost 
			&& reservation.getStatus() == status && reservation.getStartDate() == startDate
			&& reservation.getEndDate() == endDate )
		return true;
	return false;
	
}


}