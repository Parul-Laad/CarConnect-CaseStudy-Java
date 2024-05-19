package com.java.CarConnect.junit;


import com.java.CarConnect.model.Admin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AdminTest {

    @Test
    public void testDefaultConstructor() {
        Admin admin = new Admin();
        assertNotNull(admin);
    }

    @Test
    public void testParameterizedConstructor() {
        Admin admin = new Admin(1, "John", "Doe", "john.doe@example.com", "1234567890", "johndoe", "password123", "Admin", "2024-01-01");
        assertEquals(1, admin.getAdminID());
        assertEquals("John", admin.getFirstname());
        assertEquals("Doe", admin.getLastname());
        assertEquals("john.doe@example.com", admin.getEmail());
        assertEquals("1234567890", admin.getPhone());
        assertEquals("johndoe", admin.getUsername());
        assertEquals("password123", admin.getPassword());
        assertEquals("Admin", admin.getRole());
        assertEquals("2024-01-01", admin.getJoinDate());
    }

    @Test
    public void testGettersAndSetters() {
        Admin admin = new Admin();
        admin.setAdminID(2);
        admin.setFirstname("Jane");
        admin.setLastname("Smith");
        admin.setEmail("jane.smith@example.com");
        admin.setPhone("0987654321");
        admin.setUsername("janesmith");
        admin.setPassword("securepassword");
        admin.setRole("User");
        admin.setJoinDate("2024-02-01");

        assertEquals(2, admin.getAdminID());
        assertEquals("Jane", admin.getFirstname());
        assertEquals("Smith", admin.getLastname());
        assertEquals("jane.smith@example.com", admin.getEmail());
        assertEquals("0987654321", admin.getPhone());
        assertEquals("janesmith", admin.getUsername());
        assertEquals("securepassword", admin.getPassword());
        assertEquals("User", admin.getRole());
        assertEquals("2024-02-01", admin.getJoinDate());
    }

    @Test
    public void testToString() {
        Admin admin = new Admin(3, "Alice", "Brown", "alice.brown@example.com", "1112223333", "alicebrown", "mypassword", "Manager", "2024-03-01");
        String expected = "Admin [adminID=3, firstname=Alice, lastname=Brown, email=alice.brown@example.com, phone=1112223333, username=alicebrown, password=mypassword, role=Manager, joinDate=2024-03-01]";
        assertEquals(expected, admin.toString());
    }
    

    @Test
    public void testHashCode() {
    	Admin admin1 = new Admin(20,"Akanksha","Sikarwar","as@gmail.com","7539864512",
    			"akanksha123","akasik123","fleet manager","2024-05-11");
    	
    	Admin admin2 = new Admin(20,"Akanksha","Sikarwar","as@gmail.com","7539864512",
    			"akanksha123","akasik123","fleet manager","2024-05-11");
    	
    	assertEquals(admin1.hashCode(), admin2.hashCode());
    }
    
    @Test
    public void testEquals() {
    	Admin admin1 = new Admin(20,"Akanksha","Sikarwar","as@gmail.com","7539864512",
    			"akanksha123","akasik123","fleet manager","2024-05-11");
    	
    	Admin admin2 = new Admin(20,"Akanksha","Sikarwar","as@gmail.com","7539864512",
    			"akanksha123","akasik123","fleet manager","2024-05-11");
    	
    	Admin admin3 = new Admin(20,"Akanksha","Chauhan","as@gmail.com","7539864512",
    			"akanksha123","akasik123","fleet manager","2024-05-11");
    	
    	assertTrue(admin1.equals(admin2));
    	assertFalse(admin1.equals(admin3));
    }
}
