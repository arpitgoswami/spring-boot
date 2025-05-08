package requestDto;

public class CartRequestDto {

	public int customerId;
	public Long productId;
	public Long quantity;
	public String increaseOrDecreaseType;
	
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getIncreaseOrDecreaseType() {
		return increaseOrDecreaseType;
	}
	public void setIncreaseOrDecreaseType(String increaseOrDecreaseType) {
		this.increaseOrDecreaseType = increaseOrDecreaseType;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
	
	
	
	
	
	
}
