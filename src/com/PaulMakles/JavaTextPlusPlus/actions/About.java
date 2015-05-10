package com.PaulMakles.JavaTextPlusPlus.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import com.PaulMakles.JavaTextPlusPlus.JavaText;
import com.PaulMakles.JavaTextPlusPlus.language.LanguageParser;

public class About implements ActionListener {
	
	public static Action Help;
	
	public About() {
		Help = new AbstractAction("Help") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(JavaText.frame, LanguageParser.getWords(52)+" 1.1.1 "+LanguageParser.getWords(53));
			}
		};
	}

	public void actionPerformed(ActionEvent e) {}
}
