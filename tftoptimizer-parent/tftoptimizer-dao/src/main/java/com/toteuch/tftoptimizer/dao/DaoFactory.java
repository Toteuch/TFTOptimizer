package com.toteuch.tftoptimizer.dao;

import com.toteuch.tftoptimizer.dao.impl.ItemDao;

public class DaoFactory {
	private static volatile IItemDao itemDao = null;
	
	private DaoFactory() {}
	
	public final static IItemDao getIItemDaoInstance() {
		if(null == itemDao) {
			synchronized (IItemDao.class) {
				if(null == itemDao) {
					itemDao = new ItemDao();
				}
			}
		}
		return itemDao;
	}
}
