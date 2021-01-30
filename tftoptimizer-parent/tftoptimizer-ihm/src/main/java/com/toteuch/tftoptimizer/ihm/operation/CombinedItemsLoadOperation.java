package com.toteuch.tftoptimizer.ihm.operation;

import java.util.List;

import com.toteuch.tftoptimizer.domaine.Item;
import com.toteuch.tftoptimizer.ihm.main.Concierge;

public class CombinedItemsLoadOperation extends AbstractTFTOptimizerOperation<List<Item>> {

	public CombinedItemsLoadOperation(Concierge concierge) {
		super(concierge, "Loading combined items list");
	}

//----------------------------------------------------------------------------
//  Operation
//----------------------------------------------------------------------------	

	@Override
	protected List<Item> performOperation() {
		LOG.debug("Starting loading combined Items");
		return getConcierge().getService().getCombinedItems();
	}

	@Override
	protected void onSuccess(List<Item> combinedItems) {
		LOG.trace("Success on loading combined items");
		getConcierge().getMainFrame().setCombinedsList(combinedItems);
	}

	@Override
	protected void setBusyState(boolean isBusy) {
		getConcierge().getMainFrame().setBusyState(isBusy);
	}

}
