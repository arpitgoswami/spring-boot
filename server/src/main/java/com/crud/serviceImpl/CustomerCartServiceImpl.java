package com.crud.serviceImpl;

//import java.lang.module.FindException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crud.beans.CustomerCartEntityData;
import com.crud.entity.Customer;
import com.crud.entity.CustomerCartEntity;
import com.crud.entity.CustomerProductCart;
import com.crud.entity.Product;
import com.crud.repository.CustomerProductCartRepository;
import com.crud.repository.CustomerRepository;
import com.crud.repository.CutomerCartRepository;
import com.crud.repository.ProductRepository;
import com.crud.response.ApiResponse;
import com.crud.response.Response;
import com.crud.service.CustomerCart;

import requestDto.CartRequestDto;
import requestDto.NewCartReqDto;

@Service
public class CustomerCartServiceImpl implements CustomerCart {

	@Autowired
	private CustomerRepository customerRepository;

	// CustomerProductCart
	
//	@Autowired
//	private CustomerProductCart customerProductCart;
	
	@Autowired
	private CutomerCartRepository cutomerCartRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CustomerProductCartRepository customerProductCartRepository;

	@Override
	public ResponseEntity<?> addCustomerProductToCart(CustomerCartEntityData customerCartEntityData) {

		for (Long id : customerCartEntityData.getProducts()) {

			CustomerCartEntity cartEntity = new CustomerCartEntity();

			Optional<Product> productOpt = productRepository.findById(id);
			if (productOpt.isPresent()) {
				cartEntity.setProducts(productOpt.get());
				int quant = 1;

			}

//			Optional<Customer> customerOpt = customerRepository
//					.findById(customerCartEntityData.getCustomerId().intValue());
//			if (customerOpt.isPresent()) {
//				cartEntity.setCustomer(customerOpt.get());
//			} else {
//				return new ResponseEntity<>(
//						new ApiResponse(false,
//								"Customer id is not present in database !!" + customerCartEntityData.getCustomerId()),
//						HttpStatus.INTERNAL_SERVER_ERROR);
//			}

			cutomerCartRepository.save(cartEntity);
			// CustomerCartEntity save = cutomerCartRepository.save(cartEntity);

		}

		return new ResponseEntity<>(new ApiResponse(true, "Prodct added to cart successfully."), HttpStatus.ACCEPTED);
	}

	@Override
	public Response addProductToCustomerCart(CartRequestDto cartRequestDto) {
		Response response = new Response();
		
		try {
			int cid = cartRequestDto.getCustomerId();
			Long pid = cartRequestDto.getProductId();
			Long quantity = cartRequestDto.getQuantity();
			String increaseOrDecreaseType = cartRequestDto.getIncreaseOrDecreaseType();
			System.out.println(cid+"cid"+pid+"pid"+quantity+"quantity"+increaseOrDecreaseType+"increaseOrDecreaseType");
			Optional<Customer> customerById = customerRepository.findById(cid);
			System.out.println(customerById+"customerById.............................");
			
			Optional<Product> productOpt = productRepository.findByProductId(pid);
			if(!productOpt.isPresent()) {
				
				response.setCode(400);
				response.setMessage("Product id is not present in database ! !");
				response.setObject(null);
				return response;
			}
			System.out.println(productOpt+"productOpt....................................");
			CustomerProductCart newCartReqDto = null;
			Optional<CustomerProductCart> CartProductbyCustomerOpt = customerProductCartRepository.findByCustomerAndProducts(customerById,productOpt.get());
			
			if (!CartProductbyCustomerOpt.isPresent()) {
				System.out.println(CartProductbyCustomerOpt+"present nhi hai bhai");
				if(increaseOrDecreaseType.equalsIgnoreCase("positive")) {
					System.out.println("1111111111");
					if(quantity <= productOpt.get().getQuantity()) {
						newCartReqDto = new CustomerProductCart();
						newCartReqDto.setCustomer(customerById.get());
						newCartReqDto.setProducts(productOpt.get());
						newCartReqDto.setQuantity(quantity);
						CustomerProductCart save = customerProductCartRepository.save(newCartReqDto);
						response.setCode(200);
						response.setMessage("product added successfully 11111");
						response.setObject(save);
						return response;
					}else {
						response.setCode(400);
						response.setMessage("product out of stock");
						response.setObject(null);
						return response;
					}
				}
//				else {
//					
//					response.setCode(400);
//					response.setMessage("Product Not Available at Cart");
//					response.setObject(null);
//					return response;
//				}
				
				
				
			}else {
				newCartReqDto = CartProductbyCustomerOpt.get();
				
				if(increaseOrDecreaseType.equalsIgnoreCase("positive")) {
					if(newCartReqDto.getQuantity() < productOpt.get().getQuantity()) {
						newCartReqDto.setQuantity(newCartReqDto.getQuantity() +1);
						CustomerProductCart save = customerProductCartRepository.save(newCartReqDto);
						response.setCode(200);
						response.setMessage("product added successfully 2222");
						response.setObject(save);
						return response;
					}else {
						response.setCode(400);
						response.setMessage("product out of stock");
						response.setObject(null);
						return response;
					}
				}else {
					if(newCartReqDto.getQuantity()>1) {
						newCartReqDto.setQuantity(newCartReqDto.getQuantity() - 1);
						CustomerProductCart save = customerProductCartRepository.save(newCartReqDto);
						response.setCode(200);
						response.setMessage("product added successfully 2222");
						response.setObject(save);
						return response;
					}else if(newCartReqDto.getQuantity()==1) {
						customerProductCartRepository.delete(newCartReqDto);
						response.setCode(200);
						response.setMessage("Product Deleted Successfully From Cart");
						response.setObject(null);
						return response;
					}
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setCode(500);
			response.setMessage(e.getMessage());
			response.setObject(null);
			return response;
		}
		
		return response;
		
	}

	@Override
	public Response deleteProductFromCart(int cid, Long pid) {
		
		Response response = new Response();
		
		try {
			int cidNew = cid;
			Long pidNew = pid;Optional<Customer> customerById = customerRepository.findById(cid);
			System.out.println(customerById+"customerById.............................");
			
			Optional<Product> productOpt = productRepository.findByProductId(pid);
			
			if(!productOpt.isPresent()) {
				response.setCode(400);
				response.setMessage("Product id is not present in database ! !");
				response.setObject(null);
				return response;
			}
			CustomerProductCart newCartReqDto = null;
			Optional<CustomerProductCart> CartProductbyCustomerOpt = customerProductCartRepository.findByCustomerAndProducts(customerById,productOpt.get());
			if(CartProductbyCustomerOpt.isPresent()) {
				newCartReqDto = CartProductbyCustomerOpt.get();
				customerProductCartRepository.delete(newCartReqDto);
				response.setCode(200);
				response.setMessage("Product Deleted Successfully");
				response.setObject(null);
				return response;
				
				
			}else {
				response.setCode(400);
				response.setMessage("Product id is not present in database ! !");
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
	public Response getCartProductByCustomerId(int cid) {
		
		Response response = new Response();
		
		try {
			Optional<Customer> customerById = customerRepository.findById(cid);
			
			List<CustomerProductCart> byCustomer = customerProductCartRepository.findByCustomer(customerById);
			
			if(!byCustomer.isEmpty()) {
				response.setCode(200);
				response.setMessage("Data Retrieve Successfully");
				response.setObject(byCustomer);
				return response;
			}else {
				response.setCode(400);
				response.setMessage("Customer cart is Empty");
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

	
	

	


	

	

}
