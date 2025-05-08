package com.crud.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.response.Response;
import com.crud.service.CustomerCart;

import requestDto.CartRequestDto;




@CrossOrigin (origins="*", maxAge=3600)
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customerCart")
public class CustomerCartController {

	@Autowired
	private CustomerCart customerCart;
	
	@PostMapping("/addProductTocart")
	public Response addProductTocart(@RequestBody CartRequestDto body) {
		Response response  = new Response();
		Response productToCustomerCart = customerCart.addProductToCustomerCart(body);
		
		return productToCustomerCart;
	}
	
	
	@PostMapping("/productAddtoSamCart")
	public Response addProductTosamCartlist(@RequestBody CartRequestDto cartRequestDto) {
		Response productToCustomerCart = customerCart.addProductToCustomerCart(cartRequestDto);
		
		return productToCustomerCart;
	}
	
	
	
	@DeleteMapping("/deleteProductFromCart/cid/{cid}/pid/{pid}")
	public Response deleteProductFromCart(@PathVariable int cid, @PathVariable Long pid) {
		Response deleteProductFromCart = customerCart.deleteProductFromCart(cid, pid);
		return deleteProductFromCart;
		
	}
	
	
	@GetMapping("/getCartProductByCustomerId/{cid}")
	public  Response getCartProductOfCustomerByCustomerId(@PathVariable int cid) {
		Response cartProductByCustomerId = customerCart.getCartProductByCustomerId(cid);
		return cartProductByCustomerId;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
