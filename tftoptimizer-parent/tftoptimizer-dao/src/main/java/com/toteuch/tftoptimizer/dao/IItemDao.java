package com.toteuch.tftoptimizer.dao;

import java.util.List;

import com.toteuch.tftoptimizer.domaine.Item;

public interface IItemDao {
	public List<Item> getCombineds();

	public List<Item> getComponents();

	public void emptyCache();
}
