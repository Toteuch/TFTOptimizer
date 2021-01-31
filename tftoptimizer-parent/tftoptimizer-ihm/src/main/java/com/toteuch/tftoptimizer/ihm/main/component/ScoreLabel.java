package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Color;
import java.text.DecimalFormat;

import javax.swing.JLabel;

import com.toteuch.tftoptimizer.ihm.util.ColorUtils;

public class ScoreLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	public final static String PREFIX = "SCORE_LABEL_";

	public ScoreLabel(String champName, int score, Color bg) {
		setName(String.format("%s%s", PREFIX, champName));
		DecimalFormat df = new DecimalFormat("#");
		setText(df.format(score));
		setBackground(bg);
		setForeground(ColorUtils.getReadable(bg));
	}
}
