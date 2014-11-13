package com.awsomeman.javatext.actions;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings implements ActionListener {
	
	public static Action Settings;
	
	public Settings() {
		Settings = new AbstractAction("Settings") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new GridLayout(0, 2));
				panel.add(new JLabel("                       Key"));
				panel.add(new JLabel("Shortcuts"));
				panel.add(new JLabel(""));
				panel.add(new JLabel(""));
				panel.add(new JLabel("Copy CTRL+"));
				panel.add(new JLabel("Paste CTRL+"));
				JTextField copyShortcut = new JTextField("C");
				panel.add(copyShortcut);
				JTextField pasteShortcut = new JTextField("V");
				panel.add(pasteShortcut);
				panel.add(new JLabel("Cut CTRL+"));
				panel.add(new JLabel(""));
				JTextField cutShortcut = new JTextField("X");
				panel.add(cutShortcut);
				panel.add(new JLabel(""));
				panel.add(new JLabel(""));
				panel.add(new JLabel(""));
				panel.add(new JLabel("                    Other"));
				panel.add(new JLabel("Settings"));
				panel.add(new JLabel(""));
				panel.add(new JLabel(""));
				panel.add(new JLabel("File format"));
				panel.add(new JLabel("Name"));
				JTextField fileFormat = new JTextField(".txt");
				panel.add(fileFormat);
				JTextField userName = new JTextField("");
				panel.add(userName);

				int result = JOptionPane.showConfirmDialog(null, panel, "Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				switch (result) {
				    case JOptionPane.OK_OPTION:
				    	System.out.println(copyShortcut.getText());
				    	System.out.println(pasteShortcut.getText());
				    	System.out.println(cutShortcut.getText());
				    	System.out.println(fileFormat.getText());
				    	System.out.println(userName.getText());
				        //...
				        break;
				    case JOptionPane.CANCEL_OPTION:
				        //...
				        break;
				}
			}
		};
	}
	
	public void actionPerformed(ActionEvent e) {}
}
