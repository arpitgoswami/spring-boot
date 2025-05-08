package com.crud.responseDto;

import java.util.List;

import javax.persistence.ManyToOne;

import com.crud.entity.Customer;
import com.crud.entity.DeliveryOrderAddress;
import com.crud.entity.OrderProductRel;
import com.crud.entity.Product;



public class OrderResponseDto {

	private Long id;
	   
    private Customer customer;
    
    private String status;
    
    private List<OrderProductRel> productList;
    
    private DeliveryOrderAddress deliveryOrderAddress;
    
    private Double finalPayableAmount;
    

	public Double getFinalPayableAmount() {
		return finalPayableAmount;
	}

	public void setFinalPayableAmount(Double finalPayableAmount) {
		this.finalPayableAmount = finalPayableAmount;
	}

	public DeliveryOrderAddress getDeliveryOrderAddress() {
		return deliveryOrderAddress;
	}

	public void setDeliveryOrderAddress(DeliveryOrderAddress deliveryOrderAddress) {
		this.deliveryOrderAddress = deliveryOrderAddress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<OrderProductRel> getProductList() {
		return productList;
	}

	public void setProductList(List<OrderProductRel> productList) {
		this.productList = productList;
	}

	
    
    
    
    
    
    
}
