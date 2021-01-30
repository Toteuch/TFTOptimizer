package com.toteuch.tftoptimizer.domaine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreferedItem {
	private Item item;
	private double presence;

	@Override
	public boolean equals(Object o) {
		if (o instanceof PreferedItem) {
			PreferedItem b = (PreferedItem) o;
			if (b == this) {
				return true;
			}
			if (getItem() == b.getItem() && Double.compare(getPresence(), b.getPresence()) == 0) {
				return true;
			}
		}
		return false;
	}
}
