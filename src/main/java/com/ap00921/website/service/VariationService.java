/**
 * VariationService.java
 */
package com.ap00921.website.service;

import java.util.List;

import com.ap00921.website.model.product.Variation;

/**
 * @author Alex Daniel Popa
 *
 */
public interface VariationService {

	public Variation insert(Variation variation);

	public Variation update(Variation variation);

	public void remove(String variation);

	public void removeByList(List<Variation> variations);

	public void updateStockWithQuantity(String variationId, Integer number);

}
