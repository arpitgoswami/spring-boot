package requestDto;

import com.crud.entity.Product;

public class OrderProductRelUpdateReqDto {

	private Long id;
	private Long orderId;
	private Long product;
	private Double quantity;
	private String status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	public Long getProduct() {
		return product;
	}
	public void setProduct(Long product) {
		this.product = product;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
//	{
//        "id": 1,
//        "orderId": 277,
//        "product": {
//            "productId": 1,
//            "filePath": "D:\\amsitko frontend\\am-ecommerce-project\\src\\assets\\product_File_5766241255246587_basmati-rice.webp",
//            "name": "Basmati Rice",
//            "quantity": 6,
//            "price": 110.0,
//            "discountedPrice": 20.0,
//            "dilivery": "Babar society, jodhpur",
//            "value": "5",
//            "categoryId": 1
//        },
//        "quantity": 2.0,
//        "status": "pending"
//    }
	
}
