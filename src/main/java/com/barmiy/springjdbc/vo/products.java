package com.barmiy.springjdbc.vo;

public class products {
	private Integer productno ;
	private String name ;
	private Integer price ;
	public Integer getProductno() {
		return productno;
	}
	public void setProductno(Integer productno) {
		this.productno = productno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "products [productno=" + productno + ", name=" + name + ", price=" + price + "]";
	}
	

}
