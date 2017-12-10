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

public class AddProductController extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code = request.getParameter("product_code");
			String name = request.getParameter("name");
			String category = request.getParameter("category");
			String price = request.getParameter("price");
			String quantity = request.getParameter("quantity");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			ServletContext ctx = getServletConfig().getServletContext();
			Class.forName(ctx.getInitParameter("driverClass"));
			Connection con = DriverManager.getConnection(ctx.getInitParameter("url"), ctx.getInitParameter("user"),
					ctx.getInitParameter("password"));
			PreparedStatement stmt = con.prepareStatement("insert into product values(?,?,?,?,?)");
			stmt.setString(1, code);
			stmt.setString(2, name);
			stmt.setString(3, category);
			stmt.setString(4, price);
			stmt.setString(5, quantity);
			int rset = stmt.executeUpdate();
			if (rset > 0) {
				out.println("<h3>Product is successfully added.</h3>");
				out.println("<h3>Do you want to add more product.</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("addInventory.html");
				out.println("<a href='profileController'>Back to Profile</a>");
				rd.include(request, response);
			} else {
				out.println("<h3>Please try again</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("addInventory.html");
				rd.include(request, response);
			}
			con.close();
			out.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
