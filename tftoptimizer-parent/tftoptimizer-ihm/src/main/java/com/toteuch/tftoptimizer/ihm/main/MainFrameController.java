package com.toteuch.tftoptimizer.ihm.main;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;

import com.kdgregory.swinglib.components.MainFrame;
import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.domaine.Item;
import com.toteuch.tftoptimizer.ihm.main.component.ChampsScoreScrollPane;
import com.toteuch.tftoptimizer.ihm.main.component.ComponentLabel;
import com.toteuch.tftoptimizer.ihm.main.component.ComponentPanel;
import com.toteuch.tftoptimizer.ihm.operation.SortByBestScoreOperation;

public class MainFrameController {
	private final static String BASE_TITLE = "TFTOptimizer";
	private final static int WIDTH = 560;
	private final static int HEIGHT = 280;

	private Concierge concierge;
	private ActionRegistry actionRegistry;

	private JFrame mainFrame;
	private JPanel contentPane;
	private Map<Item, Integer> components;
	private List<Champion> champs;

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
		// mainFrame.setUndecorated(true);
		mainFrame.pack();
		concierge.setMainFrame(this, mainFrame);

		mainFrame.setVisible(true);
	}

	private Container createContentPane() {
		contentPane = new JPanel();
		contentPane.setName("CONTENT_PANE");
		contentPane.setLayout(new GridBagLayout());
		contentPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(actionRegistry.quitTFTOptimizer);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.NORTHEAST;
		c.insets = new Insets(1, 1, 1, 1);
		c.weightx = 10;
		c.weighty = 0;
		contentPane.add(exitButton, c);

		return contentPane;
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

//----------------------------------------------------------------------------
//  Internals
//----------------------------------------------------------------------------	

	private void displayComponents() {
		ComponentPanel componentPanel = new ComponentPanel(components);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.fill = GridBagConstraints.NONE;
		c.gridheight = 2;
		contentPane.add(componentPanel, c);
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
		refreshView(contentPane);
	}

	private void displayChamps() {
		ChampsScoreScrollPane champScrollPane = new ChampsScoreScrollPane(champs);
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		for (Component comp : contentPane.getComponents()) {
			if (comp instanceof ChampsScoreScrollPane) {
				contentPane.remove(comp);
				break;
			}
		}
		contentPane.add(champScrollPane, c);
		refreshView(contentPane);
	}

	private void refreshView(Component c) {
		c.revalidate();
		c.repaint();
	}
}
