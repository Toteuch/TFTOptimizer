package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.domaine.Item;
import com.toteuch.tftoptimizer.domaine.PreferedItem;
import com.toteuch.tftoptimizer.ihm.util.ColorUtils;
import com.toteuch.tftoptimizer.ihm.util.ImageUtils;
import com.toteuch.tftoptimizer.ihm.util.TooltipUtils;

public class PreferedItemsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private final static int ITEM_ICON_S = 20;

	public final static String PREFIX = "CHAMP_SELECTED_PANEL_PREFERED_ITEMS_";

	public PreferedItemsPanel(Champion champ) {
		setLayout(new GridBagLayout());
		Color bg = champ.getQuality().getColor();
		setBackground(bg);
		setForeground(ColorUtils.getReadable(bg));
		setName(String.format("%s%s", PREFIX, champ.getName()));
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		int index = 0;
		for (PreferedItem pitem : champ.getPreferedItems()) {
			Item item = pitem.getItem();
			JLabel itemLabel = new JLabel();
			itemLabel.setIcon(ImageUtils.getScaledImageIcon(item.getImage(), ITEM_ICON_S));
			itemLabel.setBackground(bg);
			itemLabel.setToolTipText(TooltipUtils.getTooltipText(item));
			if (index % 2 == 0) {
				c.insets = new Insets(2, 3, 2, 3);
			} else {
				c.insets = new Insets(2, 2, 2, 2);
			}
			c.gridx = index;
			add(itemLabel, c);
			index++;
		}
	}

}
