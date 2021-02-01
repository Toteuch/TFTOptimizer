package com.toteuch.tftoptimizer.ihm.main;

import com.toteuch.tftoptimizer.ihm.action.PinFrame;
import com.toteuch.tftoptimizer.ihm.action.QuitTFTOptimizer;
import com.toteuch.tftoptimizer.ihm.action.Reset;

/**
 * A central location for access to shared action instances. All instances are
 * public members, set when the assocated GUI object is constructed.
 */
public class ActionRegistry {

	public QuitTFTOptimizer quitTFTOptimizer;
	public PinFrame pinFrame;
	public Reset reset;

	protected ActionRegistry(Concierge concierge) {
		quitTFTOptimizer = new QuitTFTOptimizer();
		pinFrame = new PinFrame(concierge);
		reset = new Reset(concierge);
	}
}
