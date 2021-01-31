package com.toteuch.tftoptimizer.ihm.util;

import org.apache.commons.lang3.StringUtils;

import com.toteuch.tftoptimizer.domaine.Item;

public class TooltipUtils {
	public static String getTooltipText(Object o) {
		StringBuffer sb = new StringBuffer();
		sb.append("<html><body><p>");
		if (o instanceof Item) {
			Item item = (Item) o;
			sb.append(String.format("<u>%s</u>", item.getName()));
			if (!StringUtils.isEmpty(item.getDescription())) {
				sb.append(String.format("<br>%s", item.getDescription()));
			}
			if (null != item.getRawEffects()) {
				for (String rawE : item.getRawEffects()) {
					sb.append(String.format("<br><i>%s</i>", rawE));
				}
			}
			if (null != item.getCombination()) {
				sb.append(String.format("<br>Combination : %s + %s", item.getCombination().get(0), item.getCombination().get(1)));
			}
		}
		sb.append("</p></body></html>");
		return sb.toString();
	}
}
