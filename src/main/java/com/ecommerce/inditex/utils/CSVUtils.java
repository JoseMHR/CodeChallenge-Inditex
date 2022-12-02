package com.ecommerce.inditex.utils;

import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.StreamSupport;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.csv.CSVFormat;
import org.springframework.stereotype.Service;

import com.ecommerce.inditex.model.Product;
import com.ecommerce.inditex.model.Size;
import com.ecommerce.inditex.model.Stock;

@Service
public class CSVUtils {

	static Logger logger = Logger.getLogger(CSVUtils.class.getName());
	private final CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT).build();

	public MultiValuedMap readCSV(String type) {

		MultiValuedMap<Integer, Object> resulMap = new ArrayListValuedHashMap<>();

		try {
			switch (type) {
			case Constants.PRODUCT:
				StreamSupport
						.stream(format
								.parse(new FileReader(
										Constants.BASE_RESOURCE + Constants.PRODUCT + Constants.FORMATFILE))
								.spliterator(), true)
						.map(csvRecord -> Product.fromCsvRecord(csvRecord))
						.forEach(entry -> resulMap.put(entry.getSequence(), entry.getId()));
				break;
			case Constants.SIZE:
				StreamSupport
						.stream(format
								.parse(new FileReader(Constants.BASE_RESOURCE + Constants.SIZE + Constants.FORMATFILE))
								.spliterator(), true)
						.map(csvRecord -> Size.fromCsvRecord(csvRecord))
						.forEach(entry -> resulMap.put(entry.getProductId(), entry));
				break;
			default:
				StreamSupport
						.stream(format
								.parse(new FileReader(Constants.BASE_RESOURCE + Constants.STOCK + Constants.FORMATFILE))
								.spliterator(), true)
						.map(csvRecord -> Stock.fromCsvRecord(csvRecord))
						.forEach(entry -> resulMap.put(entry.getSizeId(), entry.getQuantity()));
				break;
			}

		} catch (Exception e) {
			logger.log(Level.INFO, "Error was happend during readCSV: " + e.getMessage());
		}
		return resulMap;
	}

}