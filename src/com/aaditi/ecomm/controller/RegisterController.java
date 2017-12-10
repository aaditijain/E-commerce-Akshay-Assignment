package com.aaditi.ecomm.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterController extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String email = request.getParameter("email");
			String name = request.getParameter("name");
			String q = request.getParameter("password");
			String r = request.getParameter("cpassword");
			String s = request.getParameter("address");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			ServletContext ctx = getServletConfig().getServletContext();
			Class.forName(ctx.getInitParameter("driverClass"));
			Connection con = DriverManager.getConnection(ctx.getInitParameter("url"), ctx.getInitParameter("user"),ctx.getInitParameter("password"));
			PreparedStatement stmt = con.prepareStatement("insert into userinfo values(?,?,?,?,?)");
			stmt.setString(1, email);
			stmt.setString(2, name);
			stmt.setString(3, q);
			stmt.setString(4, r);
			stmt.setString(5, s);
			int rset = stmt.executeUpdate();
			if (rset>0) {
				out.println("<h3>you are successfully Registered</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
				rd.include(request, response);
			} else {
				out.println("<h3>Please try again</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("Register.html");
				rd.include(request, response);
			}
			con.close();
			out.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
