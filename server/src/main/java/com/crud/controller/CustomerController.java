package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.crud.entity.Customer;

import com.crud.response.Response;
import com.crud.service.CustomerService;

import requestDto.LoginRequestDto;

@CrossOrigin (origins="*", maxAge=3600)
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/addCustomer")
	public Response AddUser(@RequestBody Customer customer) {
		Response response = new Response();
		Customer customer2 = customerService.addCustomer(customer);
		response.setCode(200);
		response.setMessage("Data Added Successfully");
		response.setObject(customer2);
		return response;
		
	}
	
	
	@PostMapping("/login")
	public Response loginCustomer(@RequestBody LoginRequestDto body) {
		
		Response login = customerService.login(body);
		return login;
		
	}
	
	//@RequestPart String consumerApplicationDto,
	//@RequestPart(value = "TAndCPpermissionFile", required = false) MultipartFile TAndCPpermissionFile
	
	@PostMapping("/addModule")
	public Response addmodule(@RequestPart String name,@RequestPart(value="Module_File",required=true) MultipartFile Module_File) {
		Response response = new Response();
		
		
		try {
//			Response submit = customerService.submit(moduleReqDto, Module_File);
			Response submit = customerService.submit1(name, Module_File);
			response.setCode(200);
			response.setMessage("Data Added Successfully");
			response.setObject(submit.getObject());
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setCode(500);
			response.setMessage(e.getMessage());
			response.setObject(null);
			return response;
		}
		
		
	}
	
	
	@GetMapping("/getModuleList")
	public Response getmoduleList() {
		Response headerModuleList = customerService.getHeaderModuleList();
		return headerModuleList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
