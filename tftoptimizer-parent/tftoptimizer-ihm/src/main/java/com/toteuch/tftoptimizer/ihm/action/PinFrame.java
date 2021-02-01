package com.toteuch.tftoptimizer.ihm.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.toteuch.tftoptimizer.ihm.main.Concierge;

public class PinFrame extends AbstractAction {
	private static final long serialVersionUID = 1L;

	private Logger LOG = LogManager.getLogger(getClass());

//----------------------------------------------------------------------------
//  Instance Variables and Constructor
//----------------------------------------------------------------------------

	private Concierge concierge;

	public PinFrame(Concierge concierge) {
		super("Pin");
		this.concierge = concierge;
	}

//----------------------------------------------------------------------------
//  ActionListener
//----------------------------------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e) {
		LOG.info("invoked");
		concierge.getMainFrame().changePinState();
	}
}
