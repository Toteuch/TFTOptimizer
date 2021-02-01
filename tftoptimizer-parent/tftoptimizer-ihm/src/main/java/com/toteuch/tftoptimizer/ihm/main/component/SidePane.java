package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.toteuch.tftoptimizer.ihm.main.ActionRegistry;

public class SidePane extends JPanel {
	private static final long serialVersionUID = 1L;

	public final static String NAME = "SIDE_PANE";
	public final static int DEFAULT_WIDTH = 210;

	public SidePane(ActionRegistry actionRegistry, int preferedHeight, JFrame mainFrame, boolean defaultPinState) {
		setLayout(new GridBagLayout());
		setName(NAME);
		setPreferredSize(new Dimension(DEFAULT_WIDTH, preferedHeight));

		setBackground(Color.YELLOW);

		MenuPanel menuPanel = new MenuPanel(actionRegistry, mainFrame, defaultPinState);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.NORTH;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 10;
		add(menuPanel, c);

		PlaceholderPanel placeholderPanel = new PlaceholderPanel();
		placeholderPanel.setBackground(Color.BLACK);
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 1;
		c.weightx = 3;
		c.weighty = 10;
		add(placeholderPanel, c);
	}
}
