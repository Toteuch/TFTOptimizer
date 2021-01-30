package com.toteuch.tftoptimizer.domaine;

import java.awt.Image;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item implements Comparable<Item> {
	private String name;
	private Image image;
	private String urlImage;
	private List<Item> combination;
	private String description;
	private List<String> rawEffects;

	@Override
	public String toString() {
		String ret = "Item : " + name + "\n" + "ImgURL : " + (image == null ? "null" : "found") + "\n" + "Combination : ";
		if (null != combination) {
			ret += combination.get(0).getName() + " + " + combination.get(1).getName();
		} else {
			ret += "non";
		}
		return ret;
	}

	@Override
	public int compareTo(Item otherItem) {
		return name.compareTo(otherItem.name);
	}
}
