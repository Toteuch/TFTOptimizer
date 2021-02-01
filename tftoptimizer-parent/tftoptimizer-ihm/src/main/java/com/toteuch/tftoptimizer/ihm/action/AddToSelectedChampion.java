package com.toteuch.tftoptimizer.ihm.action;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.toteuch.tftoptimizer.domaine.Champion;
import com.toteuch.tftoptimizer.ihm.main.Concierge;
import com.toteuch.tftoptimizer.ihm.main.component.ChampLabel;

public class AddToSelectedChampion extends AbstractAction implements MouseListener {
	private static final long serialVersionUID = 1L;

	private Logger LOG = LogManager.getLogger(getClass());

//----------------------------------------------------------------------------
//  Instance Variables and Constructor
//----------------------------------------------------------------------------

	private Concierge concierge;

	public AddToSelectedChampion(Concierge concierge) {
		this.concierge = concierge;
	}

//----------------------------------------------------------------------------
//  ActionListener
//----------------------------------------------------------------------------	

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		LOG.info("invoked");
		ChampLabel champLabel = (ChampLabel) e.getSource();
		if (SwingUtilities.isLeftMouseButton(e)) {
			String champLabelName = champLabel.getName();
			List<Champion> champs = concierge.getMainFrame().getChamps();
			for (Champion c : champs) {
				if (StringUtils.equals(String.format("%s%s", ChampLabel.PREFIX, c.getName()), champLabelName)) {
					concierge.getMainFrame().addSelectedChamp(c);
					break;
				}
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			String champLabelName = champLabel.getName();
			List<Champion> champs = concierge.getMainFrame().getChamps();
			for (Champion c : champs) {
				if (StringUtils.equals(String.format("%s%s", ChampLabel.PREFIX, c.getName()), champLabelName)) {
					concierge.getMainFrame().removeSelectedChamp(c);
					break;
				}
			}
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
