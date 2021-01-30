package com.toteuch.tftoptimizer.ihm;

import javax.swing.SwingUtilities;

import com.toteuch.tftoptimizer.ihm.main.Concierge;
import com.toteuch.tftoptimizer.ihm.main.ConfigBean;
import com.toteuch.tftoptimizer.ihm.main.MainFrameController;
import com.toteuch.tftoptimizer.ihm.operation.ComponentItemsLoadOperation;

public class Main {
	public static void main(String[] args) throws Exception {
		final Concierge concierge = new Concierge(new ConfigBean());
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				new MainFrameController(concierge).buildAndShow();
				new ComponentItemsLoadOperation(concierge).start();
			}
		});
	}
}
