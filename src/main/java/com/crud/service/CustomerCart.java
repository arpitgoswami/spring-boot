package com.crud.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crud.beans.CustomerCartEntityData;
import com.crud.entity.CustomerProductCart;
import com.crud.response.Response;

import requestDto.CartRequestDto;


public interface CustomerCart {

	public ResponseEntity<?> addCustomerProductToCart(CustomerCartEntityData customerCartEntityData);
	
	public Response addProductToCustomerCart(CartRequestDto cartRequestDto);
	
	public Response deleteProductFromCart(int cid,Long pid);
	
    public Response getCartProductByCustomerId(int cid);
	
}
