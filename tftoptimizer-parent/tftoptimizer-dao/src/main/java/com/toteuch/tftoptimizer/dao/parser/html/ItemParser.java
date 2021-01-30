package com.toteuch.tftoptimizer.dao.parser.html;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.toteuch.tftoptimizer.dao.parser.HTMLParser;
import com.toteuch.tftoptimizer.dao.utils.FileUtils;
import com.toteuch.tftoptimizer.dao.utils.PropertiesUtils;
import com.toteuch.tftoptimizer.domaine.Item;

public class ItemParser extends HTMLParser {
	private static final String URL = "https://lolchess.gg/items";
	private static final String IMG_SUB = "img\\";
	private static final String ITEM_SUB = "item\\";
	private static final Logger LOG = LogManager.getLogger(ItemParser.class);

	private static List<Item> combineds;
	private static List<Item> components;
	private static Document doc;

	private ItemParser() {
	}

	public static List<Item> getCombinedItems() {
		if (combineds == null || combineds.isEmpty()) {
			if (doc == null) {
				parseItems();
			}
			if (components == null) {
				getComponentItems(doc);
			}
			getCombinedItems(doc, components);
		}

		return combineds;

	}

	public static List<Item> getComponentItems() {
		if (components == null || components.isEmpty()) {
			if (doc == null) {
				parseItems();
			}
			getComponentItems(doc);
		}
		return components;
	}

	public static void emptyCache() {
		FileUtils.empty(String.format("%s%s%s", PropertiesUtils.getRootFolder(), ITEM_SUB, IMG_SUB));
	}

	private static void parseItems() {
		combineds = new ArrayList<Item>();
		components = new ArrayList<Item>();
		LOG.debug(String.format("Parsing items on %s", URL));
		String output = getUrlContents(URL);
		LOG.trace(output);
		doc = Jsoup.parse(output);
	}

	private static void getCombinedItems(Document page, List<Item> compos) {
		LOG.debug(String.format("Getting combined items from parse"));
		int startIndex = 0;
		for (int x = 0; x < compos.size(); x++) {
			Item lineCompo = compos.get(x);
			for (int y = startIndex; y < compos.size(); y++) {
				Item rowCompo = compos.get(y);
				Item combined = new Item();
				List<Item> combination = new ArrayList<Item>();
				combination.add(rowCompo);
				combination.add(lineCompo);
				combined.setCombination(combination);
				Element divItem = page.getElementsByAttributeValue("id", "new-item-table").first().child(x + 1).child(y + 1);
				String urlTooltip = divItem.attr("data-tooltip-url");
				LOG.trace("Getting item's tooltip content...");
				String outputTooltip = getUrlContents(urlTooltip);
				LOG.trace("End of getting item's tooltip content");
				Document tooltip = Jsoup.parse(outputTooltip);
				Element divTooltip = tooltip.getAllElements().first();
				combined.setName(divTooltip.getAllElements().get(5).text());
				combined.setDescription(divTooltip.getAllElements().get(6).text());
				List<String> rawEffects = new ArrayList<String>();

				// FoN doesn't have any raw effects
				if (divTooltip.getAllElements().get(7).hasText()) {
					for (Element rawEffect : divTooltip.getAllElements().get(7).child(0).getAllElements()) {
						rawEffects.add(rawEffect.text());
					}
				}
				combined.setRawEffects(rawEffects);
				combined.setUrlImage(divItem.getAllElements().first().child(0).attr("src"));
				combined.setImage(getImage(combined.getUrlImage(), String.format("%s%s%s", PropertiesUtils.getRootFolder(), ITEM_SUB, IMG_SUB), combined.getName()));
				combineds.add(combined);
			}
			startIndex++;
		}
		LOG.trace(String.format("End of getting combined items from parse"));
	}

	private static void getComponentItems(Document page) {
		LOG.debug(String.format("Getting components items from parse"));
		Elements divCompos = page.getElementsByAttributeValue("id", "new-item-table").first().child(0).getElementsByClass("guide-items__combine-table__item");
		for (Element divCompo : divCompos) {
			Item item = new Item();
			item.setCombination(null);
			Element imgTag = divCompo.getElementsByTag("img").first();
			item.setUrlImage(imgTag.attr("src"));
			item.setName(imgTag.attr("alt"));
			item.setImage(getImage(item.getUrlImage(), String.format("%s%s%s", PropertiesUtils.getRootFolder(), ITEM_SUB, IMG_SUB), item.getName()));
			List<String> rawEffects = new ArrayList<String>();
			rawEffects.add(divCompo.getElementsByTag("div").first().text());
			item.setRawEffects(rawEffects);
			components.add(item);
		}
		LOG.trace(String.format("End of getting components items from parse"));
	}
}
