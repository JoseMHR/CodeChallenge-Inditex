package com.ecommerce.inditex.model;

import org.apache.commons.csv.CSVRecord;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Product {

	private Integer id;
	private Integer sequence;

	public static Product fromCsvRecord(CSVRecord csvRecord) {
		return new Product(Integer.parseInt(csvRecord.get(0).trim()), Integer.parseInt(csvRecord.get(1).trim()));
	}
}
