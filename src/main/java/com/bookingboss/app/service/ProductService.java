package com.bookingboss.app.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookingboss.app.entity.Product;
import com.bookingboss.app.exception.InternalServerException;
import com.bookingboss.app.exception.ProductNotFoundException;
import com.bookingboss.app.model.ProductJson;
import com.bookingboss.app.model.ProductJson.Error;
import com.bookingboss.app.model.ProductJson.ParamHolder;
import com.bookingboss.app.repository.ProductRepository;

@Service
public class ProductService {

	Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	private ProductRepository repository;

	public void save(ProductJson productJson) throws ParseException {
		Collection<Product> products = new ArrayList<>();
		for (ParamHolder productHolder : productJson.getProducts()) {
			Product product = new Product();
			product.setEventId(productJson.getId());
			product.setEventTimestamp(productJson.getTimestamp());
			product.setId(productHolder.getId());
			product.setName(productHolder.getName());
			product.setQuantity(productHolder.getQuantity());
			product.setSalemount(productHolder.getSalemount());
			products.add(product);
		}

		repository.saveAll(products);

	}

	public ProductJson findAll(ProductJson productJson) {
		Collection<Product> products = repository.findAllByEventIdAndEventTimestampAndIdIn(productJson.getId(),
				productJson.getTimestamp(),
				productJson.getProducts().stream().map(elem -> elem.getId()).collect(Collectors.toList()));

		if (products != null && products.isEmpty()) {
			throw new ProductNotFoundException("No records found");
		}

		ProductJson productJsonRes = new ProductJson();
		productJsonRes.setId(productJson.getId());
		productJsonRes.setTimestamp(productJson.getTimestamp());

		Collection<ParamHolder> productsRes = new ArrayList<>();
		Set<Long> productIds = new HashSet<>();
		for (Product product : products) {
			ParamHolder productHolder = new ParamHolder();
			productHolder.setId(product.getId());
			productHolder.setName(product.getName());
			productHolder.setQuantity(product.getQuantity());
			productHolder.setSalemount(product.getSalemount());
			productsRes.add(productHolder);
			productIds.add(product.getId());
		}
		productJsonRes.setProducts(productsRes);

		Collection<Error> errors = new ArrayList<>();

		for (ParamHolder param : productJson.getProducts()) {
			if (!productIds.contains(param.getId())) {
				Error error = new Error();
				error.setId(param.getId());
				error.setMessage("NOT_FOUND");
				errors.add(error);
			}
		}
		productJsonRes.setErrors(errors);

		return productJsonRes;

	}

}