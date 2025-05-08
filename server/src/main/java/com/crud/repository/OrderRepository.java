package com.crud.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.Customer;
import com.crud.entity.Order;
import java.util.List;


public interface OrderRepository extends JpaRepository<Order, Long> {

//	Optional<Order> findByCustomer(Customer customer);
	
	public List<Order> findByCustomer(Customer customer);
	
	
	
}
