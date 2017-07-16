package com.barmiy.springjdbc.vo;

public class orders {
	private Integer orderid ;
	private products products ;
	private Integer quantity ;
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public products getProducts() {
		return products;
	}
	public void setProducts(products products) {
		this.products = products;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "orders [orderid=" + orderid + ", products=" + products + ", quantity=" + quantity + "]";
	}
	

}
