package com.crud.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.crud.entity.Product;
import com.crud.repository.ProductRepository;
import com.crud.response.Response;
import com.crud.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import requestDto.ProductRequestDto;
import util.Utility;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Response addProduct(String productRequectDto, MultipartFile product_File) {
		Response response = new Response();

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			ProductRequestDto productRequestDto = objectMapper.readValue(productRequectDto, ProductRequestDto.class);

			Product product = new Product();
			product.setName(productRequestDto.getName());
			product.setQuantity(productRequestDto.getQuantity());
			product.setPrice(productRequestDto.getPrice());
			product.setDiscountedPrice(productRequestDto.getDiscountedPrice());
			product.setDilivery(productRequestDto.getDelivery());
			product.setValue(productRequestDto.getValue());
			product.setCategoryId(productRequestDto.getCategoryId());
			product.setUnit(productRequestDto.getUnit());
			product.setUnitName(productRequestDto.getUnitName());

			if (productRequectDto == null || productRequectDto.isEmpty()) {
				response.setCode(400);
				response.setMessage("Product ResquestDto Can not be null");
				response.setObject(null);

			} else if (product_File.isEmpty()) {
				response.setCode(400);
				response.setMessage("Product file cannot be null");
				response.setObject(null);
			} else {
				String productFiles = Utility.uploadFile(product_File, "product_File");
				product.setFilePath(productFiles);
				Product save = productRepository.save(product);
				response.setCode(200);
				response.setMessage("Data Added Successfully");
				response.setObject(save);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setCode(301);
			response.setMessage("Some exception occured");
			response.setObject(null);
		}
		return response;
	}

	@Override
	public Response getProductList() {
		Response response = new Response();

		try {
			List<Product> all = productRepository.findAll();
			if (all.isEmpty()) {
				response.setCode(400);
				response.setMessage("No Product Available");
				response.setObject(null);
				return response;
			} else {
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

	@Override
	public Response getProductListByCatogryId(int catogryId) {
		Response response = new Response();
		
		
		try {
			List<Product> byCategoryId = productRepository.findByCategoryId(catogryId);
			if(byCategoryId.isEmpty()) {
				response.setCode(400);
				response.setMessage("No Product Available");
				response.setObject(null);
				return response;
			}else {
				response.setCode(200);
				response.setMessage("Data Retrieve Successfully");
				response.setObject(byCategoryId);
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
	public Response getProductDetailsByProductId(Long productId) {
		Response response = new Response();
		
		try {
			Optional<Product> byProductId = productRepository.findByProductId(productId);
			if( byProductId.isPresent()) {
				response.setCode(200);
				response.setMessage("Data Retrieve Successfully");
				response.setObject(byProductId);
				return response;
			}else {
				response.setCode(400);
				response.setMessage("Product Not Available");
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
