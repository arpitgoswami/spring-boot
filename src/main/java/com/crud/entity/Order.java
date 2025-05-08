package com.crud.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name="Orders")
public class Order {

	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	   
	    @ManyToOne
	    private Customer customer;
	    
	    private String status;
	    
	    @Transient
	    private List<OrderProductRel> productRels;
	    
	    @OneToOne
	    private DeliveryOrderAddress deliveryOrderAddress;
	    
	    
	    public DeliveryOrderAddress getDeliveryOrderAddress() {
			return deliveryOrderAddress;
		}

		public void setDeliveryOrderAddress(DeliveryOrderAddress deliveryOrderAddress) {
			this.deliveryOrderAddress = deliveryOrderAddress;
		}

		private Double finalPayableAmount;
	    
	    
	    
	    
	    
	    
	    public Double getFinalPayableAmount() {
			return finalPayableAmount;
		}

		public void setFinalPayableAmount(Double finalPayableAmount) {
			this.finalPayableAmount = finalPayableAmount;
		}

		

		public List<OrderProductRel> getProductRels() {
			return productRels;
		}

		public void setProductRels(List<OrderProductRel> productRels) {
			this.productRels = productRels;
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

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		
}
