package com.awsomeman.javatext.language;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import com.awsomeman.javatext.actions.Exit;
import com.awsomeman.javatext.actions.Settings;

public class LanguageActions implements ActionListener {
	
	public static Action setPolish;
	public static Action setEnglish;
	
	public LanguageActions() {
		setPolish = new AbstractAction("setPolish") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				LanguageManager.currentLanguageUUID = 37582;
				Settings.saveSettings();
		    	int result2 = JOptionPane.showConfirmDialog(null, LanguageParser.getWords(57), LanguageParser.getWords(58), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		    	switch (result2) {
				   	case JOptionPane.OK_OPTION:
				   		Exit.ExitMethod();
				   		break;
				   	case JOptionPane.CANCEL_OPTION:
				        break;
		    	}
			}
		};
		setEnglish = new AbstractAction("setEnglish") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				LanguageManager.currentLanguageUUID = 65296;
				Settings.saveSettings();
		    	int result2 = JOptionPane.showConfirmDialog(null, LanguageParser.getWords(57), LanguageParser.getWords(58), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		    	switch (result2) {
				   	case JOptionPane.OK_OPTION:
				   		Exit.ExitMethod();
				   		break;
				   	case JOptionPane.CANCEL_OPTION:
				        break;
		    	}
			}
		};
	}

	public void actionPerformed(ActionEvent e) {}
}
