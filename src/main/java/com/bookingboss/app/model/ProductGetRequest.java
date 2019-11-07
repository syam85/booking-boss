package com.bookingboss.app.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(Include.NON_NULL)
public class ProductGetRequest {
	@NotBlank
	private String id;

	@NotNull(message = "timestamp is mandatory")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private Date timestamp;

	private List<ProductIdHolder> products;

	static public class ProductIdHolder {

		private Long id;

		public ProductIdHolder() {

		}

		public ProductIdHolder(Long id) {
			this.id = id;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}
	}

	public static void main(String[] args) throws JsonProcessingException {

		ProductGetRequest re = new ProductGetRequest();
		re.setProducts(Arrays.asList(new ProductIdHolder(1L), new ProductIdHolder(2L)));
		re.setId("1");
		re.setTimestamp(new Date());

		System.out.println(new ObjectMapper().writeValueAsString(re));

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<ProductIdHolder> getProducts() {
		return products;
	}

	public void setProducts(List<ProductIdHolder> products) {
		this.products = products;
	}

}
