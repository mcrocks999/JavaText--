package com.PaulMakles.JavaTextPlusPlus.actions.autosave;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.PaulMakles.JavaTextPlusPlus.actions.Settings;

public class SetMS implements ActionListener {
	
	public static Action SetMS;
	
	public SetMS() {
		SetMS = new AbstractAction("SetMS") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new GridLayout(0, 1));
				panel.add(new JLabel("Auto-save every (ms)"));
				JTextField autoSaveMStf = new JTextField(Settings.autoSaveMS.toString());
				panel.add(autoSaveMStf);

				int result = JOptionPane.showConfirmDialog(null, panel, "Autosave Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				switch (result) {
				    case JOptionPane.OK_OPTION:
				    	try {
				    		Integer stringToInt = Integer.parseInt(autoSaveMStf.getText());
					    	Settings.autoSaveMS = stringToInt;
				    	}catch (Exception e1){
				    		JPanel panel2 = new JPanel(new GridLayout(0, 1));
				    		panel2.add(new JLabel("Cannot set new autosave time"));
				    		result = JOptionPane.showConfirmDialog(null, panel2, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
				    	}
				        break;
				    case JOptionPane.CANCEL_OPTION:
				        break;
				}
			}
		};
	}
	
	public void actionPerformed(ActionEvent e) {}
}
