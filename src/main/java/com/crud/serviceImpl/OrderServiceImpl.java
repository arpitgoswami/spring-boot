package com.crud.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.beans.OrderListItemStatus;
import com.crud.entity.Customer;
import com.crud.entity.DeliveryOrderAddress;
import com.crud.entity.Order;
import com.crud.entity.OrderItem;
import com.crud.entity.OrderProductRel;
import com.crud.entity.Product;
import com.crud.repository.CustomerRepository;
import com.crud.repository.DeliveryOrderAddressRepository;
import com.crud.repository.OrderProductRelRepository;
import com.crud.repository.OrderRepository;
import com.crud.repository.ProductRepository;
import com.crud.response.Response;
import com.crud.responseDto.OrderResponseDto;
import com.crud.service.OrderService;

import requestDto.OrderItemDTO;
import requestDto.OrderProductRelUpdateReqDto;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private DeliveryOrderAddressRepository deliveryOrderAddressRepository;

	@Autowired
	private OrderProductRelRepository orderProductRelRepository;

	@Override
	public Response createOrder(int customerId, List<OrderItemDTO> orderItemDTOs, String status,DeliveryOrderAddress deliveryOrderAddress,Double finalPayableAmount) {
		Response response = new Response();

		try {
			Order order = new Order();
			Optional<Customer> byId = customerRepository.findById(customerId);
			if (byId.isPresent()) {
				order.setCustomer(byId.get());
			} else {
				response.setCode(400);
				response.setMessage("Customer not found");
				response.setObject(null);
				return response;
			}

			order.setStatus("pending");
			order.setFinalPayableAmount(finalPayableAmount);
			DeliveryOrderAddress deliveryOrderAddressForOrder= deliveryOrderAddressRepository.save(deliveryOrderAddress);
			order.setDeliveryOrderAddress(deliveryOrderAddressForOrder);

			//////////////////////////////////////////////////////////
			Order save2 = orderRepository.save(order);

			
			
			List<OrderProductRel> orderProductRelArraylist = new ArrayList<>();
			for (OrderItemDTO orderItemDTO : orderItemDTOs) {

				OrderProductRel orderProductRel = null;
				orderProductRel = new OrderProductRel();
				orderProductRel.setOrderId(order.getId());

				Optional<Product> productOpt = productRepository.findByProductId(orderItemDTO.getProductId());
				if (productOpt.isPresent()) {
					orderProductRel.setProduct(productOpt.get());
					orderProductRel.setQuantity(orderItemDTO.getQuantity());
					orderProductRel.setStatus(status);
				}
				OrderProductRel save = orderProductRelRepository.save(orderProductRel);
				orderProductRelArraylist.add(save);
			}

			order.setProductRels(orderProductRelArraylist);

			if (save2 != null) {
				
				OrderResponseDto res = new OrderResponseDto();
				res.setCustomer(save2.getCustomer());
				res.setId(save2.getId());
				res.setStatus(save2.getStatus());
				res.setProductList(orderProductRelArraylist);
				res.setDeliveryOrderAddress(deliveryOrderAddressForOrder);
				res.setFinalPayableAmount(finalPayableAmount);
				response.setCode(200);
				response.setMessage("Order Received Successfully");
				response.setObject(res);
				return response;
			} else {
				response.setCode(400);
				response.setMessage("Order initialization failed");
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
	public Response getOrderListByCustomerId(int cid) {
		Response response = new Response();

		try {
			Optional<Customer> customerOpt = customerRepository.findById(cid);
			if (customerOpt != null || customerOpt.isPresent()) {
				List<Order> byCustomer = orderRepository.findByCustomer(customerOpt.get());

				if (byCustomer.isEmpty()) {
					response.setCode(400);
					response.setMessage("No Order Found !");
					response.setObject(null);
					return response;
				} else {
					response.setCode(200);
					response.setMessage("Data Retrieve Successfully");
					response.setObject(byCustomer);
					return response;
				}

			} else {
				response.setCode(400);
				response.setMessage("No any order found for this customer !");
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

//		try {
//			Optional<Customer> customerOpt = customerRepository.findById(cid);
//			if(customerOpt.isPresent()) {
//				System.out.println("customer present ..............");
//				Optional<Order> byCustomer = orderRepository.findByCustomer(customerOpt.get());
//				if(byCustomer.isPresent()) {
//					OrderResponseDto res = new OrderResponseDto();
//					res.setCustomer(customerOpt.get());
////					res.setId();
////					res.setStatus();
////					res.setProductList();
//					
//					System.out.println("list found...................");
//					response.setCode(200);
//					response.setMessage("Data Retrieve Successfully");
//					response.setObject(byCustomer);
//					return response;
//				}else {
//					System.out.println("list not found...................");
//					response.setCode(400);
//					response.setMessage("No Order Found");
//					response.setObject(byCustomer);
//					return response;
//				}
//			}else {
//				System.out.println("customer not present ..............");
//				response.setCode(404);
//				response.setMessage("No any Order Fount For this Customer");
//				response.setObject(null);
//				return response;
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			response.setCode(500);
//			response.setMessage(e.getMessage());
//			response.setObject(null);
//			return response;
//		}
//		

	}

	@Override
	public Response getOrderItemsByOrderID(Long oid) {
		Response response = new Response();

		try {
			List<OrderProductRel> byOrderId = orderProductRelRepository.findByOrderId(oid);
			if (byOrderId.isEmpty()) {
				response.setCode(400);
				response.setMessage("No Order Found For this OrderId");
				response.setObject(null);
				return response;
			} else {
				System.out.println(byOrderId+"byOrderId.........");
				response.setCode(200);
				response.setMessage("Data Retrieve Successfully");
				response.setObject(byOrderId);
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
	public Response getAllOrdersOfAllConsumerForAdmin() {
		Response response = new Response();

		try {
			List<Order> all = orderRepository.findAll();
			if (all.isEmpty()) {
				response.setCode(400);
				response.setMessage("No Order Available");
				response.setObject(null);
				return response;
			} else {
				response.setCode(200);
				response.setMessage("Orders Retrieve SuccessFully");
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
	public Response getListOfAllOrderedItemByCustomerId(int cid) {
		Response response = new Response();

		try {
			Optional<Customer> byId = customerRepository.findById(cid);
			if (byId.isPresent()) {
				List<Order> byCustomer = orderRepository.findByCustomer(byId.get());
				if (byCustomer.isEmpty()) {
					response.setCode(400);
					response.setMessage("No order Found For this Customer");
					response.setObject(null);
					return response;
				} else {
					OrderListItemStatus orderListItemStatus = new OrderListItemStatus();
					orderListItemStatus.setCid(cid);
					

					List<OrderListItemStatus> orderProductRelArraylist = new ArrayList<>();
					for (Order orderByCustomer : byCustomer) {

//						orderListItemStatus.setStatus(orderByCustomer.getStatus());
						List<OrderProductRel> byOrderIdss = orderProductRelRepository
								.findByOrderId(orderByCustomer.getId());
						if (byOrderIdss.isEmpty()) {

						} else {
							for (OrderProductRel productItemsList : byOrderIdss) {
								orderListItemStatus.setOrderId(productItemsList.getOrderId());
								orderListItemStatus.setId(productItemsList.getId());
								orderListItemStatus.setProduct(productItemsList.getProduct());
								orderListItemStatus.setQuantity(productItemsList.getQuantity());
								orderListItemStatus.setStatus(productItemsList.getStatus());
								//orderListItemStatus.setDeliveryOrderAddress();

								orderProductRelArraylist.add(orderListItemStatus);
							}

						}

					}

					response.setCode(200);
					response.setMessage("Data Retrieve Successfully");
					response.setObject(orderProductRelArraylist);
					return response;

				}

			} else {
				response.setCode(400);
				response.setMessage("Invalid CustomerId !");
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
	public Response getAllProductListOfAllCustomersForAdmin() {
		Response response = new Response();

		try {
			List<OrderProductRel> all = orderProductRelRepository.findAll();
			if (all.isEmpty()) {
				response.setCode(400);
				response.setMessage("Data not found");
				response.setObject(null);
				return response;

			} else {
				response.setCode(200);
				response.setMessage("Data Retreive Successfully !");
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
	
	
	
	
	
	
	
	/////////////////////////////////////////////////////////////////   update order   ///////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Response updateOrder(int customerId, List<OrderProductRelUpdateReqDto> orderItemDTOs, String status, Long oid,DeliveryOrderAddress deliveryOrderAddress,Double finalPayableAmount) {
		Response response = new Response();

		try {
			Optional<Customer> customerById = customerRepository.findById(customerId);
			if (customerById.isPresent()) {

				Optional<Order> OrderbyId = orderRepository.findById(oid);
				if (OrderbyId.isPresent()) {

					Order updatedOrderRow = OrderbyId.get();
					
					if (status.equals("pending")) {
						if(orderItemDTOs==null) {
							response.setCode(400);
							response.setMessage("Product should not be null !");
							response.setObject(null);
							return response;
						}
						updatedOrderRow.setStatus(status);
						updatedOrderRow.setId(oid);
						updatedOrderRow.setCustomer(customerById.get());
						Optional<DeliveryOrderAddress> byId2 = deliveryOrderAddressRepository.findById(deliveryOrderAddress.getDeliveryAddressId());
						byId2.get().setCity(deliveryOrderAddress.getCity());
						byId2.get().setFirstName(deliveryOrderAddress.getFirstName());
						byId2.get().setLastName(deliveryOrderAddress.getLastName());
						byId2.get().setMobile(deliveryOrderAddress.getMobile());
						byId2.get().setState(deliveryOrderAddress.getState());
						byId2.get().setStreetAddress(deliveryOrderAddress.getStreetAddress());
						byId2.get().setZipCode(deliveryOrderAddress.getZipCode());
						byId2.get().setDeliveryAddressId(deliveryOrderAddress.getDeliveryAddressId());
						DeliveryOrderAddress updatedDeliveryOrderAddressForOrder= deliveryOrderAddressRepository.save(byId2.get());
						updatedOrderRow.setDeliveryOrderAddress(updatedDeliveryOrderAddressForOrder);
						// DeliveryOrderAddress deliveryOrderAddressForOrder= deliveryOrderAddressRepository.save(deliveryOrderAddress);
						updatedOrderRow.setFinalPayableAmount(finalPayableAmount);
						List<OrderProductRel> orderProductRelArraylist = new ArrayList<>();
						for(OrderProductRelUpdateReqDto orderItemDTO :orderItemDTOs) {
							Optional<OrderProductRel> byId = orderProductRelRepository.findById(orderItemDTO.getId());
							if(byId.isPresent()) {
								OrderProductRel orderItem = byId.get();
								orderItem.setId(orderItemDTO.getId());
								orderItem.setOrderId(orderItemDTO.getOrderId());
								Optional<Product> productItem = productRepository.findById(orderItemDTO.getProduct());
								if(productItem.isPresent()) {
									orderItem.setProduct(productItem.get());
									orderItem.setQuantity(orderItemDTO.getQuantity());
									orderItem.setStatus(orderItemDTO.getStatus());
									OrderProductRel save = orderProductRelRepository.save(orderItem);
									if(save!=null) {
										orderProductRelArraylist.add(orderItem);
									}
								}else {
									response.setCode(400);
									response.setMessage("Product not found !");
									response.setObject(null);
									return response;
								}
								
							}else {
								response.setCode(400);
								response.setMessage("Invalid item for this order !");
								response.setObject(null);
								return response;
							}
						}
						
						updatedOrderRow.setProductRels(orderProductRelArraylist);
						Order save = orderRepository.save(updatedOrderRow);
						if(save!=null) {
							response.setCode(200);
							response.setMessage("order updated successfully");
							response.setObject(save);
							return response;
						}else {
							response.setCode(400);
							response.setMessage("order updation failed !");
							response.setObject(null);
							return response;
						}
					
					} else if (status.equals("cancel")) {
						if(orderItemDTOs==null) {
							response.setCode(400);
							response.setMessage("Product should not be null !");
							response.setObject(null);
							return response;
						}
						updatedOrderRow.setStatus(status);
						updatedOrderRow.setId(oid);
						updatedOrderRow.setCustomer(customerById.get());
						Optional<DeliveryOrderAddress> byId2 = deliveryOrderAddressRepository.findById(deliveryOrderAddress.getDeliveryAddressId());
						byId2.get().setCity(deliveryOrderAddress.getCity());
						byId2.get().setFirstName(deliveryOrderAddress.getFirstName());
						byId2.get().setLastName(deliveryOrderAddress.getLastName());
						byId2.get().setMobile(deliveryOrderAddress.getMobile());
						byId2.get().setState(deliveryOrderAddress.getState());
						byId2.get().setStreetAddress(deliveryOrderAddress.getStreetAddress());
						byId2.get().setZipCode(deliveryOrderAddress.getZipCode());
						byId2.get().setDeliveryAddressId(deliveryOrderAddress.getDeliveryAddressId());
						DeliveryOrderAddress updatedDeliveryOrderAddressForOrder= deliveryOrderAddressRepository.save(byId2.get());
						updatedOrderRow.setDeliveryOrderAddress(updatedDeliveryOrderAddressForOrder);
						// DeliveryOrderAddress deliveryOrderAddressForOrder= deliveryOrderAddressRepository.save(deliveryOrderAddress);
						updatedOrderRow.setFinalPayableAmount(finalPayableAmount);
						List<OrderProductRel> orderProductRelArraylist = new ArrayList<>();
						for(OrderProductRelUpdateReqDto orderItemDTO :orderItemDTOs) {
							Optional<OrderProductRel> byId = orderProductRelRepository.findById(orderItemDTO.getId());
							if(byId.isPresent()) {
								OrderProductRel orderItem = byId.get();
								orderItem.setId(orderItemDTO.getId());
								orderItem.setOrderId(orderItemDTO.getOrderId());
								Optional<Product> productItem = productRepository.findById(orderItemDTO.getProduct());
								if(productItem.isPresent()) {
									orderItem.setProduct(productItem.get());
									orderItem.setQuantity(orderItemDTO.getQuantity());
									orderItem.setStatus("cancel");
									OrderProductRel save = orderProductRelRepository.save(orderItem);
									if(save!=null) {
										orderProductRelArraylist.add(orderItem);
									}
								}else {
									response.setCode(400);
									response.setMessage("Product not found !");
									response.setObject(null);
									return response;
								}
								
							}else {
								response.setCode(400);
								response.setMessage("Invalid item for this order !");
								response.setObject(null);
								return response;
							}
						}
						
						updatedOrderRow.setProductRels(orderProductRelArraylist);
						Order save = orderRepository.save(updatedOrderRow);
						if(save!=null) {
							response.setCode(200);
							response.setMessage("order updated successfully");
							response.setObject(save);
							return response;
						}else {
							response.setCode(400);
							response.setMessage("order updation failed !");
							response.setObject(null);
							return response;
						}
						
					} else if (status.equals("delivered")) {
						if(orderItemDTOs==null) {
							response.setCode(400);
							response.setMessage("Product should not be null !");
							response.setObject(null);
							return response;
						}
						updatedOrderRow.setStatus(status);
						updatedOrderRow.setId(oid);
						updatedOrderRow.setCustomer(customerById.get());
						Optional<DeliveryOrderAddress> byId2 = deliveryOrderAddressRepository.findById(deliveryOrderAddress.getDeliveryAddressId());
						byId2.get().setCity(deliveryOrderAddress.getCity());
						byId2.get().setFirstName(deliveryOrderAddress.getFirstName());
						byId2.get().setLastName(deliveryOrderAddress.getLastName());
						byId2.get().setMobile(deliveryOrderAddress.getMobile());
						byId2.get().setState(deliveryOrderAddress.getState());
						byId2.get().setStreetAddress(deliveryOrderAddress.getStreetAddress());
						byId2.get().setZipCode(deliveryOrderAddress.getZipCode());
						byId2.get().setDeliveryAddressId(deliveryOrderAddress.getDeliveryAddressId());
						DeliveryOrderAddress updatedDeliveryOrderAddressForOrder= deliveryOrderAddressRepository.save(byId2.get());
						updatedOrderRow.setDeliveryOrderAddress(updatedDeliveryOrderAddressForOrder);
						// DeliveryOrderAddress deliveryOrderAddressForOrder= deliveryOrderAddressRepository.save(deliveryOrderAddress);
						updatedOrderRow.setFinalPayableAmount(finalPayableAmount);
						List<OrderProductRel> orderProductRelArraylist = new ArrayList<>();
						for(OrderProductRelUpdateReqDto orderItemDTO :orderItemDTOs) {
							Optional<OrderProductRel> byId = orderProductRelRepository.findById(orderItemDTO.getId());
							if(byId.isPresent()) {
								OrderProductRel orderItem = byId.get();
								orderItem.setId(orderItemDTO.getId());
								orderItem.setOrderId(orderItemDTO.getOrderId());
								Optional<Product> productItem = productRepository.findById(orderItemDTO.getProduct());
								if(productItem.isPresent()) {
									orderItem.setProduct(productItem.get());
									orderItem.setQuantity(orderItemDTO.getQuantity());
									orderItem.setStatus(orderItemDTO.getStatus());
									OrderProductRel save = orderProductRelRepository.save(orderItem);
									if(save!=null) {
										orderProductRelArraylist.add(orderItem);
									}
								}else {
									response.setCode(400);
									response.setMessage("Product not found !");
									response.setObject(null);
									return response;
								}
								
							}else {
								response.setCode(400);
								response.setMessage("Invalid item for this order !");
								response.setObject(null);
								return response;
							}
						}
						
						updatedOrderRow.setProductRels(orderProductRelArraylist);
						Order save = orderRepository.save(updatedOrderRow);
						if(save!=null) {
							response.setCode(200);
							response.setMessage("order updated successfully");
							response.setObject(save);
							return response;
						}else {
							response.setCode(400);
							response.setMessage("order updation failed !");
							response.setObject(null);
							return response;
						}
					
					} else {
						response.setCode(400);
						response.setMessage("Invalid status");
						response.setObject(null);
						return response;
					}

				} else {
					response.setCode(400);
					response.setMessage("Invalid Order Id");
					response.setObject(null);
					return response;
				}

			} else {

				response.setCode(400);
				response.setMessage("Invalid Customer");
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
	public Response getOrderByOid(Long oid) {
		Response response = new Response();
		
		try {
			Optional<Order> byId = orderRepository.findById(oid);
			if(byId.isPresent()) {
				response.setCode(200);
				response.setMessage("Data Retreive Successfully..");
				response.setObject(byId);
				return response;
			}else {
				response.setCode(400);
				response.setMessage("Data Not Found.....");
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
