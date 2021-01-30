package com.toteuch.tftoptimizer.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.toteuch.tftoptimizer.dao.DaoFactory;
import com.toteuch.tftoptimizer.dao.IItemDao;
import com.toteuch.tftoptimizer.dao.utils.PropertiesUtils;
import com.toteuch.tftoptimizer.domaine.Item;
import com.toteuch.tftoptimizer.service.ITFTOptimizerService;
import com.toteuch.tftoptimizer.service.TFTOptimizerAbstractService;

public class TFTOptimizerService extends TFTOptimizerAbstractService implements ITFTOptimizerService {

	private IItemDao itemDao;

	private static List<Item> componentItems;
	private static List<Item> combinedItems;

	private static final Logger LOG = LogManager.getLogger(TFTOptimizerService.class);

	public TFTOptimizerService() {
		PropertiesUtils.readProperties("tftoptimizer.properties");
		//FileUtils.empty(PropertiesUtils.getRootFolder());
		itemDao = DaoFactory.getIItemDaoInstance();
	}

	public List<Item> getComponentItems() {
		if (componentItems == null) {
			componentItems = itemDao.getComponents();
		}
		return componentItems;

	}

	public List<Item> getCombinedItems() {
		if (combinedItems == null) {
			combinedItems = itemDao.getCombineds();
		}
		return combinedItems;
	}

	public void emptyCache() {
		LOG.info("Emptying cache...");
		itemDao.emptyCache();
	}

}
