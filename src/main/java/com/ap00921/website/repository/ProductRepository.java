/**
 * ProductRepository.java
 */
package com.ap00921.website.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ap00921.website.model.product.Product;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;


/**
 * @author Alex Daniel Popa
 *
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    public Product findByProductId(String productId);

    public List<Product> findByProductPriceLessThan(Double max);

    @Query("{'productVariations.variationId': ?0}")
    public Product findByVariationId(String variationId); 
    
}
