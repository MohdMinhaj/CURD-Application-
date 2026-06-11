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

@WebServlet("/RetriveAll")
public class RetriveAll extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		PrintWriter pw = response.getWriter();

		try {

			String url = "jdbc:mysql://localhost:3306/excler";
			String user = "root";
			String pass = "Minhaj@123";

			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection c = DriverManager.getConnection(url, user, pass);

			PreparedStatement ps = c.prepareStatement("select * from students");

			ResultSet rs = ps.executeQuery();

			pw.println("<html><body>");
			pw.println("<h2>Student Records</h2>");

			pw.println("<table border='1' cellpadding='10'  >");
			pw.println("<tr>");
			pw.println("<th>ID</th>");
			pw.println("<th>Name</th>");
			pw.println("<th>Address</th>");
			pw.println("</tr>");

			while (rs.next()) {

				pw.println("<tr>");
				pw.println("<td>" + rs.getInt("s_id") + "</td>");
				pw.println("<td>" + rs.getString("s_name") + "</td>");
				pw.println("<td>" + rs.getString("s_address") + "</td>");
				pw.println("</tr>");
			}

			pw.println("</table>");

			pw.println("<br><a href='Home.html'>Back To Home</a>");

			pw.println("</body></html>");

			rs.close();
			ps.close();
			c.close();

		} catch (Exception e) {

			pw.println("<h2>Retrieve Failed</h2>");
			pw.println(e.getMessage());
			e.printStackTrace();

		}
	}
}