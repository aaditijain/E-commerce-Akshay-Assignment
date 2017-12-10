package com.aaditi.ecomm.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aaditi.ecomm.entity.Product;
import com.aaditi.ecomm.entity.User;

public class AddToCartController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			Product product = (Product) request.getSession().getAttribute("addToCartProduct");
			User user = (User)request.getSession().getAttribute("user");

			//DB Code to save data into UserCartInfo table
			ServletContext ctx = getServletConfig().getServletContext();
			Class.forName(ctx.getInitParameter("driverClass"));
			Connection con = DriverManager.getConnection(ctx.getInitParameter("url"), ctx.getInitParameter("user"),ctx.getInitParameter("password"));
			PreparedStatement stmt = con.prepareStatement("insert into userCartInfo values(?,?,?)");
			//user.getEmail(), product.getProductCode(), quantity()
			stmt.setString(1, user.getEmail());
			stmt.setString(2, product.getProductCode());
			stmt.setInt(3, product.getQuantity());
			int rset = stmt.executeUpdate();
			if (rset>0) {
				out.println("<h3>Your product is successfully added into the cart</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("ViewInventoryController");
				rd.include(request, response);
			} else {
				out.println("<h3>Please try again</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("ViewInventoryController");
				rd.include(request, response);
			}
			
			
			

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
