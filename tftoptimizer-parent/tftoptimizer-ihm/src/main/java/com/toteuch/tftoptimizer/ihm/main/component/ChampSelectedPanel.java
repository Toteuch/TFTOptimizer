package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import com.toteuch.tftoptimizer.domaine.Champion;

public class ChampSelectedPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public final static String PREFIX = "CHAMP_SELECTED_PANEL_";

	public ChampSelectedPanel(Champion champ) {
		Color backgroundColor = champ.getQuality().getColor();
		setLayout(new GridBagLayout());
		setBackground(backgroundColor);
		setName(String.format("%s%s", PREFIX, champ.getName()));

		// Label du champion
		ChampLabel champLabel = new ChampLabel(champ, backgroundColor);
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		c.insets = new Insets(0, 0, 1, 0);
		add(champLabel, c);

		// Items préférés du champion
		PreferedItemsPanel pipanel = new PreferedItemsPanel(champ);
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 0);
		add(pipanel, c);
	}
}
