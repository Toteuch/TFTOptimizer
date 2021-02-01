package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Insets;

import javax.swing.JCheckBox;

import com.toteuch.tftoptimizer.ihm.main.ActionRegistry;
import com.toteuch.tftoptimizer.ihm.util.ImageUtils;

public class PinCheckBox extends JCheckBox {
	private static final long serialVersionUID = 1L;
	private static final String FP_ICON = "icon/pin.png";

	public final static String NAME = "PIN_CHECK_BOX";

	public PinCheckBox(ActionRegistry actionRegistry, int iconSize, boolean defaultValue) {
		setName(NAME);
		addActionListener(actionRegistry.pinFrame);
		setMargin(new Insets(1, 1, 1, 1));
		setSelectedIcon(ImageUtils.getScaledImageIconFromClassLoader(FP_ICON, iconSize));
		setIcon(ImageUtils.BlackAndWhiteScaledImageIconFromClassLoader(FP_ICON, iconSize));
		setSelected(defaultValue);
	}
}
