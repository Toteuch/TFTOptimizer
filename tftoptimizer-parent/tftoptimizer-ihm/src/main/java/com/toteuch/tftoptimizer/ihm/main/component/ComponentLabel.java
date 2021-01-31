package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import com.toteuch.tftoptimizer.domaine.Item;
import com.toteuch.tftoptimizer.ihm.util.ImageUtils;
import com.toteuch.tftoptimizer.ihm.util.TooltipUtils;

public class ComponentLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	public final static String PREFIX = "PREFIX_COMPONENT_LABEL_";

	private final static int ITEM_ICON_SIZE = 28;

	public ComponentLabel(Item item, Integer nb) {
		String name = item.getName();
		setName(String.format("%s%s", PREFIX, name));
		setText(nb.toString());
		setForeground(Color.RED);
		setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
		setIcon(ImageUtils.getScaledImageIcon(item.getImage(), ITEM_ICON_SIZE));
		setVerticalTextPosition(JLabel.CENTER);
		setHorizontalTextPosition(JLabel.CENTER);

		setToolTipText(TooltipUtils.getTooltipText(item));
	}
}
