package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Insets;

import javax.swing.JFrame;

import com.toteuch.tftoptimizer.ihm.component.MotionButton;
import com.toteuch.tftoptimizer.ihm.util.ImageUtils;

public class MoveButton extends MotionButton {
	private static final long serialVersionUID = 1L;
	private static final String FP_ICON = "icon/move.png";

	public static final String NAME = "MOVE_BUTTON";

	public MoveButton(JFrame parent, int iconSize) {
		super(parent);
		setName(NAME);
		setIcon(ImageUtils.getScaledImageIconFromClassLoader(FP_ICON, iconSize));
		setMargin(new Insets(0, 0, 0, 0));
	}

}
