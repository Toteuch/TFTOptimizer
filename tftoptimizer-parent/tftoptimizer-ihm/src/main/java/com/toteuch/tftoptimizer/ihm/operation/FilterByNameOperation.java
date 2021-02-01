package com.toteuch.tftoptimizer.ihm.operation;

import java.util.List;

import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.ihm.main.Concierge;

public class FilterByNameOperation extends AbstractTFTOptimizerOperation<List<Champion>> {

	public FilterByNameOperation(Concierge concierge) {
		super(concierge, "Filter champs by name");
	}

//----------------------------------------------------------------------------
//  Operation
//----------------------------------------------------------------------------

	@Override
	protected List<Champion> performOperation() throws Exception {
		LOG.debug("Filter champ list by name...");
		String filter = getConcierge().getMainFrame().getFilter();
		List<Champion> champs = getConcierge().getMainFrame().getChamps();
		return getConcierge().getService().filterByName(champs, filter);
	}

	@Override
	protected void onSuccess(List<Champion> champs) {
		LOG.trace("Success on filtering champ list by name");
		getConcierge().getMainFrame().setFilteredChampsList(champs);
	}

	@Override
	protected void setBusyState(boolean isBusy) {
		getConcierge().getMainFrame().setBusyState(isBusy);
	}
}
