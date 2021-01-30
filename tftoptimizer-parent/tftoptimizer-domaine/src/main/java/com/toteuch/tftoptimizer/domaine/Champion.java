package com.toteuch.tftoptimizer.domaine;

import java.awt.Image;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.toteuch.tftoptimizer.transverse.constante.Quality;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Champion {
	private String name;
	private String detailUrl;
	private Quality quality;
	private Image image;
	private String urlImage;
	private List<PreferedItem> preferedItems;

	@Override
	public boolean equals(Object o) {
		if (o instanceof Champion) {
			Champion b = (Champion) o;
			if (this == b) {
				return true;
			}
			if (StringUtils.equals(getName(), b.getName())) {
				return true;
			}
		}
		return false;
	}
}
