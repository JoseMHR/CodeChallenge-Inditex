package com.ecommerce.inditex.service;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MultiValuedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.inditex.model.Size;
import com.ecommerce.inditex.utils.CSVUtils;
import com.ecommerce.inditex.utils.Constants;
import com.ecommerce.inditex.utils.FilterConditions;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	CSVUtils csvUtils;

	@Autowired
	FilterConditions filterConditions;

	@Override
	public List<Integer> findProducts() {
		MultiValuedMap<Integer, Integer> productsList = csvUtils.readCSV(Constants.PRODUCT);
		MultiValuedMap<Integer, Size> productsSize = csvUtils.readCSV(Constants.SIZE);
		MultiValuedMap<Integer, Integer> productsStock = csvUtils.readCSV(Constants.STOCK);

		return productsList.entries().stream()
				.filter(prod -> filterConditions.filterAllConditions(productsSize.get(prod.getValue()), productsStock))
				.map(prod -> prod.getValue()).collect(Collectors.toList());
	}

}