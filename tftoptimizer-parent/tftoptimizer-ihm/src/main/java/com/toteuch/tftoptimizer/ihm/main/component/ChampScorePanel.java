package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.toteuch.tftoptimizer.domaine.Champion;

public class ChampScorePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public final static String PREFIX = "CHAMP_SCORE_PANEL_";

	private Map<String, Object> directChilds = new HashMap<String, Object>();

	public ChampScorePanel(Champion champ) {
		Color backgroundColor = champ.getQuality().getColor();
		setLayout(new GridBagLayout());
		setBackground(backgroundColor);
		setName(String.format("%s%s", PREFIX, champ.getName()));

		// Label du champion
		ChampLabel champLabel = new ChampLabel(champ, backgroundColor);
		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		add(champLabel, c);
		directChilds.put(champLabel.getName(), champLabel);

		// Score du champion
		ScoreLabel scoreLabel = new ScoreLabel(champ.getName(), champ.getScore(), backgroundColor);
		GridBagConstraints cScoreLabel = new GridBagConstraints();
		cScoreLabel.gridy = 1;
		add(scoreLabel, cScoreLabel);
		directChilds.put(scoreLabel.getName(), scoreLabel);

		// Slamable Items
		SlamableItemsPanel slamablePanel = new SlamableItemsPanel(champ.getName(), champ.getSlamableItems(), backgroundColor);
		GridBagConstraints cSlamable = new GridBagConstraints();
		cSlamable.gridy = 2;
		add(slamablePanel, cSlamable);
		directChilds.put(slamablePanel.getName(), slamablePanel);

	}
}
