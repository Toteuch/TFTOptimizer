package com.toteuch.tftoptimizer.dao;

import java.util.List;
import java.util.Map;

import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.domaine.Item;

public interface IChampDao {
	public List<Champion> getAllChamps(Map<String, Item> combineds);
}
