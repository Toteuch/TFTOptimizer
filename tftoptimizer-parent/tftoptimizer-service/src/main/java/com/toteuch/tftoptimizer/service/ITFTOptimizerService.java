package com.toteuch.tftoptimizer.service;

import java.util.List;

import com.toteuch.tftoptimizer.domaine.Item;

public interface ITFTOptimizerService {
	public List<Item> getComponentItems();

	public List<Item> getCombinedItems();

	public void emptyCache();
}
