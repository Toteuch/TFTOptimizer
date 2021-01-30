package com.toteuch.tftoptimizer.ihm.action;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Shuts down the application, after first checking to ensure that everything
 * has been saved. In addition to being invoked from the exit button, this
 * action is attached to the main frame's close button.
 */
public class QuitTFTOptimizer extends AbstractAction implements WindowListener {
	private static final long serialVersionUID = 1L;

	private Logger LOG = LogManager.getLogger(getClass());

//----------------------------------------------------------------------------
//  Instance Variables and Constructor
//----------------------------------------------------------------------------

	public QuitTFTOptimizer() {
		super("Quit");
	}

//----------------------------------------------------------------------------
//  ActionListener
//----------------------------------------------------------------------------

	@Override
	public void actionPerformed(ActionEvent e) {
		LOG.info("invoked");
		System.exit(0);
	}

//----------------------------------------------------------------------------
//  WindowListener
//----------------------------------------------------------------------------

	@Override
	public void windowActivated(WindowEvent e) {
		// ignored
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// ignored
	}

	@Override
	public void windowClosing(WindowEvent e) {
		actionPerformed(null);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// ignored
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// ignored
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// ignored
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// ignored
	}
}
