package com.toteuch.tftoptimizer.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.toteuch.tftoptimizer.dao.DaoFactory;
import com.toteuch.tftoptimizer.dao.IChampDao;
import com.toteuch.tftoptimizer.dao.IItemDao;
import com.toteuch.tftoptimizer.dao.utils.PropertiesUtils;
import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.domaine.Item;
import com.toteuch.tftoptimizer.domaine.PreferedItem;
import com.toteuch.tftoptimizer.service.ITFTOptimizerService;
import com.toteuch.tftoptimizer.service.TFTOptimizerAbstractService;

public class TFTOptimizerService extends TFTOptimizerAbstractService implements ITFTOptimizerService {

	private IItemDao itemDao;
	private IChampDao champDao;

	private static Map<String, Item> componentItems;
	private static Map<String, Item> combinedItems;
	private static List<Champion> champs;

	private static final Logger LOG = LogManager.getLogger(TFTOptimizerService.class);

	public TFTOptimizerService() {
		PropertiesUtils.readProperties("tftoptimizer.properties");
		itemDao = DaoFactory.getIItemDaoInstance();
		champDao = DaoFactory.getIChampDaoInstance();
	}
// ---------------------------------------------------------------
// Interface	
// ---------------------------------------------------------------

	public Map<String, Item> getComponentItems() {
		if (componentItems == null) {
			componentItems = itemDao.getComponents();
		}
		return componentItems;

	}

	public List<Champion> getChampions() {
		LOG.info("Getting champs...");
		if (champs == null) {
			champs = champDao.getAllChamps(getCombinedItems());
		}
		return champs;
	}

	public void emptyCache() {
		LOG.info("Emptying cache...");
		itemDao.emptyCache();
	}

	public List<Champion> getChampionFromInput(List<Champion> champsToSort, Map<Item, Integer> components) {
		for (Champion champ : champsToSort) {
			int score = 0;
			List<Item> slamableItems = new ArrayList<Item>();
			List<Item> unusedItems = new ArrayList<Item>();
			Map<Item, Integer> workingMap = new HashMap<Item, Integer>();
			for (Item key : components.keySet()) {
				int prevValue = (components.get(key) != null ? components.get(key) : 0);
				workingMap.put(key, prevValue);
			}
			for (PreferedItem pItem : champ.getPreferedItems()) {
				if (slamableItems.size() >= 3) {
					break;
				}
				Item combinedItem = pItem.getItem();
				Item item1 = combinedItem.getCombination().get(0);
				Item item2 = combinedItem.getCombination().get(1);
				int item1Count = getItemCountInMats(workingMap, item1);
				int item2Count = getItemCountInMats(workingMap, item2);
				if ((item1 == item2 && item1Count >= 2) || (item1 != item2 && item1Count >= 1 && item2Count >= 1)) {
					score += (100 * pItem.getPresence());
					slamableItems.add(combinedItem);
					workingMap = decreaseItemNbr(workingMap, item1);
					workingMap = decreaseItemNbr(workingMap, item2);
				} else if (item1Count >= 1) {
					score += ((100 * pItem.getPresence()) / 2);
					workingMap = decreaseItemNbr(workingMap, item1);
				} else if (item2Count >= 1) {
					score += ((100 * pItem.getPresence()) / 2);
					workingMap = decreaseItemNbr(workingMap, item2);
				}
			}
			for (Item item : workingMap.keySet()) {
				int nb = workingMap.get(item);
				for (int i = 0; i < nb; i++) {
					unusedItems.add(item);
				}
			}

			champ.setScore(score);
			champ.setSlamableItems(slamableItems);
		}
		return sortByHighestScore(champsToSort);
	}

// ---------------------------------------------------------------
// Internals
// ---------------------------------------------------------------

	private Map<String, Item> getCombinedItems() {
		if (combinedItems == null) {
			combinedItems = itemDao.getCombineds();
		}
		return combinedItems;
	}

	private List<Champion> sortByHighestScore(List<Champion> champsToSort) {
		Collections.sort(champsToSort, new Comparator<Champion>() {
			public int compare(Champion c1, Champion c2) {
				return Double.compare(c2.getScore(), c1.getScore());
			}
		});
		return champsToSort;
	}

	private int getItemCountInMats(Map<Item, Integer> rawMats, Item item) {
		for (Item key : rawMats.keySet()) {
			if (StringUtils.equals(item.getName(), key.getName())) {
				return rawMats.get(key);
			}
		}
		return 0;
	}

	private static Map<Item, Integer> decreaseItemNbr(Map<Item, Integer> map, Item item) {
		Item key = null;
		for (Item keyItem : map.keySet()) {
			if (StringUtils.equals(item.getName(), keyItem.getName())) {
				key = keyItem;
				break;
			}
		}
		Integer nbr = map.get(key);
		if (nbr == null || nbr == 0) {
			LOG.error(String.format("Can't find any %s in the actual map of Item", item.getName()));
		}
		if (nbr == 1) {
			map.remove(key);
		} else {
			nbr--;
			map.put(item, nbr);
		}
		return map;
	}
}
