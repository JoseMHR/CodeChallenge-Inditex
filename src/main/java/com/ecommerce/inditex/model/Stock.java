package com.ecommerce.inditex.model;

import org.apache.commons.csv.CSVRecord;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Stock {

	private Integer sizeId;
	private Integer quantity;

	public static Stock fromCsvRecord(CSVRecord csvRecord) {
		return new Stock(Integer.parseInt(csvRecord.get(0).trim()), Integer.parseInt(csvRecord.get(1).trim()));
	}
}
