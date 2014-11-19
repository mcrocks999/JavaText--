package com.PaulMakles.JavaText.actions;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.PaulMakles.JavaText.JavaText;
import com.PaulMakles.JavaText.functions.CreateFile;
import com.PaulMakles.JavaText.functions.KeyShortcuts;
import com.PaulMakles.JavaText.language.LanguageParser;

public class KeyShortcutSettings implements ActionListener {
	
	public static Action Settings;
	public static Action OpenSettingsFile;
	
	public KeyShortcutSettings() {
		Settings = new AbstractAction("Settings") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new GridLayout(0, 3));
				panel.add(new JLabel(LanguageParser.getWords(48)));
				panel.add(new JLabel("             CTRL+"));
				JTextField undoShortcuttf = new JTextField(KeyShortcuts.currentUndo);
				panel.add(undoShortcuttf);
				panel.add(new JLabel(LanguageParser.getWords(49)));
				panel.add(new JLabel("             CTRL+"));
				JTextField redoShortcuttf = new JTextField(KeyShortcuts.currentRedo);
				panel.add(redoShortcuttf);
				panel.add(new JLabel(LanguageParser.getWords(13)));
				panel.add(new JLabel("             CTRL+"));
				JTextField saveShortcuttf = new JTextField(KeyShortcuts.currentSave);
				panel.add(saveShortcuttf);
				panel.add(new JLabel(LanguageParser.getWords(14)));
				panel.add(new JLabel("CTRL+SHIFT+"));
				JTextField saveAsShortcuttf = new JTextField(KeyShortcuts.currentSaveAs);
				panel.add(saveAsShortcuttf);
				panel.add(new JLabel(LanguageParser.getWords(12)));
				panel.add(new JLabel("             CTRL+"));
				JTextField openShortcuttf = new JTextField(KeyShortcuts.currentOpen);
				panel.add(openShortcuttf);

				int result = JOptionPane.showConfirmDialog(null, panel, "Key Shortcut Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				switch (result) {
				    case JOptionPane.OK_OPTION:
				    	KeyShortcuts.setUndo(undoShortcuttf.getText());
				    	KeyShortcuts.setRedo(redoShortcuttf.getText());
				    	KeyShortcuts.setSave(saveShortcuttf.getText());
				    	KeyShortcuts.setSaveAs(saveAsShortcuttf.getText());
				    	KeyShortcuts.setOpen(openShortcuttf.getText());
				    	KeyShortcuts.currentUndo = undoShortcuttf.getText().toUpperCase();
				    	KeyShortcuts.currentRedo = redoShortcuttf.getText().toUpperCase();
				    	KeyShortcuts.currentSave = saveShortcuttf.getText().toUpperCase();
				    	KeyShortcuts.currentSaveAs = saveAsShortcuttf.getText().toUpperCase();
				    	KeyShortcuts.currentOpen = openShortcuttf.getText().toUpperCase();
				    	saveSettings();
				        break;
				    case JOptionPane.CANCEL_OPTION:
				        //...
				        break;
				}
			}
		};
		OpenSettingsFile = new AbstractAction("OpenSettingsFile") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				File file = new File("keyshortcuts.jtsettings");
				Open.openFile(file);
			}
		};
	}
	
	public void actionPerformed(ActionEvent e) {}

	public static void saveSettings() {
		File file = new File("keyshortcuts.jtsettings");
		FileWriter w;
		try {
			w = new FileWriter(file);
			w.write(KeyShortcuts.currentUndo+"-"+KeyShortcuts.currentRedo+"-"+KeyShortcuts.currentSave+"-"+KeyShortcuts.currentSaveAs+"-"+KeyShortcuts.currentOpen);
			w.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(JavaText.frame, LanguageParser.getWords(50));
		}
	}
	
	public static void loadSettings() {
		System.out.println("Loading settings...");
		File file = new File("keyshortcuts.jtsettings");
		Boolean loadSettings = false;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			loadSettings = true;
		} catch (FileNotFoundException e1) {
			System.out.println("keyshortcuts.jtsettings not found, attempting to generate key shortcuts file.");
			CreateFile.createFile("keyshortcuts.jtsettings","Z-Y-S-S-O");
			try {
				reader = new BufferedReader(new FileReader(file));
				loadSettings = true;
			} catch (FileNotFoundException e) {System.out.println("IOException. Key Shortcuts will not be saved.");}
		}
		if (loadSettings==true) {
			System.out.println("Attempting to read key shortcuts file");
			try {
				String line = reader.readLine();
				String[] settings = line.split("-");
				
			try{
				KeyShortcuts.setUndo(settings[0]);
				KeyShortcuts.currentUndo = settings[0];
			}catch (Exception e){
				KeyShortcuts.setUndo("Z");
				KeyShortcuts.currentUndo = "Z";
			}
			try{
				KeyShortcuts.setRedo(settings[1]);
				KeyShortcuts.currentRedo = settings[1];
			}catch (Exception e){
				KeyShortcuts.setRedo("Y");
				KeyShortcuts.currentRedo = "Y";
			}
			try{
				KeyShortcuts.setSave(settings[2]);
				KeyShortcuts.currentSave = settings[2];
			}catch (Exception e){
				KeyShortcuts.setSave("S");
				KeyShortcuts.currentSave = "S";
			}
			try{
				KeyShortcuts.setSaveAs(settings[3]);
				KeyShortcuts.currentSaveAs = settings[3];
			}catch (Exception e){
				KeyShortcuts.setSaveAs("S");
				KeyShortcuts.currentSaveAs = "S";
			}
			try{
				KeyShortcuts.setOpen(settings[4]);
				KeyShortcuts.currentOpen = settings[4];
			}catch (Exception e){
				KeyShortcuts.setOpen("O");
				KeyShortcuts.currentOpen = "O";
			}
			
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			reader.close();
			
			System.out.println("Successfully read key shortcuts file.");
		} catch (IOException e1) {System.out.println("IOException. Settings will not be saved.");}
	}
	
}
}