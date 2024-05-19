package com.java.CarConnect.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.java.CarConnect.model.Vehicle;

public class VehicleTest {

    @Test
    public void testDefaultConstructor() {
        Vehicle vehicle = new Vehicle();
        assertNotNull(vehicle);
    }

	@Test
    public void testParameterizedConstructor() {
        Vehicle vehicle = new Vehicle(1, "Model S", "Tesla", 2020, "Red", "ABC123", true, 99.99);

        assertEquals(1, vehicle.getVehicleId());
        assertEquals("Model S", vehicle.getModel());
        assertEquals("Tesla", vehicle.getMake());
        assertEquals(2020, vehicle.getYear());
        assertEquals("Red", vehicle.getColor());
        assertEquals("ABC123", vehicle.getRegistrationNumber());
        assertTrue(vehicle.isAvailability());
        assertEquals(99.99, vehicle.getDailyRate(), 0.001);
    }

	@Test
    public void testGettersAndSetters() {
        Vehicle vehicle = new Vehicle();

        vehicle.setVehicleId(2);
        vehicle.setModel("Mustang");
        vehicle.setMake("Ford");
        vehicle.setYear(2021);
        vehicle.setColor("Blue");
        vehicle.setRegistrationNumber("XYZ456");
        vehicle.setAvailability(false);
        vehicle.setDailyRate(79.99);

        assertEquals(2, vehicle.getVehicleId());
        assertEquals("Mustang", vehicle.getModel());
        assertEquals("Ford", vehicle.getMake());
        assertEquals(2021, vehicle.getYear());
        assertEquals("Blue", vehicle.getColor());
        assertEquals("XYZ456", vehicle.getRegistrationNumber());
        assertFalse(vehicle.isAvailability());
        assertEquals(79.99, vehicle.getDailyRate(), 0.001);
    }

    @Test
    public void testToString() {
        Vehicle vehicle = new Vehicle(3, "Civic", "Honda", 2019, "Black", "DEF789", true, 59.99);
        String expected = "Vehicle [vehicleId=3, model=Civic, make=Honda, year=2019, color=Black, registrationNumber=DEF789, availability=true, dailyRate=59.99]";
        assertEquals(expected, vehicle.toString());
    }
    
    @Test
    public void testHashCode() {
        Vehicle vehicle1 = new Vehicle(1, "Model S", "Tesla", 2020, "Red", "ABC123", true, 99.99);
        Vehicle vehicle2 = new Vehicle(1, "Model S", "Tesla", 2020, "Red", "ABC123", true, 99.99);
        assertEquals(vehicle1.hashCode(),vehicle2.hashCode());

    }
    
    @Test
    public void testEquals() {
    	Vehicle vehicle1 = new Vehicle(1, "Model S", "Tesla", 2020, "Red", "ABC123", true, 99.99);
        Vehicle vehicle2 = new Vehicle(1, "Model S", "Tesla", 2020, "Red", "ABC123", true, 99.99);
        Vehicle vehicle3 = new Vehicle(1, "Model S", "Tesla", 2021, "Red", "ABC123", true, 99.99);

        assertTrue(vehicle1.equals(vehicle2));
        assertFalse(vehicle1.equals(vehicle3));
    }
    }