package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Insets;

import javax.swing.JCheckBox;

import com.toteuch.tftoptimizer.ihm.main.ActionRegistry;
import com.toteuch.tftoptimizer.ihm.util.ImageUtils;

public class SelectedChampsCheckBox extends JCheckBox {
	private static final long serialVersionUID = 1L;
	private static final String FP_ICON = "icon/selected_champs.png";

	public final static String NAME = "SELECTED_CHAMPS_CHECK_BOX";

	public SelectedChampsCheckBox(ActionRegistry actionRegistry, int iconSize) {
		setName(NAME);
		addActionListener(actionRegistry.switchMainPanelView);
		setMargin(new Insets(1, 1, 1, 1));
		setIcon(ImageUtils.BlackAndWhiteScaledImageIconFromClassLoader(FP_ICON, iconSize));
		setSelectedIcon(ImageUtils.getScaledImageIconFromClassLoader(FP_ICON, iconSize));
	}
}
