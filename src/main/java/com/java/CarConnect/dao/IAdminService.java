package com.java.CarConnect.dao;

import java.sql.SQLException;

import java.util.List;

import com.java.CarConnect.exception.AdminNotFoundException;
import com.java.CarConnect.model.Admin;



public interface IAdminService {
	List<Admin> showAdmin() throws SQLException, ClassNotFoundException;
	Admin getAdminById(int adminID) throws SQLException, ClassNotFoundException;
	Admin getAdminByUsername(String username) throws ClassNotFoundException, SQLException;
	int authenticateAdmin(String user,String pwd) throws ClassNotFoundException, SQLException;
	String  updateAdmin(Admin admin4) throws ClassNotFoundException, SQLException;
	String  deleteAdmin(int adminID ) throws ClassNotFoundException, SQLException, AdminNotFoundException;
	public String registerAdmin(Admin admin3) throws ClassNotFoundException, SQLException;
}
