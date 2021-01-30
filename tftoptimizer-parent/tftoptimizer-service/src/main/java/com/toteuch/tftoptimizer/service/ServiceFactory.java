package com.toteuch.tftoptimizer.service;

import com.toteuch.tftoptimizer.service.impl.TFTOptimizerService;

public class ServiceFactory {

	private static volatile ITFTOptimizerService tftoptimizerService = null;

	public final static ITFTOptimizerService getITFTOptimizerService() {
		if (null == tftoptimizerService) {
			synchronized (ITFTOptimizerService.class) {
				if (null == tftoptimizerService) {
					tftoptimizerService = new TFTOptimizerService();
				}
			}
		}
		return tftoptimizerService;
	}
}
