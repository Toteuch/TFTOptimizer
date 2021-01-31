package com.toteuch.tftoptimizer.dao.impl;

import java.util.List;
import java.util.Map;

import com.toteuch.tftoptimizer.dao.IChampDao;
import com.toteuch.tftoptimizer.dao.TFTOptimizerAbstractDao;
import com.toteuch.tftoptimizer.dao.parser.html.ChampParser;
import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.domaine.Item;

public class ChampDao extends TFTOptimizerAbstractDao implements IChampDao {

	public List<Champion> getAllChamps(Map<String, Item> combineds) {
		return ChampParser.getChampions(combineds);
	}

}
