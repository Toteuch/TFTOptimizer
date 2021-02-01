package com.toteuch.tftoptimizer.ihm.main;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.commons.lang3.StringUtils;

import com.kdgregory.swinglib.components.MainFrame;
import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.domaine.Item;
import com.toteuch.tftoptimizer.ihm.layout.WrapLayout;
import com.toteuch.tftoptimizer.ihm.main.component.ChampSelectedPanel;
import com.toteuch.tftoptimizer.ihm.main.component.ChampsScoreScrollPane;
import com.toteuch.tftoptimizer.ihm.main.component.ComponentLabel;
import com.toteuch.tftoptimizer.ihm.main.component.ComponentPanel;
import com.toteuch.tftoptimizer.ihm.main.component.FilterField;
import com.toteuch.tftoptimizer.ihm.main.component.SelectedChampsCheckBox;
import com.toteuch.tftoptimizer.ihm.main.component.SidePane;
import com.toteuch.tftoptimizer.ihm.operation.FilterByNameOperation;
import com.toteuch.tftoptimizer.ihm.operation.SortByBestScoreOperation;
import com.toteuch.tftoptimizer.ihm.util.ComponentsUtils;

public class MainFrameController {
	private final static String BASE_TITLE = "TFTOptimizer";
	private final static int WIDTH = 550;
	private final static int HEIGHT = 280;

	private final static boolean DEFAULT_ALWAYS_ON_TOP = true;

	private Concierge concierge;
	private ActionRegistry actionRegistry;

	private JFrame mainFrame;
	private JPanel mainPane;
	private JPanel selectedChampsPane;
	private JPanel sidePane;

	private Map<Item, Integer> components;
	private List<Champion> champs;
	private List<Champion> selectedChamps;

	public MainFrameController(Concierge concierge) {
		this.concierge = concierge;
		actionRegistry = new ActionRegistry(concierge);
	}

//----------------------------------------------------------------------------
//  Initialization
//----------------------------------------------------------------------------

	public void buildAndShow() {
		mainFrame = new MainFrame(BASE_TITLE, actionRegistry.quitTFTOptimizer);
		mainFrame.setContentPane(createContentPane());
		JPanel glassPane = new JPanel();
		glassPane.add(new JLabel("Loading"));
		mainFrame.setGlassPane(glassPane);
		mainFrame.setUndecorated(true);
		mainFrame.setResizable(false);
		mainFrame.setAlwaysOnTop(DEFAULT_ALWAYS_ON_TOP);
		mainFrame.pack();
		concierge.setMainFrame(this, mainFrame);

		mainFrame.setVisible(true);
	}

	private Container createContentPane() {
		Container contentPane = new JPanel();
		contentPane.setName("CONTENT_PANE");
		contentPane.setLayout(new GridBagLayout());

		mainPane = createMainPane();
		mainPane.setVisible(true);
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0.5;
		c.fill = GridBagConstraints.VERTICAL;
		contentPane.add(mainPane, c);

		selectedChampsPane = createSelectedChampsPane();
		selectedChampsPane.setVisible(false);
		contentPane.add(selectedChampsPane, c);

		JButton expandButton = new JButton(">");
		expandButton.setMinimumSize(new Dimension(13, 10));
		expandButton.setMaximumSize(new Dimension(13, HEIGHT));
		expandButton.setMargin(new Insets(0, 0, 0, 0));
		expandButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (sidePane.isVisible()) {
					sidePane.setVisible(false);
					expandButton.setText(">");
				} else {
					sidePane.setVisible(true);
					expandButton.setText("<");
				}

				mainFrame.pack();
				refreshView(mainFrame);
			}
		});
		GridBagConstraints cExpand = new GridBagConstraints();
		cExpand.gridx = 1;
		cExpand.gridy = 0;
		cExpand.fill = GridBagConstraints.VERTICAL;

		contentPane.add(expandButton, cExpand);

		sidePane = new SidePane(actionRegistry, HEIGHT, mainFrame, DEFAULT_ALWAYS_ON_TOP);
		FilterField ff = (FilterField) ComponentsUtils.getRecursivelyFirstChildByName(sidePane, FilterField.NAME);
		ff.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				filter();
			}

			public void removeUpdate(DocumentEvent e) {
				filter();
			}

			public void insertUpdate(DocumentEvent e) {
				filter();
			}

			private void filter() {
				new FilterByNameOperation(concierge).start();
			}
		});
		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 2;
		c1.fill = GridBagConstraints.VERTICAL;
		c1.weightx = 0.5;
		sidePane.setVisible(false);
		contentPane.add(sidePane, c1);

		return contentPane;
	}

	private JPanel createMainPane() {
		JPanel mainPane = new JPanel();
		mainPane.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		mainPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		mainPane.setLayout(new GridBagLayout());

		return mainPane;
	}

	public JPanel createSelectedChampsPane() {
		selectedChamps = new ArrayList<Champion>();
		JPanel selectedChampsPanel = new JPanel();
		selectedChampsPanel.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		selectedChampsPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		selectedChampsPanel.setLayout(new WrapLayout(WrapLayout.LEFT));

		return selectedChampsPanel;
	}

