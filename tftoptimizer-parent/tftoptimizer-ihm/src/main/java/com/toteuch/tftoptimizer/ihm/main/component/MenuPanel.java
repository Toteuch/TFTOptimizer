package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.toteuch.tftoptimizer.ihm.main.ActionRegistry;

public class MenuPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_ICON_SIZE = 15;

	public final static String NAME = "MENU_PANEL";

	public MenuPanel(ActionRegistry actionRegistry, JFrame mainFrame, boolean defaultPinState) {
		setLayout(new GridBagLayout());
		setName(NAME);

		setBackground(Color.PINK);

		FilterField FilterField = new FilterField();
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(1, 1, 1, 1);
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 10;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(FilterField, c);

		PinCheckBox pinCheckBox = new PinCheckBox(actionRegistry, DEFAULT_ICON_SIZE, defaultPinState);
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(1, 1, 1, 1);
		c.gridx = 1;
		c.weightx = 0;
		c.weighty = 0;
		add(pinCheckBox, c);

		ResetButton resetButton = new ResetButton(actionRegistry, DEFAULT_ICON_SIZE);
		c.gridx = 2;
		add(resetButton, c);

		MoveButton moveButton = new MoveButton(mainFrame, DEFAULT_ICON_SIZE);
		c.gridx = 3;
		add(moveButton, c);

		ExitButton exitButton = new ExitButton(actionRegistry, DEFAULT_ICON_SIZE);
		c.gridx = 4;
		add(exitButton, c);
	}

}
