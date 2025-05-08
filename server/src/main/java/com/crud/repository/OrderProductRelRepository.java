package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.entity.OrderProductRel;
import java.util.List;


@Repository
public interface OrderProductRelRepository extends JpaRepository<OrderProductRel, Long> {
	
	List<OrderProductRel> findByOrderId(Long orderId);

}
