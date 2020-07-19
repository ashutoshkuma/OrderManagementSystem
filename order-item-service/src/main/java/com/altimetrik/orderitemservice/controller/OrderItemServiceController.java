package com.altimetrik.orderitemservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.altimetrik.orderitemservice.entity.OrderItem;
import com.altimetrik.orderitemservice.exception.OrderItemNotFoundException;
import com.altimetrik.orderitemservice.repository.OrderItemRepository;



@RestController
public class OrderItemServiceController {
	
	@Autowired
	private OrderItemRepository repository;
	
	@GetMapping("/orderItems")
	public List<OrderItem> getOrderItem(){	
		List<OrderItem> orderItems=repository.findAll();
		if(orderItems.isEmpty())
			throw new OrderItemNotFoundException("No Items found");
		else 
			return orderItems;		
	}
	
	@GetMapping("/orderItems/{pCode}")
	public OrderItem getOrderItemByProductCode(@PathVariable String pCode){
		
		Optional<OrderItem> orderItem=repository.findByProductCode(pCode);
		if(orderItem.isPresent())
				return orderItem.get();
		else
			throw new OrderItemNotFoundException("Item Not found with product Code "+ pCode);	
		
	}	
	
	@GetMapping("/checkorderItems/{pName}/{pCode}/{quantity}")
	public OrderItem getOrderItemByProductNameCodeAndQuantity(@PathVariable String pName,
			@PathVariable String pCode,@PathVariable Integer quantity){
		
		Optional<OrderItem> orderItem=repository.findByProductNameAndProductCodeAndQuantityGreaterThanEqual(pName,
				pCode, quantity);
		if(orderItem.isPresent())
				return orderItem.get();
		else
			throw new OrderItemNotFoundException("Item Not found with product Name "+pName+
					" product Code "+pCode+ " and quantity "+quantity );	
		
	}	
	

	@GetMapping("/orderItem/{id}")
	public OrderItem getOrderItem(@PathVariable Long id){
		
		Optional<OrderItem> orderItem=repository.findById(id);
		if(orderItem.isPresent())
			return orderItem.get();
		else 
			throw new OrderItemNotFoundException("Item Not found with id "+ id);
		
	}
	
	@PostMapping("/orderItem")
	public ResponseEntity<Object> createOrder(@Valid @RequestBody OrderItem orderItem){
		OrderItem item=repository.save(orderItem);
		
		URI location= ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").buildAndExpand(item.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	@PutMapping("/orderItem")
	public ResponseEntity<Object> updateOrderItem(@Valid @RequestBody OrderItem orderItem){
		repository.save(orderItem);
		
		return ResponseEntity.ok().build();
		
	}
	
}
