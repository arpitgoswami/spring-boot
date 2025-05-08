package com.crud.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.Customer;
import com.crud.entity.CustomerProductCart;
import com.crud.entity.Product;

public interface CustomerProductCartRepository extends JpaRepository<CustomerProductCart, Long> {

	List<CustomerProductCart> findByCustomer(Optional<Customer> customerById);
	 Optional<Product> findByProducts(Product products);
	Optional<CustomerProductCart> findByCustomerAndProducts(Optional<Customer> customerById, Product product);
}
