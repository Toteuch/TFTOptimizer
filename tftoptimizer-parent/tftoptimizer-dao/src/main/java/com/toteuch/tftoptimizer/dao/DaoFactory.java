package com.toteuch.tftoptimizer.dao;

import com.toteuch.tftoptimizer.dao.impl.ChampDao;
import com.toteuch.tftoptimizer.dao.impl.ItemDao;

public class DaoFactory {
	private static volatile IItemDao itemDao = null;
	private static volatile IChampDao champDao = null;

	private DaoFactory() {
	}

	public final static IItemDao getIItemDaoInstance() {
		if (null == itemDao) {
			synchronized (IItemDao.class) {
				if (null == itemDao) {
					itemDao = new ItemDao();
				}
			}
		}
		return itemDao;
	}

	public final static IChampDao getIChampDaoInstance() {
		if (null == champDao) {
			synchronized (IChampDao.class) {
				if (null == champDao) {
					champDao = new ChampDao();
				}
			}
		}
		return champDao;
	}
}
