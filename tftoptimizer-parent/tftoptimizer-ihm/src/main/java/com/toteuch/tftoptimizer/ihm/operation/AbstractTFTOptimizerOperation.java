package com.toteuch.tftoptimizer.ihm.operation;

import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.kdgregory.swinglib.AsynchronousOperation;
import com.kdgregory.swinglib.components.ProgressMonitor;
import com.toteuch.tftoptimizer.ihm.main.Concierge;

/**
 * Holds common code for all TFTOptimizer operations.
 */
public abstract class AbstractTFTOptimizerOperation<T> extends AsynchronousOperation<T> {
	protected Logger LOG = LogManager.getLogger(getClass());

	private Concierge concierge;
	private String description;

	private ProgressMonitor _progressMonitor;

	protected AbstractTFTOptimizerOperation(Concierge concierge, String description) {
		this.concierge = concierge;
		this.description = description;
	}

//----------------------------------------------------------------------------
//  Workflow Methods
//----------------------------------------------------------------------------

	/**
	 * Initiates a wait cursor and then puts this operation on the background
	 * thread. Subclasses may override to provide addition initialization, but
	 * should always delegate to this implementation.
	 */
	public void start() {
		setBusyState(true);
		concierge.execute(this);
	}

	/**
	 * Clears the wait cursor / progress monitor.
	 */
	@Override
	protected void onComplete() {
		setBusyState(false);
	}

	/**
	 * Common exception handler. Turns off wait cursor, logs exception, and notifies
	 * user.
	 * <p>
	 * Note that this is marked as <code>final</code>, to prevent subclasses from
	 * overriding.
	 */
	@Override
	protected void onFailure(Throwable ex) {
		LOG.error("request failed", ex);
		setBusyState(false);
		JOptionPane.showMessageDialog(concierge.getDialogOwner(), "Unable to process this request: " + ex.getMessage() + "\nSee log for more information", "Unable to Execute Operation",
				JOptionPane.ERROR_MESSAGE);
	}

//----------------------------------------------------------------------------
//  Support methods for subclasses
//----------------------------------------------------------------------------

	protected Concierge getConcierge() {
		return concierge;
	}

	/**
	 * Called during operation to update the progress monitor status message.
	 */
	protected void updateProgressMonitor(String message) {
		_progressMonitor.setStatus(message);
	}

//----------------------------------------------------------------------------
//  Internals
//----------------------------------------------------------------------------

	/**
	 * Controls the various "wait indicators" -- pass <code>true</code> at the start
	 * of an operation, <code>false</code> at the end.
	 */
	protected void setBusyState(boolean isBusy) {
		concierge.getMainFrame().setBusyState(isBusy);
		concierge.getMainFrame().activateGlassPane(isBusy);
//    	if(isBusy) {
//    	_progressMonitor = new ProgressMonitor(
//                concierge.getDialogOwner(),
//                "TFTOptimizer Operation in Progress",
//                description
//                //ProgressMonitor.Options.MODAL,
//                //ProgressMonitor.Options.CENTER,
//                //,ProgressMonitor.Options.SHOW_STATUS
//                );    		
//    	_progressMonitor.show();
//    	} else if(_progressMonitor != null) {
//    		_progressMonitor.dispose();
//    		_progressMonitor = null;
//    	}
	}
}
