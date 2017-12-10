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
import javax.servlet.http.HttpSession;

import com.aaditi.ecomm.entity.Product;

public class ViewProductController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			ServletContext ctx = getServletConfig().getServletContext();
			Class.forName(ctx.getInitParameter("driverClass"));
			Connection con = DriverManager.getConnection(ctx.getInitParameter("url"), ctx.getInitParameter("user"),
					ctx.getInitParameter("password"));
			PreparedStatement ps = con
					.prepareStatement("Select code, name, quantity, price, category from product where code=?");
			ps.setString(1, request.getParameter("productCode"));
			ResultSet rs = ps.executeQuery();
			out.println("<table border='1px'>");
			Product p = new Product();
			while (rs.next()) {
				out.println("<tr>");
				out.println("<td>Code</td>");
				out.println("<td>" + rs.getString("code") + "</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Code</td>");
				out.println("<td> " + rs.getString("name") + "  </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Code</td>");
				out.println("<td> " + rs.getString("quantity") + "  </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Code</td>");
				out.println("<td> " + rs.getString("price") + "  </td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td>Code</td>");
				out.println("<td> " + rs.getString("category") + "  </td>");
				out.println("</tr>");
				// By Using Collection
				p.setProductCode(rs.getString("code"));
				p.setName(rs.getString("name"));
				p.setCategory(rs.getString("quantity"));
				p.setPrice(Integer.parseInt(rs.getString("price")));
				p.setQuantity(Integer.parseInt(rs.getString("quantity")));

			}
			out.println("</table>");

			// out.println("<input type='text' name='quantity' /> ");
			int quantity = 1;

			out.println("<a href='addToCart?quantity=" + quantity + "'>Add to Cart</a>");
			HttpSession session = request.getSession();
			session.setAttribute("addToCartProduct", p);
			con.close();
			out.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
