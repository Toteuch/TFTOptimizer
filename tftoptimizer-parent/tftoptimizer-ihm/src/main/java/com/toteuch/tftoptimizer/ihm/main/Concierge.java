package com.toteuch.tftoptimizer.ihm.main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

import com.kdgregory.swinglib.AsynchronousOperation;
import com.kdgregory.swinglib.CursorManager;
import com.toteuch.tftoptimizer.service.ITFTOptimizerService;
import com.toteuch.tftoptimizer.service.ServiceFactory;

/**
 * Provides access to common services. Some of these are read-only after
 * startup, some may be configured during operation.
 * <p>
 * A single instance of the Concierge is created at application startup, and
 * initialized with dependent objects. This single instance will be passed as a
 * constructor parameter to everything that needs it.
 */
public class Concierge {
	private ConfigBean config;

	private ExecutorService threadPool;
	private CursorManager cursorManager;
	private JFrame dialogOwner;
	private MainFrameController mainFrame;

	private ITFTOptimizerService service;

	public Concierge(ConfigBean config) {
		this.config = config;
	}

// ----------------------------------------------------------------------------
//  Public Accessor Methods -- these may be called from anywhere, at any time
//---------------------------------------------------------------------------- 

	/**
	 * Adds an operation to the shared background thread pool. This pool is created
	 * at instantiation, and cannot be changed.
	 */
	public void execute(AsynchronousOperation<?> op) {
		if (threadPool == null)
			threadPool = Executors.newFixedThreadPool(1);
		threadPool.execute(op);
	}

	/**
	 * Returns the configuration bean.
	 */
	public ConfigBean getConfig() {
		return config;
	}

	/**
	 * Returns the "main frame" controller, for update from actions.
	 */
	public MainFrameController getMainFrame() {
		return mainFrame;
	}

	/**
	 * Returns the "owner frame" for all dialogs created by the app (nominally the
	 * main frame).
	 */
	public JFrame getDialogOwner() {
		return dialogOwner;
	}

	/**
	 * Returns the cursor manager, allowing operations/actions to set a temporary
	 * cursor on any component in the application.
	 */
	public CursorManager getCursorManager() {
		if (cursorManager == null)
			cursorManager = new CursorManager();
		return cursorManager;
	}

	/**
	 * Returns the singleton TFTOptimizer's service.
	 */
	public ITFTOptimizerService getService() {
		if (service == null) {
			service = ServiceFactory.getITFTOptimizerService();
		}
		return service;
	}

// ----------------------------------------------------------------------------
//  Methods called during initialization; these are all protected, on the
//  assumption that all initialization takes place in the "main" package
//----------------------------------------------------------------------------

	protected void setMainFrame(MainFrameController controller, JFrame frame) {
		mainFrame = controller;
		dialogOwner = frame;
	}
}
