package com.altimetrik.orderservice.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.altimetrik.orderservice.entity.Order;
import com.altimetrik.orderservice.entity.OrderItem;
import com.altimetrik.orderservice.exception.OrderNotFoundException;
import com.altimetrik.orderservice.repository.OrderRepository;
import com.altimetrik.orderservice.service.OrderItemServiceProxy;
import com.altimetrik.orderservice.service.OrderService;

@RestController
public class OrderServiceController {
	
	@Autowired
	private OrderRepository repository;
	@Autowired
	private OrderItemServiceProxy proxy;
	@Autowired
	private OrderService service;
	
	@GetMapping("/orders")
	public List<Order> getOrders(){
		
		List<Order> orders=repository.findAll();
		if(orders.isEmpty())
			throw new OrderNotFoundException("No Orders found");
		else 
			return orders;
		
	}
	
	@GetMapping("/ordersFeign/{pcode}")
	public OrderItem getOrderItemByProductCodeFeign(@PathVariable String pcode){
		
		OrderItem items= proxy.getOrderItemByProductCode(pcode);		
		return items;
		
	}
	
	@GetMapping("/ordersFeign/{pName}/{pcode}/{quantity}")
	public OrderItem getOrderItemByProductNameCodeQuantityFeign(@PathVariable String pName,@PathVariable String pcode,
			@PathVariable Integer quantity){
				
		OrderItem items= service.getOrderItemByProductNameCodeAndQuantity(pName,pcode, quantity);
		return items;
		
	}

	@GetMapping("/orders/{id}")
	public Order getOrder(@PathVariable Long id){
		
		Optional<Order> order=repository.findById(id);
		if(order.isPresent())
			return order.get();
		else 
			throw new OrderNotFoundException("Order not found with id "+ id);
		
	}
	
	@PostMapping("/orders")
	public ResponseEntity<Object> createOrder(@Valid @RequestBody Order order){
		
		Order ord=service.createNewOrder(order);		
		URI location= ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").buildAndExpand(ord.getId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
}
