package com.toteuch.tftoptimizer.ihm.main;

import com.toteuch.tftoptimizer.ihm.action.AddToSelectedChampion;
import com.toteuch.tftoptimizer.ihm.action.PinFrame;
import com.toteuch.tftoptimizer.ihm.action.QuitTFTOptimizer;
import com.toteuch.tftoptimizer.ihm.action.Reset;
import com.toteuch.tftoptimizer.ihm.action.SwitchMainPanelView;

/**
 * A central location for access to shared action instances. All instances are
 * public members, set when the assocated GUI object is constructed.
 */
public class ActionRegistry {

	public QuitTFTOptimizer quitTFTOptimizer;
	public PinFrame pinFrame;
	public Reset reset;
	public SwitchMainPanelView switchMainPanelView;
	public AddToSelectedChampion addToSelectedChampion;

	protected ActionRegistry(Concierge concierge) {
		quitTFTOptimizer = new QuitTFTOptimizer();
		pinFrame = new PinFrame(concierge);
		reset = new Reset(concierge);
		switchMainPanelView = new SwitchMainPanelView(concierge);
		addToSelectedChampion = new AddToSelectedChampion(concierge);
	}
}
