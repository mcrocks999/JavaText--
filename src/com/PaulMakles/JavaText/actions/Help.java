package com.PaulMakles.JavaText.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import com.PaulMakles.JavaText.JavaText;

public class Help implements ActionListener {
	
	public static Action Help;
	
	public Help() {
		Help = new AbstractAction("Help") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(JavaText.frame, "Help feature soon to come!");
			}
		};
	}

	public void actionPerformed(ActionEvent e) {}
}