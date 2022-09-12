package com.Hotwax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/Signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("Workinnnn");
		
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String country = request.getParameter("country");
		String phone = request.getParameter("phone");
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		RequestDispatcher dispatcher = 	null;
		Connection connection= null;
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root","123456");
			
			int party = 0;
			PreparedStatement preparedStatement1 = connection.prepareStatement("select max(partyId) from party");
			ResultSet rs = preparedStatement1.executeQuery();
			if (rs.next()){
				party = rs.getInt(1);
				party++;
			PreparedStatement preparedStatement = connection.prepareStatement("insert into party(partyId,firstname,lastname,address,city,zip,state,country,phone,password) values(?,?,?,?,?,?,?,?,?,?)");
			
			preparedStatement.setInt(1, party);
			preparedStatement.setString(2, firstName);
			preparedStatement.setString(3, lastName);
			preparedStatement.setString(4, address);
			preparedStatement.setString(5, city);
			preparedStatement.setString(6, zip);
			preparedStatement.setString(7, state);
			preparedStatement.setString(8, country);
			preparedStatement.setString(9, phone);
			preparedStatement.setString(10, password);
			
			int rowCount = preparedStatement.executeUpdate();
			dispatcher = request.getRequestDispatcher("signup.jsp");
			if (rowCount>0){
				request.setAttribute("status", "success");
			}
			else{
				request.setAttribute("status", "failed");
			}
			}
			dispatcher.forward(request, response);
			
			
//			PreparedStatement preparedStatement2 = connection.prepareStatement("insert into userlogin (userLoginId, password, partyId) values(?,?,?)");
//			preparedStatement2.setString(1, userid);
//			preparedStatement2.setString(2, password);
//			preparedStatement2.setInt(3, party);
//			ResultSet rs1 = preparedStatement2.executeQuery(); 
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
//		out.println(firstName + password + zip);
	}

}
