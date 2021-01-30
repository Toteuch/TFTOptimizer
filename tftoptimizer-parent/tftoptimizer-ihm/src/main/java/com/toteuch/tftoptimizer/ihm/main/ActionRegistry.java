package com.toteuch.tftoptimizer.ihm.main;

import com.toteuch.tftoptimizer.ihm.action.QuitTFTOptimizer;

/**
 * A central location for access to shared action instances. All instances are
 * public members, set when the assocated GUI object is constructed.
 */
public class ActionRegistry {

	public QuitTFTOptimizer quitTFTOptimizer;

	protected ActionRegistry(Concierge concierge) {
		quitTFTOptimizer = new QuitTFTOptimizer();
	}
}
