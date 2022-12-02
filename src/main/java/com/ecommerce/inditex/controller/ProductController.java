package com.ecommerce.inditex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.inditex.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	@Operation(summary = "Find all products", description = "This endpoint find all products visible")
	@ApiResponse(responseCode = "200", description = "success")
	ResponseEntity<List<Integer>> findListProduct() {
		return new ResponseEntity<>(productService.findProducts(), HttpStatus.OK);
	}
}
