package com.java.CarConnect.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.Test;
import com.java.CarConnect.model.Reservation;
import com.java.CarConnect.model.Status;
import com.java.CarConnect.model.Vehicle;


public class ReservationTest {

    @Test
    public void testDefaultConstructor() {
        Reservation reservation = new Reservation();
        assertNotNull(reservation);
    }

    @Test
    public void testParameterizedConstructor() {
    	Timestamp startDate = Timestamp.valueOf("2024-01-01 13:00:00");
        Timestamp endDate = Timestamp.valueOf("2024-01-10 13:00:00");
        Status status = Status.confirmed; // Assuming Status is an enum
        Reservation reservation = new Reservation(1, 2, 3, 100, status, startDate, endDate);

        assertEquals(1, reservation.getReservationId());
        assertEquals(2, reservation.getCustomerId());
        assertEquals(3, reservation.getVehicleId());
        assertEquals(100, reservation.getTotalCost());
        assertEquals(status, reservation.getStatus());
        assertEquals(startDate, reservation.getStartDate());
        assertEquals(endDate, reservation.getEndDate());
    }

    @Test
    public void testGettersAndSetters() {
        Reservation reservation = new Reservation();
        Timestamp startDate = Timestamp.valueOf("2024-01-01 13:00:00");
        Timestamp endDate = Timestamp.valueOf("2024-01-10 13:00:00");
        Status status = Status.completed; // Assuming Status is an enum

        reservation.setReservationId(2);
        reservation.setCustomerId(3);
        reservation.setVehicleId(4);
        reservation.setTotalCost(200);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setStatus(status);
        
        assertEquals(2, reservation.getReservationId());
        assertEquals(3, reservation.getCustomerId());
        assertEquals(4, reservation.getVehicleId());
        assertEquals(200, reservation.getTotalCost());
        assertEquals(startDate, reservation.getStartDate());
        assertEquals(endDate, reservation.getEndDate());
        assertEquals(status, reservation.getStatus());
    }

    @Test
    public void testCalculateTotalCost() {
        Reservation reservation = new Reservation();
        reservation.calculateTotalCost(5, 50);
        assertEquals(250, reservation.getTotalCost());
    }

    @Test
    public void testToString() {
        Timestamp startDate = Timestamp.valueOf("2024-01-01 13:00:00");
        Timestamp endDate = Timestamp.valueOf("2024-01-10 13:00:00");
        Status status = Status.confirmed; // Assuming Status is an enum
        Reservation reservation = new Reservation(3, 4, 5, 300, status, startDate, endDate);
        reservation.setStatus(status);

        String expected = "Reservation [reservationId=3, customerId=4, vehicleId=5, totalCost=300, status=" + status + ", startDate=" + startDate + ", endDate=" + endDate + "]";
        assertEquals(expected, reservation.toString());
    }
    
    @Test
    public void testHashCode() {
    	Timestamp startDate = Timestamp.valueOf("2024-01-01 13:00:00");
        Timestamp endDate = Timestamp.valueOf("2024-01-10 13:00:00");
        Status status = Status.confirmed; 
        Reservation reservation1 = new Reservation(1, 2, 3, 100, status, startDate, endDate);
        Reservation reservation2 = new Reservation(1, 2, 3, 100, status, startDate, endDate);
        assertEquals(reservation1.hashCode(),reservation2.hashCode());

    }
    
    @Test
    public void testEquals() {
    	Timestamp startDate = Timestamp.valueOf("2024-01-01 13:00:00");
        Timestamp endDate = Timestamp.valueOf("2024-01-10 13:00:00");
        Status status = Status.confirmed;
    	
    	Reservation reservation1 = new Reservation(1, 2, 3, 100, status, startDate, endDate);
        Reservation reservation2 = new Reservation(1, 2, 3, 100, status, startDate, endDate);
        Reservation reservation3 = new Reservation(1, 2, 3, 101, status, startDate, endDate);


        assertTrue(reservation1.equals(reservation2));
        assertFalse(reservation1.equals(reservation3));
    }
}