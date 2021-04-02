/**
 * VariationRepository.java
 */
package com.ap00921.website.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ap00921.website.model.product.Variation;

/**
 * @author Alex Daniel Popa
 *
 */
@Repository
public interface VariationRepository extends MongoRepository<Variation, String>{
	
	Variation findByVariationId(String variationId);

	Variation deleteByVariationId(String variationId);
}
