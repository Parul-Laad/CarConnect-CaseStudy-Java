package com.java.CarConnect.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.CarConnect.util.DBPropertyUtil;
import com.java.CarConnect.exception.AdminNotFoundException;
import com.java.CarConnect.model.Admin;
import com.java.CarConnect.util.DBConnUtil;

public class AdminService  implements IAdminService{
	Connection connection;
	PreparedStatement pst;
	
	@Override
	public List<Admin> showAdmin() throws SQLException, ClassNotFoundException {
		String connStr=DBPropertyUtil.connectionString("db");
		connection=DBConnUtil.getConnection(connStr);
		String cmd="select * from Admin";
		pst=connection.prepareStatement(cmd);
		ResultSet rs=pst.executeQuery();
		List<Admin> adminlist=new ArrayList<Admin>();
		Admin  admin1=null;
		while(rs.next())
		{ admin1=new Admin();
			admin1.setAdminID(rs.getInt("AdminId"));
		 admin1.setFirstname(rs.getString("FirstName"));
		 admin1.setLastname(rs.getString("LastName"));
		 admin1.setEmail(rs.getString("Email"));
		 admin1.setPhone(rs.getString("PhoneNumber"));
		 admin1.setUsername(rs.getString("Username"));
		 admin1.setPassword(rs.getString("Password"));
		 admin1.setRole(rs.getString("Role"));
		 admin1.setJoinDate(rs.getString("JoinDate"));
		 adminlist.add(admin1);
		}
		return adminlist;
	}



	@Override
	public Admin getAdminById(int adminID) throws SQLException, ClassNotFoundException 
	{
		String connStr=DBPropertyUtil.connectionString("db");
		connection=DBConnUtil.getConnection(connStr);
		String cmd="select * from Admin where AdminID= ?";
		pst=connection.prepareStatement(cmd);
		pst.setInt(1,adminID);
		ResultSet rs=pst.executeQuery();
		Admin admin1=null;
		if(rs.next())
		{
			admin1=new Admin();
			admin1.setAdminID(rs.getInt("AdminId"));
		 admin1.setFirstname(rs.getString("FirstName"));
		 admin1.setLastname(rs.getString("LastName"));
		 admin1.setEmail(rs.getString("Email"));
		 admin1.setPhone(rs.getString("PhoneNumber"));
		 admin1.setUsername(rs.getString("Username"));
		 admin1.setPassword(rs.getString("Password"));
		 admin1.setRole(rs.getString("Role"));
		 admin1.setJoinDate(rs.getString("JoinDate"));
		}
		
		return admin1;
		
	}
	
//	TO search a customer by username
	
	public Admin getAdminByUsername(String username) throws ClassNotFoundException, SQLException
	{String connStr=DBPropertyUtil.connectionString("db");
	connection=DBConnUtil.getConnection(connStr);
	String cmd="select * from Admin where Username= ?";
	pst=connection.prepareStatement(cmd);
	pst.setString(1,username);
	ResultSet rs=pst.executeQuery();
	Admin admin2=null;
	if(rs.next())
	{
		admin2=new Admin();
		admin2.setAdminID(rs.getInt("AdminId"));
	 admin2.setFirstname(rs.getString("FirstName"));
	 admin2.setLastname(rs.getString("LastName"));
	 admin2.setEmail(rs.getString("Email"));
	 admin2.setPhone(rs.getString("PhoneNumber"));
	 admin2.setUsername(rs.getString("Username"));
	 admin2.setPassword(rs.getString("Password"));
	 admin2.setRole(rs.getString("Role"));
	 admin2.setJoinDate(rs.getString("JoinDate"));
	}
	return admin2;
	}
	
//	To Register new Admin
	
	public String registerAdmin(Admin admin3) throws ClassNotFoundException, SQLException
	{
		String connStr=DBPropertyUtil.connectionString("db");
		connection=DBConnUtil.getConnection(connStr);
		String pwd = EncryptPassword.getCode(admin3.getPassword());
		admin3.setPassword(pwd);
		String cmd = "Insert into Admin(AdminID, FirstName, LastName, Email, PhoneNumber, UserName, Password,Role,JoinDate) "
				+ " values(?,?,?,?,?,?,?,?,?)";
		pst=connection.prepareStatement(cmd);
		pst.setInt(1,admin3.getAdminID());
		pst.setString(2,admin3.getFirstname());
		pst.setString(3,admin3.getLastname());
		pst.setString(4,admin3.getEmail());
		pst.setString(5,admin3.getPhone());
		pst.setString(6,admin3.getUsername());
		pst.setString(7,admin3.getPassword());
		pst.setString(8,admin3.getRole());
		pst.setString(9,admin3.getJoinDate());
		pst.executeUpdate();
		return "Successfully inserted Admin details";
		
	}
	
	public int authenticateAdmin(String user,String pwd) throws ClassNotFoundException, SQLException
	{
		String connStr=DBPropertyUtil.connectionString("db");
		connection=DBConnUtil.getConnection(connStr);
		String encr = EncryptPassword.getCode(pwd);
		String cmd = "select count(*) cont from Customer where UserName = ? "
				+ " AND Password = ?";
		pst = connection.prepareStatement(cmd);
		pst.setString(1, user);
		pst.setString(2, encr);
		ResultSet rs = pst.executeQuery();
		rs.next();
		int cont = rs.getInt("cont");
		return cont;
	}
	
	
	public String updateAdmin(Admin admin4) throws ClassNotFoundException, SQLException
	{
		String connStr=DBPropertyUtil.connectionString("db");
		connection=DBConnUtil.getConnection(connStr);
		String cmd = "UPDATE Admin SET FirstName = ?, LastName = ?, Email = ?, PhoneNumber = ?, Username = ?, Password = ?, Role = ?, JoinDate = ? WHERE AdminID = ?";

		pst=connection.prepareStatement(cmd);
		pst.setString(1,admin4.getFirstname());
		pst.setString(2,admin4.getLastname());
		pst.setString(3,admin4.getEmail());
		pst.setString(4,admin4.getPhone());
		pst.setString(5,admin4.getUsername());
		pst.setString(6,admin4.getPassword());
		pst.setString(7,admin4.getRole());
		pst.setString(8,admin4.getJoinDate());
		pst.setInt(9,admin4.getAdminID() );
		pst.executeUpdate();
		
		return "Admin data updated successfully.";
						
	}
	
	public String  deleteAdmin(int adminID ) throws ClassNotFoundException, SQLException
	{
		String connStr=DBPropertyUtil.connectionString("db");
		connection=DBConnUtil.getConnection(connStr);
		String cmd="delete from Admin where AdminID= ?"; 
		pst=connection.prepareStatement(cmd);
		pst.setInt(1,adminID);
		
		int rowsAffected=pst.executeUpdate();
		
		if(rowsAffected>0)
			return "*** Record Deleted ***";
		return "***Unable to delete Record***";
	}



	

	
	}

	


