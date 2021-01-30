package com.toteuch.tftoptimizer.ihm.operation;

import java.util.List;

import com.toteuch.tftoptimizer.domaine.Item;
import com.toteuch.tftoptimizer.ihm.main.Concierge;

/**
 * Called when the application starts, to retrieve component item object list
 */
public class ComponentItemsLoadOperation extends AbstractTFTOptimizerOperation<List<Item>> {

	public ComponentItemsLoadOperation(Concierge concierge) {
		super(concierge, "Component items load");
	}

//----------------------------------------------------------------------------
//  Operation
//----------------------------------------------------------------------------

	@Override
	protected List<Item> performOperation() throws Exception {
		LOG.debug("Requesting components items list...");
		return getConcierge().getService().getComponentItems();
	}

	@Override
	protected void onSuccess(List<Item> componentItems) {
		LOG.trace("Success on requesting components items list");
		getConcierge().getMainFrame().setComponentsList(componentItems);
		new CombinedItemsLoadOperation(getConcierge()).start();
	}
}
