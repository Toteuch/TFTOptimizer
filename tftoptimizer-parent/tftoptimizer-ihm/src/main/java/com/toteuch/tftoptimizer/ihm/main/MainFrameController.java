package com.toteuch.tftoptimizer.ihm.main;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kdgregory.swinglib.SwingUtil;
import com.kdgregory.swinglib.components.MainFrame;
import com.toteuch.tftoptimizer.domaine.Item;

public class MainFrameController {
	private final static String BASE_TITLE = "TFTOptimizer";

	private Concierge concierge;
	private ActionRegistry actionRegistry;

	private JFrame mainFrame;
	private JPanel contentPane;
	private List<Item> components;
	private List<Item> combineds;

	public MainFrameController(Concierge concierge) {
		this.concierge = concierge;
		actionRegistry = new ActionRegistry(concierge);
	}

//----------------------------------------------------------------------------
//  Initialization
//----------------------------------------------------------------------------

	public void buildAndShow() {
		mainFrame = new MainFrame(BASE_TITLE);
		mainFrame.setContentPane(createContentPane());
		JPanel glassPane = new JPanel();
		glassPane.add(new JLabel("Loading"));
		mainFrame.setGlassPane(glassPane);
		mainFrame.pack();
		concierge.setMainFrame(this, mainFrame);

		SwingUtil.centerAndShow(mainFrame);
	}

	private Container createContentPane() {
		contentPane = new JPanel();
		contentPane.setName("CONTENT_PANE");
		contentPane.setLayout(new FlowLayout());
		contentPane.setPreferredSize(new Dimension(600, 400));

		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(actionRegistry.quitTFTOptimizer);
		contentPane.add(exitButton);

		return contentPane;
	}

//----------------------------------------------------------------------------
//  Operational Methods
//----------------------------------------------------------------------------	

	public void setBusyState(boolean isBusy) {
		if (isBusy) {
			concierge.getCursorManager().pushBusyCursor(mainFrame.getRootPane());
		} else {
			concierge.getCursorManager().popCursor(mainFrame.getRootPane());
		}
	}

	public void activateGlassPane(boolean isBusy) {
		mainFrame.getGlassPane().setVisible(isBusy);
	}

	public void setComponentsList(List<Item> componentItems) {
		components = componentItems;
		displayComponents();
	}

	public void setCombinedsList(List<Item> combinedItems) {
		combineds = combinedItems;
		displayCombineds();
	}

//----------------------------------------------------------------------------
//  Internals
//----------------------------------------------------------------------------	

	private void displayComponents() {
		for (Item component : components) {
			JLabel compoLabel = new JLabel(component.getName());
			contentPane.add(compoLabel);
		}
		refreshView(contentPane);
	}

	private void displayCombineds() {
		for (Item combined : combineds) {
			JLabel combinedLabel = new JLabel(combined.getName());
			contentPane.add(combinedLabel);
		}
		refreshView(contentPane);
	}

	private void refreshView(Component c) {
		c.revalidate();
		c.repaint();
	}
}
