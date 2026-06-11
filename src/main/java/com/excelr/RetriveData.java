package com.excelr;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class RetriveData
 */
@WebServlet("/RetriveData")
public class RetriveData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
//		int s_id = Integer.parseInt((request.getParameter("s_id");
		
		int s_id = Integer.parseInt(request.getParameter("s_id"));
		
		PrintWriter pw = response.getWriter();
		
		try {
			String url = "jdbc:mysql://localhost:3306/excler";
			String user = "root";
			String pass = "Minhaj@123";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection c = DriverManager.getConnection(url,user,pass);
			
			PreparedStatement ps = c.prepareStatement("select * from students where s_id=?");
			
			ps.setInt(1, s_id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				pw.println("<h2>Student Details</h2>");
				
				pw.println("<b> Student ID: </b>" + rs.getInt(1) + "<br>");
				pw.println("<b> Student Name: </b>" + rs.getString("s_name") + "<br>");
				pw.println("<b> Student Address: </b>" + rs.getString("s_address") + "<br><br>");
			} 
			else {
				pw.println("<h2>No Records Found</h2>");
			}
			
			pw.println("<a href='Home.html'> Back to Home </a>");
			
		} catch(Exception e) {
			System.out.println("Query Failed");
			System.out.println(e.getMessage());
		}
	}

}
