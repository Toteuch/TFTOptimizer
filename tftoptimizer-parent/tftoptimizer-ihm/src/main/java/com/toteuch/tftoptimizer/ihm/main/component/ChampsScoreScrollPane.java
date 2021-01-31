package com.toteuch.tftoptimizer.ihm.main.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.ihm.layout.WrapLayout;

public class ChampsScoreScrollPane extends JScrollPane {
	private static final long serialVersionUID = 1L;

	public final static String NAME = "CHAMP_SCORE_SCROLL_PANE";
	private Map<String, ChampScorePanel> champScorePanels = new HashMap<String, ChampScorePanel>();

	public ChampsScoreScrollPane(List<Champion> champs) {
		// Mandatory JPanel contained by ScrollPane & containing ChampPanel
		JPanel subScrollPanePanel = new JPanel();
		subScrollPanePanel.setLayout(new WrapLayout(WrapLayout.LEFT));
		add(subScrollPanePanel);

		for (Champion champ : champs) {
			ChampScorePanel champScorePanel = new ChampScorePanel(champ);
			subScrollPanePanel.add(champScorePanel);
			champScorePanels.put(champScorePanel.getName(), champScorePanel);
		}

		// Scroll pane config
		setName(NAME);
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		getVerticalScrollBar().setUnitIncrement(16);
		setViewportView(subScrollPanePanel);
	}

	public ChampScorePanel getChampScorePanel(String champName) {
		return champScorePanels.get(String.format("%s%s", ChampScorePanel.PREFIX, champName));
	}

	public List<ChampScorePanel> getAllChampScorePanel() {
		return (List<ChampScorePanel>) champScorePanels.values();
	}
}
