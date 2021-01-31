package com.toteuch.tftoptimizer.ihm.main.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import com.toteuch.tftoptimizer.domaine.Item;

public class ComponentPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public final static String NAME = "COMPONENT_PANEL";
	private Map<String, ComponentLabel> componentLabels = new HashMap<String, ComponentLabel>();

	public ComponentPanel(Map<Item, Integer> components) {
		this.setLayout(new GridBagLayout());
		this.setName(NAME);
		int index = 0;
		for (Item item : components.keySet()) {
			ComponentLabel component = new ComponentLabel(item, components.get(item));
			GridBagConstraints c = new GridBagConstraints();
			c.gridx = 0;
			c.gridy = index;
			c.insets = new Insets(1, 1, 1, 1);
			add(component, c);
			componentLabels.put(component.getName(), component);
			index++;
		}
	}

	public ComponentLabel getComponentLabel(String component) {
		return componentLabels.get(String.format("%s%s", ComponentLabel.PREFIX, component));
	}

	public List<ComponentLabel> getAllComponentLabel() {
		List<ComponentLabel> ret = new ArrayList<ComponentLabel>();
		for (ComponentLabel x : componentLabels.values()) {
			ret.add(x);
		}
		return ret;
	}
}
