package com.toteuch.tftoptimizer.dao;

import java.util.Map;

import com.toteuch.tftoptimizer.domaine.Item;

public interface IItemDao {
	public Map<String, Item> getCombineds();

	public Map<String, Item> getComponents();

	public void emptyCache();
}
