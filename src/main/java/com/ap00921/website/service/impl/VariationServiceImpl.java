/**
 * VariationServiceImpl.java
 */
package com.ap00921.website.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ap00921.website.exceptions.variation.VariationIdException;
import com.ap00921.website.exceptions.variation.VariationStockException;
import com.ap00921.website.model.product.Variation;
import com.ap00921.website.repository.VariationRepository;
import com.ap00921.website.service.VariationService;

/**
 * @author Alex Daniel Popa
 *
 */
@Service
public class VariationServiceImpl implements VariationService {
	
	@Autowired
	private VariationRepository variationRepository;

	@Override
	public Variation insert(Variation variation) {
		variation.setCreatedAt(new Date());
		return this.variationRepository.insert(variation);
	}
	
	@Override
	public Variation update(Variation variation) {

		Variation v = this.variationRepository.findByVariationId(variation.getVariationId());
		
//		CASE 1: add new one
		if(v == null) {
			return this.insert(variation);
		}
		
		if (variation.getCreatedAt() != v.getCreatedAt()) {
			variation.setCreatedAt(v.getCreatedAt());
		}
		
//		CASE 2: nothing changed
		if(v.equals(variation)) {
			return variation;
		}
		
//		CASE 3: update existing one
		variation.setUpdatedAt(new Date());
		return this.variationRepository.save(variation);
	}

	@Override
	public void remove(String variationId) {
		Variation variation = this.variationRepository.findByVariationId(variationId);
		
		if(variation == null) {
			throw new VariationIdException("The variation with id '" + variationId + "' does not exist");
		}
		
		this.variationRepository.deleteById(variationId);
	}
	
	@Override
	public void removeByList(List<Variation> variations) {
		for(Variation v : variations) {
			Variation variation = this.variationRepository.findByVariationId(v.getVariationId());
			
			if(variation == null) {
				throw new VariationIdException("The variation with id '" + v.getVariationId() + "' does not exist");
			}
			
			this.variationRepository.deleteById(v.getVariationId());
		}
	}

	@Override
	public void updateStockWithQuantity(String variationId, Integer quantity) {
		if(quantity < 1 || quantity > 10) {
			throw new VariationStockException("Quantity must be between 1 and 10");
		}
		
		Variation variation = this.variationRepository.findByVariationId(variationId);
		
		if(variation == null) {
			throw new VariationIdException("The variation with id '" + variationId + "' does not exist");
		}
		
		variation.setProductQuantity(variation.getProductQuantity() - quantity);
		
		this.variationRepository.save(variation);
	}

}
