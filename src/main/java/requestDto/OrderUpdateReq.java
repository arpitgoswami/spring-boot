package requestDto;

import java.util.List;

import com.crud.entity.DeliveryOrderAddress;

public class OrderUpdateReq {

	private int customerId;
	private List<OrderProductRelUpdateReqDto> orderItemDTOs;
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
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public List<OrderProductRelUpdateReqDto> getOrderItemDTOs() {
		return orderItemDTOs;
	}
	public void setOrderItemDTOs(List<OrderProductRelUpdateReqDto> orderItemDTOs) {
		this.orderItemDTOs = orderItemDTOs;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
}
