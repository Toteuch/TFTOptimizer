package com.toteuch.tftoptimizer.ihm.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.toteuch.tftoptimizer.ihm.main.Concierge;

public class Reset extends AbstractAction {
	private static final long serialVersionUID = 1L;

	private Logger LOG = LogManager.getLogger(getClass());

//----------------------------------------------------------------------------
//  Instance Variables and Constructor
//----------------------------------------------------------------------------

	private Concierge concierge;

	public Reset(Concierge concierge) {
		super("Reset");
		this.concierge = concierge;
	}

//----------------------------------------------------------------------------
//  ActionListener
//----------------------------------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e) {
		LOG.info("invoked");
		concierge.getMainFrame().reset();
	}
}
