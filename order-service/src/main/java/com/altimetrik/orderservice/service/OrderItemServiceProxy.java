package com.altimetrik.orderservice.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.altimetrik.orderservice.entity.OrderItem;

@FeignClient(name="order-item-service", url="localhost:8100")
public interface OrderItemServiceProxy {
	
	@GetMapping("/orderItems/{pCode}")
	public OrderItem getOrderItemByProductCode(@PathVariable String pCode);
	
	@GetMapping("/checkorderItems/{pName}/{pCode}/{quantity}")
	public OrderItem getOrderItemByProductNameCodeAndQuantity(@PathVariable String pName,
			@PathVariable String pCode,@PathVariable Integer quantity);
	
	@PutMapping("/orderItem")
	public ResponseEntity<Object> updateOrderItem(@Valid @RequestBody OrderItem orderItem);
	
}
