package com.nghiait.test.nghiait.test.model;

import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name ="tblProduct")
public class Product {
	// this is primary key
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // auto-increment

	private Long id;
	
	// validate = constraint
	@Column(nullable = false, unique = true, length = 300)
	private String productName;
	private int year;
	private Double price;
	private String url;

	public Product() {

	}
	
	// caculated field = transient(cac truong khong luu trong DB nhung duoc tinh toan tu truong khac)
	@Transient
	private int age; 
	// tuoi se duoc tinh bang nam hien tai - nam sinh
	public int getAge() {
		return Calendar.getInstance().get(Calendar.YEAR) - year;
	}

	public Product( String productName, int year, Double price, String url) {
		this.productName = productName;
		this.year = year;
		this.price = price;
		this.url = url;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", productName=" + productName + ", year=" + year + ", price=" + price + ", url="
				+ url + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	// dinh nghia 2 doi tuong product giong khac nhau
	// loc du lieu de hon
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;
		Product product = (Product) o;
		return year == product.year 
				&& age == product.age 
				&& Objects.equals(id, product.id) 
				&& Objects.equals(productName, product.productName)
				&& Objects.equals(price, product.price) 
				&& Objects.equals(url, product.url); 
		
	
	}
	
	

}
