package com.aaditi.ecomm.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aaditi.ecomm.entity.User;

public class ProfileController extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
		processRequest(req, res);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
		processRequest(req, res);
	}

	private static void processRequest(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		out.println("<h2>Welcome," + user.getUserName() + "</h2>");
		out.println("<a href='viewInventoryController'>View Product</a>");
		out.println("<a href='addInventory.html'>Add Product</a>");
		out.println("<a href='viewCart'>View Cart</a>");

		out.close();

	}

}
