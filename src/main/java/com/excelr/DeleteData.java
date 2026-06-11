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

@WebServlet("/DeleteData")
public class DeleteData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter pw = response.getWriter();

		int s_id = Integer.parseInt(request.getParameter("s_id"));

		try {

			String url = "jdbc:mysql://localhost:3306/excler";
			String user = "root";
			String pass = "Minhaj@123";

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection c = DriverManager.getConnection(url, user, pass);

			PreparedStatement ps = c.prepareStatement("DELETE FROM students WHERE s_id=?");

			ps.setInt(1, s_id);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				pw.println("<h2>Data Deleted Successfully</h2>");
			} else {
				pw.println("<h2>No Record Found With ID : " + s_id + "</h2>");
			}

			pw.println("<br><a href='Home.html'>Back To Home</a>");

			ps.close();
			c.close();

		} catch (Exception e) {

			pw.println("<h2>Delete Failed</h2>");
			pw.println(e.getMessage());
			e.printStackTrace();

		}
	}
}