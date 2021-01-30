package com.toteuch.tftoptimizer.transverse.constante;

import java.awt.Color;

public enum Quality {
	COMMUN(1, new Color(128, 128, 128)), // GRIS
	INHABITUEL(2, new Color(0, 255, 0)), // VERT
	RARE(3, new Color(0, 0, 255)), // BLEU
	EPIQUE(4, new Color(127, 0, 255)), // VIOLET
	LEGENDAIRE(5, new Color(204, 204, 0)); // DORE

	private int cost;
	private Color color;

	private Quality(int cost, Color color) {
		this.cost = cost;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public int getCost() {
		return cost;
	}
}
