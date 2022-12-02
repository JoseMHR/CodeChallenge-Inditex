package com.ecommerce.inditex.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MultiValuedMap;
import org.springframework.stereotype.Service;

import com.ecommerce.inditex.model.Size;

@Service
public class FilterConditions {

	// Filtro de casuisticas
	public Boolean filterAllConditions(Collection<Size> sizeCollection,
			MultiValuedMap<Integer, Integer> productsStock) {
		return sizeCollection.stream()
				.filter(size -> (caseBackSoon(size) || caseHaveStock(size, productsStock))
						&& (!size.getSpecial() || caseSpecial(sizeCollection, productsStock)))
				.collect(Collectors.collectingAndThen(Collectors.toList(), list -> !list.isEmpty()));
	}

	private Boolean caseBackSoon(Size size) {
		return Boolean.TRUE.equals(size.getBackSoon());
	}

	private Boolean caseHaveStock(Size size, MultiValuedMap<Integer, Integer> productsStock) {
		return productsStock.containsKey(size.getId())
				&& productsStock.get(size.getId()).stream().filter(stock -> stock > 0)
						.collect(Collectors.collectingAndThen(Collectors.toList(), l -> !l.isEmpty()));
	}

	private boolean caseSpecial(Collection<Size> sizeCollection, MultiValuedMap<Integer, Integer> productsStock) {
		Boolean haveSpecialStock = false;
		Boolean haveNoSpecialStock = false;

		Iterator<Size> itr = sizeCollection.iterator();
		while (itr.hasNext()) {
			Size size = itr.next();
			if (caseHaveStock(size, productsStock)) {
				if (size.getSpecial()) {
					haveSpecialStock = true;
				} else {
					haveNoSpecialStock = true;
				}
			}
		}
		return (haveSpecialStock && haveNoSpecialStock);
	}
}