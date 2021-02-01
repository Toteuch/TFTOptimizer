package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.Insets;

import javax.swing.JTextField;

public class FilterField extends JTextField {
	private static final long serialVersionUID = 1L;

	public final static String NAME = "FILTER_FIELD";

	public FilterField() {
		setName(NAME);
		setMargin(new Insets(0, 0, 0, 0));
	}
}
