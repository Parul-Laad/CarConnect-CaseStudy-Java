package com.java.CarConnect.util;

import java.sql.SQLException;

public class ExceptionUtils {
	public static boolean isConnectionIssue(SQLException e) {
       
		String sqlState = e.getSQLState();
	    int errorCode = e.getErrorCode();
	    
	    return sqlState != null && (
	            sqlState.startsWith("08") || // Connection-related SQLStates usually start with "08"
	            errorCode == 0 || // MySQL returns 0 for connection issues)
	            errorCode == 1045 || // Specific MySQL error code for authentication failure
	            e.getMessage().toLowerCase().contains("access denied") // Check for error message indicating access denied
	            );
	}
		
    }


