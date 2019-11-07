package com.bookingboss.app.model;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder(value = { "id", "timestamp", "products", "errors" })
@JsonInclude(Include.NON_NULL)
public class ProductJson {

	@NotBlank
	private String id;

	@NotNull(message = "timestamp is mandatory")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;

	private Collection<ParamHolder> products;
	private Collection<Error> errors;

	@Valid
	@JsonInclude(Include.NON_NULL)
	static public class ParamHolder {

		@NotNull
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

	@JsonInclude(Include.NON_NULL)
	static public class Error {
		private Long id;
		private String message;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Collection<ParamHolder> getProducts() {
		return products;
	}

	public void setProducts(Collection<ParamHolder> products) {
		this.products = products;
	}

	public Collection<Error> getErrors() {
		return errors;
	}

	public void setErrors(Collection<Error> errors) {
		this.errors = errors;
	}

}
