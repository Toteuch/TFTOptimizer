package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.ihm.main.ActionRegistry;
import com.toteuch.tftoptimizer.ihm.util.ColorUtils;
import com.toteuch.tftoptimizer.ihm.util.ImageUtils;

public class ChampLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	public final static String PREFIX = "CHAMP_LABEL_";

	private final static int CHAMP_ICON_TEXT_FONT_SIZE = 10;
	private final static int CHAMP_ICON_S = 55;

	public ChampLabel(Champion champ, Color bg) {
		build(champ, bg);
	}

	public ChampLabel(Champion champ, Color bg, ActionRegistry actionRegistry) {
		addMouseListener(actionRegistry.addToSelectedChampion);
		build(champ, bg);
	}

	public void build(Champion champ, Color bg) {
		String name = champ.getName();
		setName(String.format("%s%s", PREFIX, name));
		String text = name;
		if (text.length() > 9) {
			text = text.substring(0, 9);
		}
		setText(text);
		setFont(new Font("Serial", Font.BOLD, CHAMP_ICON_TEXT_FONT_SIZE));
		setIcon(ImageUtils.getScaledImageIcon(champ.getImage(), CHAMP_ICON_S));
		setVerticalTextPosition(JLabel.TOP);
		setHorizontalTextPosition(JLabel.CENTER);
		setIconTextGap(0);
		setBackground(bg);
		setForeground(ColorUtils.getReadable(bg));
		setToolTipText(champ.getName());
	}

}
