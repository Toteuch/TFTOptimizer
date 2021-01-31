package com.toteuch.tftoptimizer.ihm.operation;

import java.util.List;

import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.ihm.main.Concierge;

public class ChampionsLoadOperation extends AbstractTFTOptimizerOperation<List<Champion>> {

	public ChampionsLoadOperation(Concierge concierge) {
		super(concierge, "Loading champions list");
	}

//----------------------------------------------------------------------------
//  Operation
//----------------------------------------------------------------------------	

	@Override
	protected List<Champion> performOperation() {
		LOG.debug("Starting loading champs");
		return getConcierge().getService().getChampions();
	}

	@Override
	protected void onSuccess(List<Champion> champs) {
		LOG.trace("Success on loading champs list");
		getConcierge().getMainFrame().setChampsList(champs);
	}
}
