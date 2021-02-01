package com.toteuch.tftoptimizer.ihm.util;

import java.awt.Component;
import java.awt.Container;

import org.apache.commons.lang3.StringUtils;

public class ComponentsUtils {
	public static Component getRecursivelyFirstChildByName(Container parent, String childName) {
		Component ret = null;
		for (Component c : parent.getComponents()) {
			if (StringUtils.equals(childName, c.getName())) {
				return c;
			}
			if (c instanceof Container) {
				ret = getRecursivelyFirstChildByName((Container) c, childName);
				if (ret != null) {
					return ret;
				}
			}
		}
		return ret;
	}
}
