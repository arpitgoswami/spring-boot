package com.crud.serviceImpl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crud.entity.Customer;
import com.crud.entity.HeaderModule;
import com.crud.repository.CustomerRepository;
import com.crud.repository.HeaderModuleRepository;
import com.crud.response.Response;
import com.crud.responseDto.CustomerLoginResponseDto;
import com.crud.service.CustomerService;


import requestDto.LoginRequestDto;
import util.Utility;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private HeaderModuleRepository headerModuleRepository;

	@Override
	public Customer addCustomer(Customer customer) {
		Customer save = customerRepository.save(customer);
		return save;
	}

	@Override
	public Response login(LoginRequestDto body) {
		Response response = new Response();

		try {
			String loginId = body.getId();
			String loginPass = body.getPassword();
			if (loginId == null) {
				response.setCode(404);
				response.setMessage("Login_id can not be null");
				response.setObject(null);
				return response;
			}
			if (loginPass == null) {
				response.setCode(404);
				response.setMessage("Login_password can not be null");
				response.setObject(null);
				return response;
			}

			Customer byMobileNo = customerRepository.findByMobileNo(body.getId());

			if (byMobileNo != null) {

				if (byMobileNo.getPassword().equals(loginPass)) {
					CustomerLoginResponseDto loginResponse = new CustomerLoginResponseDto();
					loginResponse.setName(byMobileNo.getName());
					loginResponse.setMobileNo(byMobileNo.getMobileNo());
					loginResponse.setEmail(byMobileNo.getEmail());
					loginResponse.setRole(byMobileNo.getRole());
					loginResponse.setRoleId(byMobileNo.getRoleId());
					loginResponse.setCid(byMobileNo.getId());
					loginResponse.setAddress(byMobileNo.getAddress());
					response.setCode(200);
					response.setMessage("Login Successfully");
					response.setObject(loginResponse);
					return response;
				} else {
					System.out.println(byMobileNo.getPassword()+"="+loginPass+"...."+loginId+"="+byMobileNo.getMobileNo());
					response.setCode(400);
					response.setMessage("Invalid Credential");
					response.setObject(null);
					return response;
				}
			} else {
				response.setCode(400);
				response.setMessage("Customer not availabe");
				response.setObject(null);
				return response;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setCode(500);
			response.setMessage(e.getMessage());
			response.setObject(null);
			return response;
		}

	}

	
	@Override
	public Response submit(HeaderModule modelHeaderDto, MultipartFile moduleFile) {
		
		Response response = new Response();
		HeaderModule headerModuleRequestDto = new HeaderModule();
		
		try {
			
			if(modelHeaderDto!=null) {
				if(modelHeaderDto.getName()!=null) {
					headerModuleRequestDto.setName(modelHeaderDto.getName());
				}else {
					response.setCode(400);
					response.setMessage("Name can not be null");
					response.setObject(null);
					return response;
				}
					
			}else {
				response.setCode(400);
				response.setMessage("modelHeaderDto can not be null");
				response.setObject(null);
				return response;
			}
		
			
//			if(moduleFile!=null) {
////				Response moduleFiles = Utility.uploadFile(moduleFile, "Module_File");
//				if(moduleFiles.getCode()==200) {
//					FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) moduleFiles.getObject();
//					modelHeaderDto.setFilePath(fileUploadPathDto.getFilePath());
//					headerModuleRequestDto.setFilePath(modelHeaderDto.getFilePath());
//					// HeaderModule
//				}else {
//					return moduleFiles;
//				}
//			}else {
//				response.setCode(400);
//				response.setMessage("file can not be null");
//				response.setObject(null);
//				return response;
//			}
			
			HeaderModule save = headerModuleRepository.save(headerModuleRequestDto);
			response.setCode(200);
			response.setMessage("Data Added Successfully");
			response.setObject(save);
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

	@Override
	public Response submit1(String name, MultipartFile moduleFile) {
		
		Response response = new Response();
		HeaderModule headerModuleRequestDto = new HeaderModule();
		
		try {
			
			
				if(name!=null) {
					headerModuleRequestDto.setName(name);
				}else {
					response.setCode(400);
					response.setMessage("Name can not be null");
					response.setObject(null);
					return response;
				}
		
			
			if(moduleFile!=null) {
				String moduleFiles = Utility.uploadFile(moduleFile, "Module_File");
				System.out.println("moduleFiles " +moduleFiles);
				if(moduleFiles!=null) {
//					FileUploadPathDto fileUploadPathDto = (FileUploadPathDto) moduleFiles.getObject();
					headerModuleRequestDto.setFilePath(moduleFiles);
//					headerModuleRequestDto.setFilePath(fileUploadPathDto.getFilePath());
					// HeaderModule
				}else {
//					return moduleFiles;
				}
			}else {
				response.setCode(400);
				response.setMessage("file can not be null");
				response.setObject(null);
				return response;
			}
			System.err.println("aaaaaa : " +headerModuleRequestDto.toString());
			
			HeaderModule save = headerModuleRepository.save(headerModuleRequestDto);
			System.err.println("bbbbbbbbbb : " +save);
			response.setCode(200);
			response.setMessage("Data Added Successfully");
			response.setObject(save);
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

	@Override
	public Response getHeaderModuleList() {
		Response response = new Response();
		
		try {
			List<HeaderModule> all = headerModuleRepository.findAll();
			if(all.isEmpty()) {
				response.setCode(404);
				response.setMessage("No Data Available");
				response.setObject(null);
				return response;
			}else {
				response.setCode(200);
				response.setMessage("Data Retrieve Successfully");
				response.setObject(all);
				return response;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setCode(500);
			response.setMessage(e.getMessage());
			response.setObject(null);
			return response;
		}
		
	}

}
