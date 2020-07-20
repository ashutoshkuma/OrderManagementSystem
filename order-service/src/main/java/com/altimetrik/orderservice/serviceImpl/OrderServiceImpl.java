package com.altimetrik.orderservice.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.orderservice.entity.Order;
import com.altimetrik.orderservice.entity.OrderItem;
import com.altimetrik.orderservice.exception.OrderItemNotAvailableException;
import com.altimetrik.orderservice.repository.OrderRepository;
import com.altimetrik.orderservice.service.OrderItemServiceProxy;
import com.altimetrik.orderservice.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository repository;
	@Autowired
	private OrderItemServiceProxy proxy;

	@Override
	@Transactional
	public Order createNewOrder(@Valid Order order) {
		
		List<OrderItem> items=order.getOrderItem();
		List<OrderItem> list =checkAvailability(items);
		List<OrderItem> updatedList=list;
		for(int i=0;i<list.size();i++) {
			OrderItem item=items.get(i);
			item.setId(list.get(i).getId());
			items.set(i, item);
			
			OrderItem updateditem=list.get(i);
			updateditem.setQuantity(list.get(i).getQuantity()-item.getQuantity());
			updatedList.set(i,updateditem);
		}
		Order newOrder= order;
		newOrder.setCustomerName(order.getCustomerName());
		newOrder.setShippingAddress(order.getShippingAddress());
		newOrder.setOrderItem(items);
		newOrder.setOrderDateTime(LocalDateTime.now());
		newOrder.setTotal(order.getTotal());
		Order ord=repository.save(newOrder);
		updateOrderItems(updatedList); // to update the items which are ordered
		return ord;
	}

	private void updateOrderItems(List<OrderItem> list) {
				
		list.stream().forEach(item->proxy.updateOrderItem(item));
	}

	private List<OrderItem> checkAvailability(List<OrderItem> items) {
		
		List<OrderItem> list=new ArrayList<OrderItem>();
		items.stream().forEach(item->{
			try {
				list.add(proxy.checkOrderItemByProductNameCodeAndQuantity(item.getProductName(),
						item.getProductCode(),item.getQuantity()));
			}catch(Exception e){
				throw new OrderItemNotAvailableException("Item Not available with product Name "+item.getProductName()+
						" product Code "+item.getProductCode()+ " and quantity "+item.getQuantity());				
			}	
		});
		return list;		
	}

	
	@Override
	public OrderItem checkOrderItemByProductNameCodeAndQuantity(String pName,String pcode, Integer quantity) {
		
		return proxy.checkOrderItemByProductNameCodeAndQuantity(pName, pcode, quantity);
	}

}
