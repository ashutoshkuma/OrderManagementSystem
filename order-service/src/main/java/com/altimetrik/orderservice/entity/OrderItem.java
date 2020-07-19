package com.altimetrik.orderservice.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Embeddable
public class OrderItem {

	private Long id;
	
	@NotNull(message="Product Name should not be null")
	@ApiModelProperty(notes="Product Name should have atleast 2 characters")
	@Size(min=2, message="Product name should have atleast 2 characters")
	private String productName;
	
	@NotNull(message="Product Code should not be null")
	@ApiModelProperty(notes="Product Code should have atleast 2 characters")
	@Size(min=1, message="Product code should have atleast 1 characters")
	private String productCode;
	
	@ApiModelProperty(notes="Order Item quantity must be equal or greater than 1")
	@Min(value=1, message="Order Item quantity must be equal or greater than 1") 
	private Integer quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}	
	
}
