package com.toteuch.tftoptimizer.dao.impl;

import java.util.List;

import com.toteuch.tftoptimizer.dao.IItemDao;
import com.toteuch.tftoptimizer.dao.TFTOptimizerAbstractDao;
import com.toteuch.tftoptimizer.dao.parser.html.ItemParser;
import com.toteuch.tftoptimizer.domaine.Item;

public class ItemDao extends TFTOptimizerAbstractDao implements IItemDao {

	public List<Item> getCombineds() {
		return ItemParser.getCombinedItems();
	}

	public List<Item> getComponents() {
		return ItemParser.getComponentItems();
	}

	/**
	 * Empty all cache
	 */
	public void emptyCache() {
		ItemParser.emptyCache();
	}
}
