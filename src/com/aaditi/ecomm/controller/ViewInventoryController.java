package com.aaditi.ecomm.controller;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.aaditi.ecomm.entity.Product;

public class ViewInventoryController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			ServletContext ctx = getServletConfig().getServletContext();
			Class.forName(ctx.getInitParameter("driverClass"));
			Connection con = DriverManager.getConnection(ctx.getInitParameter("url"), ctx.getInitParameter("user"),
					ctx.getInitParameter("password"));
			PreparedStatement stmt = con.prepareStatement("Select code, name, quantity, price, category from product");
			ResultSet rs = stmt.executeQuery();
			out.println("<table border='1px'>");
			List<Product> prductList = new ArrayList<>();
			while (rs.next()) {
				out.println("<tr>");
				out.println("<td><a href='viewProduct?productCode=" + rs.getString("code") + "'>" + rs.getString("code")
						+ "</a></td>");
				out.println("<td> " + rs.getString("name") + "  </td>");
				out.println("<td> " + rs.getString("quantity") + "  </td>");
				out.println("<td> " + rs.getString("price") + "  </td>");
				out.println("<td> " + rs.getString("category") + "  </td>");
				out.println("</tr>");

				 
				//By Using Collection < Not Used >
				Product p = new Product();
				p.setProductCode(rs.getString("code"));
				p.setName(rs.getString("name"));
				p.setCategory(rs.getString("quantity"));
				p.setPrice(Integer.parseInt(rs.getString("price")));
				p.setQuantity(Integer.parseInt(rs.getString("quantity")));
				prductList.add(p);

				HttpSession session = request.getSession();
				session.setAttribute("productList", prductList);
			}
			out.println("</table>");
			con.close();
			out.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
