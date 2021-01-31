package com.toteuch.tftoptimizer.service;

import java.util.List;
import java.util.Map;

import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.domaine.Item;

public interface ITFTOptimizerService {
	public Map<String, Item> getComponentItems();

	public List<Champion> getChampions();

	public void emptyCache();

	public List<Champion> getChampionFromInput(List<Champion> champsToSort, Map<Item, Integer> components);
}
