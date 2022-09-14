package com.Hotwax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AllRecords
 */
@WebServlet("/allrecords")
public class AllRecordsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ResultSet selectAll() throws SQLException{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root","123456");
		PreparedStatement preparedStatement = connection.prepareStatement("select partyId,firstName,lastName,address,city,zip,state,country,phone,password from party");
		ResultSet rs = preparedStatement.executeQuery();
		
		return rs;

	}
	
	public boolean deleteRecord( int partyId ) throws SQLException{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root","123456");
//		PreparedStatement preparedStatement = connection.prepareStatement();
		Statement s1 = connection.createStatement();
		int j = s1.executeUpdate("Delete from userlogin where partyId="+partyId);
		Statement s = connection.createStatement();
		int i  = s.executeUpdate("Delete from party where partyId ="+partyId);
		
		
		if (i>0){
			return true;
		}
		else{
			return false;
		}

		
	}
	public ResultSet updateRecord(int partyId) throws SQLException{
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root","123456");
		Statement s = connection.createStatement();
		ResultSet rs = s.executeQuery("Select * from party where partyId ="+partyId);
		return rs;
			
	}
}