//----------------------------------------------------------------------------
//  Operational Methods
//----------------------------------------------------------------------------	

	public void setBusyState(boolean isBusy) {
		if (isBusy) {
			concierge.getCursorManager().pushBusyCursor(mainFrame.getRootPane());
		} else {
			concierge.getCursorManager().popCursor(mainFrame.getRootPane());
		}
	}

	public void activateGlassPane(boolean isBusy) {
		mainFrame.getGlassPane().setVisible(isBusy);
	}

	public void setComponentsList(Map<String, Item> componentItems) {
		components = new HashMap<Item, Integer>();
		for (Item item : componentItems.values()) {
			components.put(item, 0);
		}
		displayComponents();
	}

	@SuppressWarnings("unchecked")
	public void setChampsList(List<Champion> champs) {
		this.champs = champs;
		displayChamps();
	}

	public Map<Item, Integer> getComponents() {
		return components;
	}

	public List<Champion> getChamps() {
		return champs;
	}

	public void changePinState() {
		if (mainFrame.isAlwaysOnTop()) {
			mainFrame.setAlwaysOnTop(false);
		} else {
			mainFrame.setAlwaysOnTop(true);
		}
	}

	public void reset() {
		// reset input
		Map<String, Item> initCompo = new HashMap<String, Item>();
		for (Item component : components.keySet()) {
			initCompo.put(component.getName(), component);
		}
		setComponentsList(initCompo);

		// reset filterField
		FilterField filterField = (FilterField) ComponentsUtils.getRecursivelyFirstChildByName(sidePane, FilterField.NAME);
		if (null != filterField) {
			filterField.setText("");
		}

		// reset SelectedChampsPanel data & visibility
		if (selectedChampsPane.isVisible()) {
			SelectedChampsCheckBox sccb = (SelectedChampsCheckBox) ComponentsUtils.getRecursivelyFirstChildByName(sidePane, SelectedChampsCheckBox.NAME);
			selectedChampsPane.setVisible(false);
			mainPane.setVisible(true);
			sccb.setSelected(false);
			while (selectedChampsPane.getComponents().length > 0) {
				selectedChampsPane.remove(0);
			}
			selectedChamps = new ArrayList<Champion>();
		}

		// sort by best score for reset champs
		new SortByBestScoreOperation(concierge).start();
	}

	public String getFilter() {
		FilterField ff = (FilterField) ComponentsUtils.getRecursivelyFirstChildByName(sidePane, FilterField.NAME);
		return ff.getText();
	}

	@SuppressWarnings("unchecked")
	public void setFilteredChampsList(List<Champion> filteredChamps) {
		displayChamps(filteredChamps);
	}

	public void swtichMainFrameView() {
		SelectedChampsCheckBox sccb = (SelectedChampsCheckBox) ComponentsUtils.getRecursivelyFirstChildByName(sidePane, SelectedChampsCheckBox.NAME);
		if (this.mainPane.isVisible()) {
			this.mainPane.setVisible(false);
			this.selectedChampsPane.setVisible(true);
			sccb.setSelected(true);
		} else {
			this.mainPane.setVisible(true);
			this.selectedChampsPane.setVisible(false);
			sccb.setSelected(false);
		}
	}

	public void addSelectedChamp(Champion c) {
		if (!selectedChamps.contains(c)) {
			selectedChamps.add(c);
			addChampSelectedPanel(c);
			FilterField filterField = (FilterField) ComponentsUtils.getRecursivelyFirstChildByName(sidePane, FilterField.NAME);
			if (null != filterField && StringUtils.isNoneEmpty(filterField.getText())) {
				filterField.setText("");
				new SortByBestScoreOperation(concierge).start();
			}
		}

	}

	public void removeSelectedChamp(Champion c) {
		if (selectedChamps.contains(c)) {
			selectedChamps.remove(c);
			removeChampSelectedPanel(c);
		}
	}

