package com.Hotwax;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("loginid");
		String password = request.getParameter("password");
		
//		PrintWriter out = response.getWriter();
//		out.print(userid+password);
		
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root","123456");
			PreparedStatement preparedStatement = connection.prepareStatement("select * from party where partyId =? and password =?");
			preparedStatement.setString(1, userid);
			preparedStatement.setString(2, password);
			
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()){
				session.setAttribute("name", rs.getString("firstName"));
				dispatcher = request.getRequestDispatcher("index.jsp");
			}
			else{
				request.setAttribute("status", "failed");
				dispatcher = request.getRequestDispatcher("login.jsp");
//
			}
			dispatcher.forward(request, response);
//					
//
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
	}

}
		