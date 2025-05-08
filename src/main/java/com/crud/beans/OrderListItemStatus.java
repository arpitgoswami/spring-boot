package com.crud.beans;

import com.crud.entity.DeliveryOrderAddress;

public class OrderListItemStatus {
	
	private Long id;
	private int cid;
	private Object product;
	private Long orderId;
	private Double quantity;
	private String status;
	private DeliveryOrderAddress  deliveryOrderAddress;
	
	
	
	public DeliveryOrderAddress getDeliveryOrderAddress() {
		return deliveryOrderAddress;
	}
	public void setDeliveryOrderAddress(DeliveryOrderAddress deliveryOrderAddress) {
		this.deliveryOrderAddress = deliveryOrderAddress;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public Object getProduct() {
		return product;
	}
	public void setProduct(Object product) {
		this.product = product;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	

}
