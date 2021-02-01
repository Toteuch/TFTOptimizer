package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Insets;

import javax.swing.JButton;

import com.toteuch.tftoptimizer.ihm.main.ActionRegistry;
import com.toteuch.tftoptimizer.ihm.util.ImageUtils;

public class ResetButton extends JButton {
	private static final long serialVersionUID = 1L;
	private static final String FP_ICON = "icon/reset.png";

	public static final String NAME = "RESET_BUTTON";

	public ResetButton(ActionRegistry actionRegistry, int iconSize) {
		addActionListener(actionRegistry.reset);
		setIcon(ImageUtils.getScaledImageIconFromClassLoader(FP_ICON, iconSize));
		setMargin(new Insets(0, 0, 0, 0));
	}
}