//----------------------------------------------------------------------------
//  Internals
//----------------------------------------------------------------------------	

	private void displayComponents() {
		ComponentPanel componentPanel = new ComponentPanel(components);
		for (Component c : mainPane.getComponents()) {
			if (StringUtils.equals(c.getName(), componentPanel.getName())) {
				mainPane.remove(c);
			}
		}
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 2;
		mainPane.add(componentPanel, c);
		for (ComponentLabel componentLabel : componentPanel.getAllComponentLabel()) {
			componentLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int value = Integer.parseInt(componentLabel.getText());
					int newValue = 0;
					if (SwingUtilities.isLeftMouseButton(e)) {
						newValue = value + 1;
					} else if (SwingUtilities.isRightMouseButton(e)) {
						if (value > 0) {
							newValue = value - 1;
						}
					}
					if (value != newValue) {
						componentLabel.setText(String.valueOf(newValue));
						String componentName = StringUtils.substring(componentLabel.getName(), ComponentLabel.PREFIX.length(), componentLabel.getName().length());
						for (Item item : components.keySet()) {
							if (StringUtils.equals(item.getName(), componentName)) {
								components.put(item, newValue);
								break;
							}
						}
						new SortByBestScoreOperation(concierge).start();
					}
				}
			});
		}
		refreshView(mainPane);
	}

	private void displayChamps(@SuppressWarnings("unchecked") List<Champion>... args) {
		ChampsScoreScrollPane champScrollPane = null;
		if (args.length > 0) {
			List<Champion> filteredChamp = null;
			for (List<Champion> l : args) {
				filteredChamp = l;
			}
			champScrollPane = new ChampsScoreScrollPane(filteredChamp, actionRegistry);
		} else {
			champScrollPane = new ChampsScoreScrollPane(champs, actionRegistry);
		}
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 10;
		c.weighty = 10;
		c.fill = GridBagConstraints.BOTH;
		for (Component comp : mainPane.getComponents()) {
			if (comp instanceof ChampsScoreScrollPane) {
				mainPane.remove(comp);
				break;
			}
		}
		mainPane.add(champScrollPane, c);
		refreshView(mainPane);
	}

	private void refreshView(Component c) {
		c.revalidate();
		c.repaint();
	}

	private void addChampSelectedPanel(Champion c) {
		ChampSelectedPanel csp = new ChampSelectedPanel(c);
		selectedChampsPane.add(csp);

	}

	private void removeChampSelectedPanel(Champion c) {
		ChampSelectedPanel csp = (ChampSelectedPanel) ComponentsUtils.getRecursivelyFirstChildByName(selectedChampsPane, String.format("%s%s", ChampSelectedPanel.PREFIX, c.getName()));
		selectedChampsPane.remove(csp);
	}
}
