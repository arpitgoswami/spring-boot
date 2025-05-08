package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crud.response.Response;
import com.crud.service.ProductService;

@CrossOrigin (origins="*", maxAge=3600)
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/product")
public class productController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/addProduct")
	public Response addProducts(@RequestPart String BodyDto,@RequestPart(value="product_File",required=true) MultipartFile product_File) {
		Response product = productService.addProduct(BodyDto, product_File);
		return product;
	}
	
	@GetMapping("/getAllProductList")
	public Response getAllProduct() {
		Response productList = productService.getProductList();
		return productList;
	}
	
	
	@GetMapping("/getProductByCatogry/{id}")
	public Response getProductByCatogeryId(@PathVariable(name="id") int id) {
		Response productListByCatogryId = productService.getProductListByCatogryId(id);
		return productListByCatogryId;
		
	}
	
	
	@GetMapping("/getProductDetails/{productId}")
	public Response getProductDetails(@PathVariable(name="productId") Long productId) {
		Response productDetailsByProductId = productService.getProductDetailsByProductId(productId);
		return productDetailsByProductId;
	}
	
	
	
}
