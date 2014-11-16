package com.awsomeman.javatext.language;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.awsomeman.javatext.JavaText;
import com.awsomeman.javatext.actions.Exit;
import com.awsomeman.javatext.actions.Settings;
import com.awsomeman.javatext.functions.CreateFile;

public class LanguageKeeper {

	public static Action openLanguageSelect;
	
	public LanguageKeeper() {
		openLanguageSelect = new AbstractAction("LanguageSelect") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				File file = new File("languages.jtsettings");
				Boolean loadSettings = false;
				BufferedReader reader = null;
				String[] languages = null;
				try {
					reader = new BufferedReader(new FileReader(file));
					loadSettings = true;
				} catch (FileNotFoundException e1) {
					System.out.println("languages.jtsettings not found, attempting to generate settings file.");
					LanguageManager.getLanguages();
					String newFile = "";
					for (String path : LanguageManager.languagePaths) {
						newFile += path+"-";
					}
					CreateFile.createFile("languages.jtsettings",newFile);
					try {
						reader = new BufferedReader(new FileReader(file));
						loadSettings = true;
					} catch (FileNotFoundException e11) {System.out.println("IOException. Languages will not be saved.");}
				}
				if (loadSettings==true) {
					System.out.println("Attempting to read languages file");
					try {
						String line = reader.readLine();
						languages = line.split("-");
						
						reader.close();
						
						System.out.println("Successfully read languages file.");
					} catch (IOException e1) {System.out.println("IOException. Languages will not be saved.");}
				}
				
				JPanel panel = new JPanel(new GridLayout(0, 1));
			    JComboBox<?> languagecb = new JComboBox<Object>(languages);
				panel.add(languagecb);

				int result = JOptionPane.showConfirmDialog(null, panel, "Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				switch (result) {
				    case JOptionPane.OK_OPTION:
						String[] returnedValue = LanguageParser.Parse(LanguageManager.loadLanguage(languagecb.getSelectedItem().toString()));
						int languageUUID = Integer.parseInt(returnedValue[2]);
						LanguageManager.currentLanguageUUID = languageUUID;
				    	Settings.saveSettings();
				    	int result2 = JOptionPane.showConfirmDialog(null, LanguageParser.getWords(57), LanguageParser.getWords(58), JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				    	switch (result2) {
						   	case JOptionPane.OK_OPTION:
						   		Exit.ExitMethod();
						  		break;
						   	case JOptionPane.CANCEL_OPTION:
						        break;
				    	}
				        break;
				    case JOptionPane.CANCEL_OPTION:
				        //...
				        break;
				}
			}
		};
	}
	
	public static void getLanguagesFromFile() {
		System.out.println("Loading languages file...");
		File file = new File("languages.jtsettings");
		Boolean loadSettings = false;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			loadSettings = true;
		} catch (FileNotFoundException e1) {
			System.out.println("languages.jtsettings not found, attempting to generate settings file.");
			LanguageManager.getLanguages();
			String newFile = "";
			for (String path : LanguageManager.languagePaths) {
				newFile += path+"-";
			}
			CreateFile.createFile("languages.jtsettings",newFile);
			try {
				reader = new BufferedReader(new FileReader(file));
				loadSettings = true;
			} catch (FileNotFoundException e) {System.out.println("IOException. Languages will not be saved.");}
		}
		if (loadSettings==true) {
			System.out.println("Attempting to read languages file");
			try {
				String line = reader.readLine();
				String[] languages = line.split("-");
				
				for (String language : languages) {
					LanguageManager.languagePaths.add(language);
				}
				
				reader.close();
				
				System.out.println("Successfully read languages file.");
			} catch (IOException e1) {System.out.println("IOException. Languages will not be saved.");}
		}
	}
	
	public static void saveLanguages() {
		File file = new File("languages.jtsettings");
		FileWriter w;
		try {
			w = new FileWriter(file);
			String newFile = "";
			for (String path : LanguageManager.languagePaths) {
				newFile += path+"-";
			}
			w.write(newFile);
			w.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(JavaText.frame, LanguageParser.getWords(46));
		}
	}

}
