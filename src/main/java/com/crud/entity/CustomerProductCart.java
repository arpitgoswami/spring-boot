package com.crud.entity;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="customerProductCart")
public class CustomerProductCart {

	
	@Id
	@Column(name = "cartId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cartId;
	
	@ManyToOne
//	@JoinColumn(name = "customerId", nullable = false,insertable = false,updatable = false)
	private Customer customer;
	
	@ManyToOne
//	@JoinColumn(name = "product_id", nullable = false,insertable = false,updatable = false)
	private Product products;
	
	@Column(name="quantity")
	private Long quantity;

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Product getProducts() {
		return products;
	}

	public void setProducts(Product products) {
		this.products = products;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
	
	
}
