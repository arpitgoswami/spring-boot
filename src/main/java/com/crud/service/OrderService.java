package com.crud.service;

import java.util.List;
//import java.util.Optional;

import com.crud.entity.DeliveryOrderAddress;
import com.crud.entity.Order;
import com.crud.response.Response;

import requestDto.OrderItemDTO;
import requestDto.OrderProductRelUpdateReqDto;

public interface OrderService {

	public Response createOrder( int customerId, List<OrderItemDTO> orderItemDTOs,String status,DeliveryOrderAddress deliveryOrderAddress,Double finalPayableAmount);
	public Response updateOrder(int customerId, List<OrderProductRelUpdateReqDto> orderItemDTOs,String status,Long oid,DeliveryOrderAddress deliveryOrderAddress,Double finalPayableAmount);
	
//	public Response createOrder( int customerId, List<OrderItemDTO> orderItemDTOs,String status,Double finalPayableAmount);
//	public Response updateOrder(int customerId, List<OrderProductRelUpdateReqDto> orderItemDTOs,String status,Long oid,Double finalPayableAmount);
	
	public Response getOrderListByCustomerId(int cid);
	public Response getOrderItemsByOrderID(Long oid);
	public Response getListOfAllOrderedItemByCustomerId(int cid) ;
	
	public Response getAllOrdersOfAllConsumerForAdmin();
	public Response getAllProductListOfAllCustomersForAdmin();
	public Response getOrderByOid(Long oid);

	
	
	
	
	
	
	
	
	

	
	
}
