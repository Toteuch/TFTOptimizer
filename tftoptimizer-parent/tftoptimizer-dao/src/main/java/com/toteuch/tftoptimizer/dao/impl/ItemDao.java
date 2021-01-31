package com.toteuch.tftoptimizer.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.toteuch.tftoptimizer.dao.IItemDao;
import com.toteuch.tftoptimizer.dao.TFTOptimizerAbstractDao;
import com.toteuch.tftoptimizer.dao.parser.html.ItemParser;
import com.toteuch.tftoptimizer.domaine.Item;

public class ItemDao extends TFTOptimizerAbstractDao implements IItemDao {

	public Map<String, Item> getCombineds() {
		return getMapOf(ItemParser.getCombinedItems());
	}

	public Map<String, Item> getComponents() {
		return getMapOf(ItemParser.getComponentItems());
	}

	/**
	 * Empty all cache
	 */
	public void emptyCache() {
		ItemParser.emptyCache();
	}

	private Map<String, Item> getMapOf(List<Item> items) {
		Map<String, Item> map = new HashMap<String, Item>();
		for (Item i : items) {
			map.put(i.getName(), i);
		}
		return map;
	}
}
