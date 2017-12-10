package com.aaditi.ecomm.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaditi.ecomm.entity.Product;
import com.aaditi.ecomm.entity.User;

public class ViewCartController extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			//Product product = (Product) request.getSession().getAttribute("addToCartProduct");
			User user = (User)request.getSession().getAttribute("user");

			ServletContext ctx = getServletConfig().getServletContext();
			Class.forName(ctx.getInitParameter("driverClass"));
			Connection con = DriverManager.getConnection(ctx.getInitParameter("url"), ctx.getInitParameter("user"),
					ctx.getInitParameter("password"));
			PreparedStatement ps = con
					.prepareStatement("Select  Quantity, ProductCode from userCartInfo where Email=?");
			ps.setString(1,  user.getEmail());
			ResultSet rs = ps.executeQuery();
			
			out.println("<table border='1px'>");
			while (rs.next()) {
			out.println("<tr>");
			out.println("<td>Code</td>");
			out.println("<td>" + rs.getString("ProductCode") + "</td>");
			
			out.println("<td>Quantity</td>");
			out.println("<td> " + rs.getString( "Quantity") + "</td>");
			out.println("</tr>");
			}

	
	
	
	
	
	
}catch(Exception e )
		{
		}
		}}