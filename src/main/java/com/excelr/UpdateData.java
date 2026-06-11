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

@WebServlet("/UpdateData")
public class UpdateData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter pw = response.getWriter();

		int s_id = Integer.parseInt(request.getParameter("s_id"));
		String s_name = request.getParameter("s_name");
		String s_address = request.getParameter("s_address");

		try {
			String url = "jdbc:mysql://localhost:3306/excler";
			String user = "root";
			String pass = "Minhaj@123";

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection c = DriverManager.getConnection(url, user, pass);

			PreparedStatement ps = c.prepareStatement("UPDATE students SET s_name=?, s_address=? WHERE s_id=?");

			ps.setString(1, s_name);
			ps.setString(2, s_address);
			ps.setInt(3, s_id);

			int rows = ps.executeUpdate();

			if (rows > 0) {
				pw.println("<h2>Data Updated Successfully</h2>");
			} else {
				pw.println("<h2>No Record Found With ID : " + s_id + "</h2>");
			}

			pw.println("<br><a href='Home.html'>Back To Home</a>");

			ps.close();
			c.close();

		} catch (Exception e) {
			pw.println("<h2>Update Failed</h2>");
			pw.println(e.getMessage());
			e.printStackTrace();
		}
	}
}