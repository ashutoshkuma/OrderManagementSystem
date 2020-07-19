package com.altimetrik.orderitemservice.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.altimetrik.orderitemservice.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
	
	Optional<OrderItem> findByProductCode(String productCode);
	
	Optional<OrderItem> findByProductCodeAndQuantityGreaterThanEqual(String productCode,Integer quantity);

	Optional<OrderItem> findByProductNameAndProductCodeAndQuantityGreaterThanEqual(String productName,
			String productCode, Integer quantity);
	
}


