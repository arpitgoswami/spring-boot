package requestDto;

import com.crud.entity.Customer;
import com.crud.entity.Product;

public class NewCartReqDto {
	
	private Customer cus;
	private Product pd;
	private Long  quant;
	public Customer getCus() {
		return cus;
	}
	public void setCus(Customer cus) {
		this.cus = cus;
	}
	public Product getPd() {
		return pd;
	}
	public void setPd(Product pd) {
		this.pd = pd;
	}
	public Long getQuant() {
		return quant;
	}
	public void setQuant(Long quant) {
		this.quant = quant;
	}
	
	
}
