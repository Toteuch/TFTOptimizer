package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.ihm.main.ActionRegistry;

public class ChampScorePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public final static String PREFIX = "CHAMP_SCORE_PANEL_";

	public ChampScorePanel(Champion champ, ActionRegistry actionRegistry) {
		Color backgroundColor = champ.getQuality().getColor();
		setLayout(new GridBagLayout());
		setBackground(backgroundColor);
		setName(String.format("%s%s", PREFIX, champ.getName()));

		// Label du champion
		ChampLabel champLabel = new ChampLabel(champ, backgroundColor, actionRegistry);
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		add(champLabel, c);

		// Score du champion
		ScoreLabel scoreLabel = new ScoreLabel(champ.getName(), champ.getScore(), backgroundColor);
		GridBagConstraints cScoreLabel = new GridBagConstraints();
		cScoreLabel.gridy = 1;
		add(scoreLabel, cScoreLabel);

		// Slamable Items
		SlamableItemsPanel slamablePanel = new SlamableItemsPanel(champ.getName(), champ.getSlamableItems(), backgroundColor);
		GridBagConstraints cSlamable = new GridBagConstraints();
		cSlamable.gridy = 2;
		add(slamablePanel, cSlamable);
	}
}
