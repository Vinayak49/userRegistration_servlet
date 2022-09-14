package com.Hotwax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName = request.getParameter("fname");
		String lastName = request.getParameter("lname");
		Connection connection= null;
		
		PrintWriter out = response.getWriter();
//		out.print(firstName+lastName);
		
		
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root","123456");
			PreparedStatement preparedStatement = connection.prepareStatement("select partyId,firstName,lastName,address,city,zip,state,country,phone,password from party where firstName = ? and lastName= ?");
			
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
//			RequestDispatcher dispatcher = 	null;
//			HttpSession session = request.getSession();
			
			ResultSet rs =  preparedStatement.executeQuery();
			if (rs.next()){
				request.setAttribute("partyId", rs.getString("partyId"));
				request.setAttribute("fname", rs.getString("firstName"));
				request.setAttribute("lname", rs.getString("lastName"));
				request.setAttribute("address", rs.getString("address"));
				request.setAttribute("city", rs.getString("city"));
				request.setAttribute("zip", rs.getString("zip"));
				request.setAttribute("state", rs.getString("state"));
				request.setAttribute("coutnry", rs.getString("country"));
				request.setAttribute("phone", rs.getString("phone"));
				request.setAttribute("password", rs.getString("password"));
				request.getRequestDispatcher("searchresult.jsp").forward(request, response);
			}
			else{
				out.print("No user found");
			}
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
	}


}
