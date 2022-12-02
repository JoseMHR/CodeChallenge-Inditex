package com.ecommerce.inditex.model;

import org.apache.commons.csv.CSVRecord;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Size {

	private Integer id;
	private Integer productId;
	private Boolean backSoon;
	private Boolean special;

	public static Size fromCsvRecord(CSVRecord csvRecord) {
		return new Size(Integer.parseInt(csvRecord.get(0).trim()), Integer.parseInt(csvRecord.get(1).trim()),
				Boolean.parseBoolean(csvRecord.get(2).trim()), Boolean.parseBoolean(csvRecord.get(3).trim()));
	}
}
