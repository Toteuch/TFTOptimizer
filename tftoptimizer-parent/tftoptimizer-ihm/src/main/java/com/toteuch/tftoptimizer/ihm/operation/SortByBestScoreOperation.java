package com.toteuch.tftoptimizer.ihm.operation;

import java.util.List;
import java.util.Map;

import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.domaine.Item;
import com.toteuch.tftoptimizer.ihm.main.Concierge;

public class SortByBestScoreOperation extends AbstractTFTOptimizerOperation<List<Champion>> {

	public SortByBestScoreOperation(Concierge concierge) {
		super(concierge, "Sorting champions list by highest score from input");
	}

//----------------------------------------------------------------------------
//  Operation
//----------------------------------------------------------------------------	

	@Override
	protected List<Champion> performOperation() {
		LOG.debug("Starting sorting champions list by highest score from input");
		List<Champion> champs = getConcierge().getMainFrame().getChamps();
		Map<Item, Integer> input = getConcierge().getMainFrame().getComponents();
		return getConcierge().getService().getChampionFromInput(champs, input);
	}

	@Override
	protected void onSuccess(List<Champion> champs) {
		LOG.trace("Success on sorting champions list by highest score from input");
		getConcierge().getMainFrame().setChampsList(champs);
	}

	@Override
	protected void setBusyState(boolean isBusy) {
		getConcierge().getMainFrame().setBusyState(isBusy);
	}
}
