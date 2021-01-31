package com.toteuch.tftoptimizer.ihm.operation;

import java.util.Map;

import com.toteuch.tftoptimizer.domaine.Item;
import com.toteuch.tftoptimizer.ihm.main.Concierge;

/**
 * Called when the application starts, to retrieve component item object list
 */
public class ComponentItemsLoadOperation extends AbstractTFTOptimizerOperation<Map<String, Item>> {

	public ComponentItemsLoadOperation(Concierge concierge) {
		super(concierge, "Component items load");
	}

//----------------------------------------------------------------------------
//  Operation
//----------------------------------------------------------------------------

	@Override
	protected Map<String, Item> performOperation() throws Exception {
		LOG.debug("Requesting components items list...");
		return getConcierge().getService().getComponentItems();
	}

	@Override
	protected void onSuccess(Map<String, Item> componentItems) {
		LOG.trace("Success on requesting components items list");
		getConcierge().getMainFrame().setComponentsList(componentItems);
		new ChampionsLoadOperation(getConcierge()).start();
	}
}
