package com.bookingboss.app.web;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bookingboss.app.exception.InternalServerException;
import com.bookingboss.app.exception.ProductNotFoundException;
import com.bookingboss.app.model.ProductJson;
import com.bookingboss.app.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping
	public ResponseEntity<String> create(@RequestBody @Valid ProductJson product) throws ParseException {

		try {
			service.save(product);
		} catch (Throwable exception) {
			throw new InternalServerException("Error occured while saving data to database");
		}

		return new ResponseEntity<String>(HttpStatus.CREATED);
	}

	@GetMapping
	@ExceptionHandler({ ProductNotFoundException.class })
	public ProductJson get(@RequestBody ProductJson products) throws ParseException {

		try {
			return service.findAll(products);
		} catch (Throwable exception) {
			throw new InternalServerException("Error occured while getting data from database");
		}
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}