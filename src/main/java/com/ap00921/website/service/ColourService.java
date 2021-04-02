/**
 * ColourService.java
 */
package com.ap00921.website.service;

import java.util.List;

import com.ap00921.website.model.product.Colour;

/**
 * @author Alex Daniel Popa
 *
 */
public interface ColourService {

	public Colour insert(Colour colour);

	public Colour update(Colour colour);

	public void remove(String colour);

	public void removeByList(List<Colour> colours);

	public void updateStockWithQuantity(String colourId, Integer number);
}
