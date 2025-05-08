package com.crud.service;
import org.springframework.web.multipart.MultipartFile;
import com.crud.response.Response;

public interface ProductService {

	
	public Response addProduct(String ProductRequectDto,MultipartFile product_File);
	public Response getProductList();
	public Response getProductListByCatogryId( int catogryId);
	public Response getProductDetailsByProductId(Long productId);
	
	
}
