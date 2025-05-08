package com.crud.service;

import org.springframework.web.multipart.MultipartFile;

import com.crud.entity.Customer;
import com.crud.entity.HeaderModule;
import com.crud.response.Response;

import requestDto.LoginRequestDto;

public interface CustomerService {

	public Customer addCustomer(Customer customer);
	
	public Response login(LoginRequestDto body);
	
	public Response submit(HeaderModule modelHeaderDto, MultipartFile moduleFile);

	public Response submit1(String name, MultipartFile module_File);
	
	public Response getHeaderModuleList();
	
	
}
