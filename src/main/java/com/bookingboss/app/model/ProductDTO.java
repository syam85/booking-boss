package com.bookingboss.app.model;

public class ProductDTO {

	private Long id;
	private String name;
	private Integer quantity;
	private Double salemount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getSalemount() {
		return salemount;
	}

	public void setSalemount(Double salemount) {
		this.salemount = salemount;
	}

}
