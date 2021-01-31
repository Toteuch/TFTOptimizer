package com.toteuch.tftoptimizer.ihm.util;

import java.awt.Color;

import com.toteuch.tftoptimizer.transverse.constante.Quality;

public class ColorUtils {
	public static Color getReadable(Color backgroundColor) {
		if (Quality.RARE.getColor() == backgroundColor || Quality.EPIQUE.getColor() == backgroundColor) {
			return Color.WHITE;
		} else {
			return Color.BLACK;
		}
	}
}
