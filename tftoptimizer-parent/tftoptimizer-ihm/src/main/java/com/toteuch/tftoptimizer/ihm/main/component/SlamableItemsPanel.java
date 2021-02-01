package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toteuch.tftoptimizer.domaine.Item;
import com.toteuch.tftoptimizer.ihm.layout.WrapLayout;
import com.toteuch.tftoptimizer.ihm.util.ColorUtils;
import com.toteuch.tftoptimizer.ihm.util.ImageUtils;
import com.toteuch.tftoptimizer.ihm.util.TooltipUtils;

public class SlamableItemsPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public static final String PREFIX = "SLAMABLE_ITEM_PANEL_";

	private final static int ITEM_ICON_S = 20;
	private final static String FP_PLACEHOLDER_ITEM = "icon/placeholder.png";

	public SlamableItemsPanel(String champName, List<Item> slamables, Color bg) {
		setName(String.format("%s%s", PREFIX, champName));
		setLayout(new WrapLayout(WrapLayout.LEFT));
		setBackground(bg);
		setForeground(ColorUtils.getReadable(bg));
		if (null == slamables || slamables.isEmpty()) {
			JLabel itemLabel = new JLabel();
			itemLabel.setIcon(ImageUtils.getScaledImageIconFromClassLoader(FP_PLACEHOLDER_ITEM, ITEM_ICON_S));
			itemLabel.setBackground(new Color(0, 0, 0, 0)); // Transparent
			add(itemLabel);
		} else {
			for (Item item : slamables) {
				JLabel itemLabel = new JLabel();
				itemLabel.setIcon(ImageUtils.getScaledImageIcon(item.getImage(), ITEM_ICON_S));
				itemLabel.setBackground(bg);
				itemLabel.setToolTipText(TooltipUtils.getTooltipText(item));
				add(itemLabel);
			}
		}

	}
}
