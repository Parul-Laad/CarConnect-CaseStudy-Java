package com.java.CarConnect.junit;


import com.java.CarConnect.model.Customer;

import java.sql.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CustomerTest {

    @Test
    public void testDefaultConstructor() {
        Customer customer = new Customer();
        assertNotNull(customer);
    }

    @Test
    public void testParameterizedConstructor() {
        Date registrationDate = Date.valueOf("2024-01-01");
        Customer customer = new Customer(1, "John", "Doe", "john.doe@example.com", "1234567890", "123 Main St",
                "johndoe", "password123", registrationDate);
        assertEquals(1, customer.getCustomerId());
        assertEquals("John", customer.getFirstName());
        assertEquals("Doe", customer.getLastName());
        assertEquals("john.doe@example.com", customer.getEmail());
        assertEquals("1234567890", customer.getPhoneNumber());
        assertEquals("123 Main St", customer.getAddress());
        assertEquals("johndoe", customer.getUserName());
        assertEquals("password123", customer.getPassword());
        assertEquals(registrationDate, customer.getRegistrationDate());
    }

    @Test
    public void testGettersAndSetters() {
        Customer customer = new Customer();
        Date registrationDate = Date.valueOf("2024-02-01");
        
        customer.setCustomerId(2);
        customer.setFirstName("Jane");
        customer.setLastName("Smith");
        customer.setEmail("jane.smith@example.com");
        customer.setPhoneNumber("0987654321");
        customer.setAddress("456 Elm St");
        customer.setUserName("janesmith");
        customer.setPassword("securepassword");
        customer.setRegistrationDate(registrationDate);

        assertEquals(2, customer.getCustomerId());
        assertEquals("Jane", customer.getFirstName());
        assertEquals("Smith", customer.getLastName());
        assertEquals("jane.smith@example.com", customer.getEmail());
        assertEquals("0987654321", customer.getPhoneNumber());
        assertEquals("456 Elm St", customer.getAddress());
        assertEquals("janesmith", customer.getUserName());
        assertEquals("securepassword", customer.getPassword());
        assertEquals(registrationDate, customer.getRegistrationDate());
    }

    @Test
    public void testToString() {
        Date registrationDate = Date.valueOf("2024-03-01");
        Customer customer = new Customer(3, "Alice", "Brown", "alice.brown@example.com", "1112223333", "789 Oak St",
                "alicebrown", "mypassword", registrationDate);
        String expected = "Customer [customerId=3, firstName=Alice, lastName=Brown, email=alice.brown@example.com, phoneNumber=1112223333, address=789 Oak St, userName=alicebrown, password=mypassword, registrationDate=2024-03-01]";
        assertEquals(expected, customer.toString());
    }
    
    @Test
    public void testHashCode() {
    	 Date registrationDate = Date.valueOf("2024-03-01");
         Customer customer1 = new Customer(3, "Alice", "Brown", "alice.brown@example.com", "1112223333", "789 Oak St",
                 "alicebrown", "mypassword", registrationDate);
         Customer customer2 = new Customer(3, "Alice", "Brown", "alice.brown@example.com", "1112223333", "789 Oak St",
                 "alicebrown", "mypassword", registrationDate);
        assertEquals(customer1.hashCode(),customer2.hashCode());

    }
    
    @Test
    public void testEquals() {
    	 Date registrationDate = Date.valueOf("2024-03-01");
         Customer customer1 = new Customer(3, "Alice", "Brown", "alice.brown@example.com", "1112223333", "789 Oak St",
                 "alicebrown", "mypassword", registrationDate);
         Customer customer2 = new Customer(3, "Alice", "Brown", "alice.brown@example.com", "1112223333", "789 Oak St",
                 "alicebrown", "mypassword", registrationDate);
         Customer customer3 = new Customer(3, "Alice", "Brown", "alice.brown@example.com", "1122334455", "789 Oak St",
                 "alicebrown", "mypassword", registrationDate);

        assertTrue(customer1.equals(customer2));
        assertFalse(customer1.equals(customer3));
    }
}