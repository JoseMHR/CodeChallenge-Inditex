package com.ecommerce.inditex.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ProductService {

	List<Integer> findProducts();
}
