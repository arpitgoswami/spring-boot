package requestDto;

import java.util.List;

import com.crud.entity.DeliveryOrderAddress;

public class OrderRequest {
	
	private int customerId;
	private List<OrderItemDTO> productList;
	private String status;
	private DeliveryOrderAddress deliveryOrderAddress;
	private Double finalPayableAmount;
	
	public DeliveryOrderAddress getDeliveryOrderAddress() {
		return deliveryOrderAddress;
	}

	public void setDeliveryOrderAddress(DeliveryOrderAddress deliveryOrderAddress) {
		this.deliveryOrderAddress = deliveryOrderAddress;
	}

	

	

	public Double getFinalPayableAmount() {
		return finalPayableAmount;
	}

	public void setFinalPayableAmount(Double finalPayableAmount) {
		this.finalPayableAmount = finalPayableAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public List<OrderItemDTO> getProductList() {
		return productList;
	}

	public void setProductList(List<OrderItemDTO> productList) {
		this.productList = productList;
	}

}
