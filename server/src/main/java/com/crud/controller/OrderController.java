package com.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.entity.Order;
import com.crud.response.Response;
import com.crud.service.OrderService;

import requestDto.OrderRequest;
import requestDto.OrderUpdateReq;


@CrossOrigin (origins="*", maxAge=3600)
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/order")
public class OrderController {

	
	 @Autowired
	    private OrderService orderService;
	 
	 
	 @PostMapping("/create")
	    public Response createOrder(@RequestBody OrderRequest orderRequest) {
		 Response order = orderService.createOrder(orderRequest.getCustomerId(),orderRequest.getProductList(),orderRequest.getStatus(),orderRequest.getDeliveryOrderAddress(), orderRequest.getFinalPayableAmount());
		 return order;
	       
	    }
	 
	 
	 @GetMapping("/getOrderListByCustomerId/{cid}")
	 public Response getOrderByCustomer(@PathVariable int cid) {
		 
		 Response orderListByCustomerId = orderService.getOrderListByCustomerId(cid);
		 return orderListByCustomerId;
	 }
	 
	 
	 
	 @GetMapping("/getOrderItemListByOrderId/{oid}")
	 public Response getOrderByOid(@PathVariable(name="oid") Long oid) {
		 Response orderItemsByOrderID = orderService.getOrderItemsByOrderID(oid);
		 return orderItemsByOrderID;
	 }
	 
	 
	 @GetMapping("/getAllOrders")
	 public Response getAllOrders() {
		 Response allOrderS = orderService.getAllOrdersOfAllConsumerForAdmin();
		 return allOrderS;
	 }
	 
	 
	 @GetMapping("/getListOfAllOrderedItemByCustomerId/{cid}")
	 public Response getListOfAllOrderedItemByCustomerId(@PathVariable(name="cid") int cid) {
		 Response listOfAllOrderedItemByCustomerId = orderService.getListOfAllOrderedItemByCustomerId(cid);
		 return listOfAllOrderedItemByCustomerId;
	 }
	 
	 
	 @GetMapping("/getAllProductListOfAllCustomersForAdmin")
	 public Response getAllProductListOfAllCustomersForAdmin() {
		 
		 Response allProductListOfAllCustomersForAdmin = orderService.getAllProductListOfAllCustomersForAdmin();
		 return allProductListOfAllCustomersForAdmin;
	 }
	 
	 
	 @PutMapping("/updateOrderByOrderId/{oid}")
	 public Response updateOrderByOrderId(@PathVariable(name="oid") Long oid, @RequestBody OrderUpdateReq body  ) {
		 Response updateOrder = orderService.updateOrder(body.getCustomerId(),body.getOrderItemDTOs(),body.getStatus(),oid,body.getDeliveryOrderAddress(),body.getFinalPayableAmount());
		 return updateOrder;
		 
		// int customerId, List<OrderProductRelUpdateReqDto> orderItemDTOs, String status, Long oid
		 
	 }
	 
	 
	 @GetMapping("/getOrderByOid/{oid}")
	 public Response getOrderByOidMain(@PathVariable Long oid) {
		 Response orderByOid = orderService.getOrderByOid(oid);
		 return orderByOid;
	 }
	 
	 
	 
	 
	 
	 
	 
}
