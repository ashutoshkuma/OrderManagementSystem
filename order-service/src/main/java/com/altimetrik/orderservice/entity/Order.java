package com.altimetrik.orderservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description="All details about Order.")
@Entity
@Table(name="order_table")
public class Order {
	
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@NotNull(message="Customer Name should not be null")
	@ApiModelProperty(notes="Customer Name should have atleast 2 characters")
	@Size(min=2,message="Customer Name should have atleast 2 characters")
	private String customerName;
	
	@NotNull(message="Shipping Address should not be null")
	@ApiModelProperty(notes="Shipping Address should have atleast 2 characters")
	@Size(min=2, message="Shipping Address should have atleast 2 characters")
	private String shippingAddress;
	
	private LocalDateTime orderDate;
	
	@ElementCollection
	private List<OrderItem> orderItem=new ArrayList<OrderItem>() ;
	
	@ApiModelProperty(notes="Price must be equal or greater than 1")
	@Min(value=1, message="Price must be equal or greater than 1")
	private Double total;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDate;
	}

	public void setOrderDateTime(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}	

}
