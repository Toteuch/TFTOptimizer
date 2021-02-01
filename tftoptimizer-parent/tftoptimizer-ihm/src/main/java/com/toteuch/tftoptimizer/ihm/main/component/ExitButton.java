package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Insets;

import javax.swing.JButton;

import com.toteuch.tftoptimizer.ihm.main.ActionRegistry;
import com.toteuch.tftoptimizer.ihm.util.ImageUtils;

public class ExitButton extends JButton {
	private static final long serialVersionUID = 1L;
	private static final String FP_ICON = "icon/exit.png";

	public static final String NAME = "RESET_BUTTON";

	public ExitButton(ActionRegistry actionRegistry, int iconSize) {
		setIcon(ImageUtils.getScaledImageIconFromClassLoader(FP_ICON, iconSize));
		addActionListener(actionRegistry.quitTFTOptimizer);
		setMargin(new Insets(0, 0, 0, 0));
	}
}
