/**
 * ColourRepositoryImpl.java
 */
package com.ap00921.website.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ap00921.website.exceptions.colour.ColourIdException;
import com.ap00921.website.model.product.Colour;
import com.ap00921.website.model.product.Variation;
import com.ap00921.website.repository.ColourRepository;
import com.ap00921.website.service.ColourService;
import com.ap00921.website.service.VariationService;

/**
 * @author Alex Daniel Popa
 *
 */
@Service
public class ColourServiceImpl implements ColourService {

	@Autowired
	private ColourRepository colourRepository;
	
	@Autowired
	private VariationService variationService;
	
	@Override
	public Colour insert(Colour colour) {
		colour.setCreatedAt(new Date());
		
		List<Variation> newVariations = new ArrayList<Variation>();
		for (Variation v : colour.getProductVariations()) {
			newVariations.add(this.variationService.insert(v));
		}
		
		return this.colourRepository.insert(colour);
	}

	@Override
	public Colour update(Colour colour) {
		Colour c = this.colourRepository.findByColourId(colour.getColourId());
		
//		CASE 1: add new one
		if (c == null) {
			return this.insert(colour);
		}
		
		if (colour.getCreatedAt() != c.getCreatedAt()) {
			colour.setCreatedAt(c.getCreatedAt());
		}
		
//		CASE 2: nothing changed
		if(c.equals(colour)) {
			return colour;
		}
		
//		CASE 3: update existing one
		List<Variation> newVariations = new ArrayList<Variation>();
		for (Variation v : colour.getProductVariations()) {
			newVariations.add(this.variationService.update(v));
		}
		
		for (Variation w : colour.getProductVariations())
			c.getProductVariations().removeIf(v -> v.getVariationId().equals(w.getVariationId()));
		
		this.variationService.removeByList(c.getProductVariations());
		
		colour.setUpdatedAt(new Date());
		return this.colourRepository.save(colour);
	}

	@Override
	public void remove(String colour) {
		// TODO Auto-generated method stub
	}

	@Override
	public void removeByList(List<Colour> colours) {
		for(Colour c : colours) {
			Colour colour = this.colourRepository.findByColourId(c.getColourId());
			
			if (colour == null) {
				throw new ColourIdException("The colour with id '" + colour.getColourId() + "' does not exist");
			}
			
			this.variationService.removeByList(c.getProductVariations());

			this.colourRepository.deleteById(colour.getColourId());
		}
	}


	@Override
	public void updateStockWithQuantity(String colourId, Integer number) {
		// TODO Auto-generated method stub
		
	}

	

}
