package com.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.entity.DeliveryOrderAddress;

public interface DeliveryOrderAddressRepository extends JpaRepository<DeliveryOrderAddress, Long> {

}
