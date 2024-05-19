package com.java.CarConnect.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class DBConnUtil {

		public static Connection getConnection(String connString) throws ClassNotFoundException, SQLException {
			ResourceBundle rb = ResourceBundle.getBundle("db");
			String driver = rb.getString("driver");
			String user = rb.getString("user");
			String pwd = rb.getString("password");
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(connString, user, pwd);
			return connection;
		}
	}
