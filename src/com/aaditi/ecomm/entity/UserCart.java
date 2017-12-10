package com.aaditi.ecomm.entity;

import java.util.List;

public class UserCart {
	private String userEmail;
	private List<Product> products;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
