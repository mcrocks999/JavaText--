package com.awsomeman.javatext.actions;

import java.awt.Font;
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

import com.awsomeman.javatext.JavaText;
import com.awsomeman.javatext.functions.CreateFile;
import com.awsomeman.javatext.language.LanguageManager;
import com.awsomeman.javatext.language.LanguageParser;

public class Settings implements ActionListener {
	
	public static Action Settings;
	public static Action OpenSettingsFile;
	public static String copyShortcut = "c";
	public static String pasteShortcut = "p";
	public static String cutShortcut = "x";
	public static String fileFormat = ".txt";
	public static String userName = "";
	public static String autoSave = "false";
	public static Integer autoSaveMS = 300000;
	public static String fontTypeface = "Arial";
	public static Integer fontSize = 14;
	
	public Settings() {
		Settings = new AbstractAction("Settings") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new GridLayout(0, 2));
				panel.add(new JLabel(LanguageParser.getWords(42)));
				JTextField fileFormattf = new JTextField(fileFormat);
				panel.add(fileFormattf);
				panel.add(new JLabel(LanguageParser.getWords(43)));
				JTextField userNametf = new JTextField(userName);
				panel.add(userNametf);
				panel.add(new JLabel(LanguageParser.getWords(44)));
				JTextField fonttf = new JTextField(fontTypeface);
				panel.add(fonttf);
				panel.add(new JLabel(LanguageParser.getWords(45)));
				JTextField fontSizetf = new JTextField(fontSize.toString());
				panel.add(fontSizetf);

				int result = JOptionPane.showConfirmDialog(null, panel, "Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				switch (result) {
				    case JOptionPane.OK_OPTION:
				    	fileFormat = fileFormattf.getText();
				    	userName = userNametf.getText();
				    	fontTypeface = fonttf.getText();
				    	try{
				    		fontSize = Integer.parseInt(fontSizetf.getText());
				    		JavaText.textArea.setFont(new Font(fontTypeface,Font.PLAIN,fontSize));
				    	}finally{}
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
				File file = new File("settings.jtsettings");
				Open.openFile(file);
			}
		};
		
	}
	
	public void actionPerformed(ActionEvent e) {}
	
	public static void saveSettings() {
		File file = new File("settings.jtsettings");
		FileWriter w;
		try {
			w = new FileWriter(file);
			w.write(fileFormat+"-"+userName+"-"+autoSave+"-"+autoSaveMS+"-"+fontTypeface+"-"+fontSize+"-"+LanguageManager.currentLanguageUUID);
			w.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(JavaText.frame, LanguageParser.getWords(46));
		}
	}
	
	public static void loadSettings() {
		System.out.println("Loading settings...");
		File file = new File("settings.jtsettings");
		Boolean loadSettings = false;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			loadSettings = true;
		} catch (FileNotFoundException e1) {
			System.out.println("settings.jtsettings not found, attempting to generate settings file.");
			CreateFile.createFile("settings.jtsettings",".txt--false-300000-Arial-14");
			try {
				reader = new BufferedReader(new FileReader(file));
				loadSettings = true;
			} catch (FileNotFoundException e) {System.out.println("IOException. Settings will not be saved.");}
		}
		if (loadSettings==true) {
			System.out.println("Attempting to read settings file");
			try {
				String line = reader.readLine();
				String[] settings = line.split("-");
				
				try{
					fileFormat = settings[0];
				}catch (Exception e){
					fileFormat = ".txt";
				}
				try{
					userName = settings[1];				
				}catch (Exception e){
					userName = "";
				}
				try{
					autoSave = settings[2];
				}catch (Exception e){
					autoSave = "false";
				}
				try{
					autoSaveMS = Integer.parseInt(settings[3]);				
				}catch (Exception e){
					autoSaveMS = 300000;
				}
				try{
					fontTypeface = settings[4];
				}catch (Exception e){
					fontTypeface = "Arial";
				}
				try{
					fontSize = Integer.parseInt(settings[5]);				
				}catch (Exception e){
					fontSize = 14;
				}
				try{
					LanguageManager.loadLanguageBySettings = Integer.parseInt(settings[6]);				
				}catch (Exception e){
					LanguageManager.loadLanguageBySettings = 65296;
				}
				
				reader.close();
				
				System.out.println("Successfully read settings file.");
			} catch (IOException e1) {System.out.println("IOException. Settings will not be saved.");}
		}
		
	}
}