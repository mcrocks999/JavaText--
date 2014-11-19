package com.PaulMakles.JavaText.actions.autosave;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;

import com.PaulMakles.JavaText.actions.Settings;

public class EnableAutosave implements ActionListener {
	
	public static Action EnableAutosave;
	
	public EnableAutosave() {
		EnableAutosave = new AbstractAction("EnableAutosave") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				Settings.autoSave = "true";
			}
		};
	}

	public void actionPerformed(ActionEvent e) {}
}
