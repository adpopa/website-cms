/**
 * ColourRepository.java
 */
package com.ap00921.website.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ap00921.website.model.product.Colour;

/**
 * @author Alex Daniel Popa
 *
 */
@Repository
public interface ColourRepository extends MongoRepository<Colour, String>{
	
	public Colour findByColourId(String colourId);
	
}
