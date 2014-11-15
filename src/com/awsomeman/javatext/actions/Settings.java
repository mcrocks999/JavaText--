package com.awsomeman.javatext.actions;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings implements ActionListener {
	
	public static Action Settings;
	public static String copyShortcut = "c";
	public static String pasteShortcut = "p";
	public static String cutShortcut = "x";
	public static String fileFormat = ".txt";
	public static String userName = "";
	public static String autoSave = "false";
	public static Integer autoSaveMS = 300000;
	
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
				JTextField copyShortcuttf = new JTextField("C");
				panel.add(copyShortcuttf);
				JTextField pasteShortcuttf = new JTextField("V");
				panel.add(pasteShortcuttf);
				panel.add(new JLabel("Cut CTRL+"));
				panel.add(new JLabel(""));
				JTextField cutShortcuttf = new JTextField("X");
				panel.add(cutShortcuttf);
				panel.add(new JLabel(""));
				panel.add(new JLabel(""));
				panel.add(new JLabel(""));
				panel.add(new JLabel("                    Other"));
				panel.add(new JLabel("Settings"));
				panel.add(new JLabel(""));
				panel.add(new JLabel(""));
				panel.add(new JLabel("File format"));
				panel.add(new JLabel("Name"));
				JTextField fileFormattf = new JTextField(".txt");
				panel.add(fileFormattf);
				JTextField userNametf = new JTextField("");
				panel.add(userNametf);
				panel.add(new JLabel("Auto-save"));
				panel.add(new JLabel("Every (ms)"));
				JTextField autoSavetf = new JTextField("false");
				panel.add(autoSavetf);
				JTextField autoSaveMStf = new JTextField(autoSaveMS);
				panel.add(autoSaveMStf);

				int result = JOptionPane.showConfirmDialog(null, panel, "Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				switch (result) {
				    case JOptionPane.OK_OPTION:
				    	System.out.println(copyShortcuttf.getText());
				    	System.out.println(pasteShortcuttf.getText());
				    	System.out.println(cutShortcuttf.getText());
				    	System.out.println(fileFormattf.getText());
				    	System.out.println(userNametf.getText());
				    	System.out.println(autoSavetf.getText());
				    	System.out.println(autoSaveMStf.getText());
				        break;
				    case JOptionPane.CANCEL_OPTION:
				        //...
				        break;
				}
			}
		};
	}
	
	public void actionPerformed(ActionEvent e) {}

	public static void loadSettings() {
		System.out.println("Loading settings...");
		File file = new File("settings.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String fileName = file.getName();
			
			int pos = fileName.lastIndexOf(".");
			
			if (pos > 0) {
			    fileName = fileName.substring(0, pos);
			}
			
			reader.close();
		} catch (FileNotFoundException e1) {
			System.out.println("settings.txt not found, attempting to generate settings file.");
		} catch (IOException e2) {
			System.out.println("IOException. Settings will not be saved.");
		}
	}
}
