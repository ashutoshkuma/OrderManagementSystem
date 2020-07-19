package com.altimetrik.orderservice.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.altimetrik.orderservice.entity.Order;
import com.altimetrik.orderservice.entity.OrderItem;


public interface OrderService {

	public Order createNewOrder(@Valid Order order);

	public OrderItem getOrderItemByProductNameCodeAndQuantity(String pname, String pcode, Integer quantity);

}
