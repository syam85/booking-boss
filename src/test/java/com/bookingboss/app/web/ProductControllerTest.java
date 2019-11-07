package com.bookingboss.app.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.bookingboss.app.AbstractTest;
import com.bookingboss.app.exception.ProductNotFoundException;
import com.bookingboss.app.model.ProductJson;
import com.bookingboss.app.model.ProductJson.ParamHolder;
import com.bookingboss.app.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest extends AbstractTest {

	@InjectMocks
	private ProductController productController;

	@MockBean
	private ProductService productService;

	private String uri = "/products";

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void createProduct() throws Exception {
		String inputJson = super.mapToJson(getProductJson());

		Mockito.doNothing().when(productService).save(any(ProductJson.class));

		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.CREATED.compareTo(HttpStatus.valueOf(status)), 0);
		Mockito.verify(productService).save(any(ProductJson.class));
	}

	@Test
	public void getProductsList() throws Exception {
		ProductJson product = getProductJson();
		Mockito.when(productService.findAll(any(ProductJson.class))).thenReturn(product);
		String inputJson = super.mapToJson(product);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		assertEquals(200, mvcResult.getResponse().getStatus());

		String content = mvcResult.getResponse().getContentAsString();
		ProductJson productlist = super.mapFromJson(content, ProductJson.class);

		assertTrue(productlist.getProducts().size() > 0);
		Mockito.verify(productService).findAll(any(ProductJson.class));
	}

	private ProductJson getProductJson() {
		ProductJson product = new ProductJson();
		product.setId("1");
		product.setTimestamp(new Date());

		ParamHolder productHolder = new ParamHolder();
		productHolder.setId(1L);
		product.setProducts(Arrays.asList(productHolder));
		return product;
	}

	@Test
	public void getProductsWithNotFoundException() throws Exception {
		Mockito.doThrow(ProductNotFoundException.class).when(productService).findAll(any(ProductJson.class));
		String inputJson = super.mapToJson(getProductJson());
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.get(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		assertEquals(404, mvcResult.getResponse().getStatus());

	}

}
