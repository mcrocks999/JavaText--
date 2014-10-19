package com.awsomeman.javatext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

public class Settings implements ActionListener {
	
	public static Action Settings;
	
	public Settings() {
		Settings = new AbstractAction("Settings") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(JavaText.frame, "Settings soon to come.");
			}
		};
	}
	
	public void actionPerformed(ActionEvent e) {}
}
