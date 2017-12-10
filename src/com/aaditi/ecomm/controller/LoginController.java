package com.aaditi.ecomm.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aaditi.ecomm.entity.User;

public class LoginController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		try {

			res.setContentType("text/html");
			PrintWriter out = res.getWriter();
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			ServletContext ctx = getServletConfig().getServletContext();

			Class.forName(ctx.getInitParameter("driverClass"));
			Connection con = DriverManager.getConnection(ctx.getInitParameter("url"), ctx.getInitParameter("user"),
					ctx.getInitParameter("password"));

			PreparedStatement stmt = con.prepareStatement("select * from  userinfo where email=? and password=?");
			stmt.setString(1, email);
			stmt.setString(2, password);
			java.sql.ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				
				HttpSession session = req.getSession();
				User user = new User();
				user.setEmail(email);
				user.setAddress(rs.getString("address"));
				user.setUserName(rs.getString("name"));
				session.setAttribute("user", user);

				RequestDispatcher rd = req.getRequestDispatcher("profileController");
				rd.forward(req, res);
			} else {
				out.println("<h3>Please try again</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("index.html");
				rd.include(req, res);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
