package com.toteuch.tftoptimizer.dao.parser.html;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.toteuch.tftoptimizer.dao.parser.HTMLParser;
import com.toteuch.tftoptimizer.dao.utils.PropertiesUtils;
import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.domaine.Item;
import com.toteuch.tftoptimizer.domaine.PreferedItem;
import com.toteuch.tftoptimizer.transverse.constante.Quality;

public class ChampParser extends HTMLParser {

	private static final String URL = "https://lolchess.gg/statistics/items";
	private static final String IMG_SUB = "img\\";
	private static final String CHAMP_SUB = "champ\\";

	private static final Logger LOG = LogManager.getLogger(ChampParser.class);

	public static List<Champion> getChampions(Map<String, Item> combineds) {
		String output = getUrlContents(URL);
		Document doc = Jsoup.parse(output);
		return getChampions(doc, combineds);
	}

	private static List<Champion> getChampions(Document doc, Map<String, Item> combineds) {
		LOG.debug("Start parsing champs");
		List<Champion> champs = new ArrayList<Champion>();
		Elements tdChampionElements = doc.getElementsByClass("champion");
		for (Element tdChampion : tdChampionElements) {
			Champion champ = new Champion();
			champ.setName(tdChampion.getElementsByTag("span").get(0).ownText());
			champ.setDetailUrl(tdChampion.getElementsByTag("a").get(0).attr("href"));
			champ.setUrlImage(tdChampion.getElementsByTag("img").get(0).attr("src"));
			champ.setImage(getImage(champ.getUrlImage(), String.format("%s%s%s", PropertiesUtils.getRootFolder(), CHAMP_SUB, IMG_SUB), champ.getName()));
			String classQuality = null;
			Set<String> classes = tdChampion.getElementsByTag("div").get(0).classNames();
			for (String clas : classes) {
				if (StringUtils.startsWith(clas, "cost-")) {
					classQuality = clas;
					break;
				}
			}
			int cost = Integer.parseInt(StringUtils.substringAfter(classQuality, "cost-"));
			for (Quality q : Quality.values()) {
				if (q.getCost() == cost) {
					champ.setQuality(q);
					break;
				}
			}

			Elements tdsItem = tdChampion.parent().getElementsByClass("items");
			List<PreferedItem> preferedItems = new ArrayList<PreferedItem>();
			for (Element tdItem : tdsItem) {
				Item item = combineds.get(tdItem.getElementsByClass("name").get(0).ownText());
				Double presence = Double.valueOf(tdItem.getElementsByClass("ratio").get(0).ownText().replace("%", ""));
				preferedItems.add(new PreferedItem(item, presence));

			}
			champ.setPreferedItems(preferedItems);
			champs.add(champ);
		}
		return champs;
	}
}
