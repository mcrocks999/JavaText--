package com.PaulMakles.JavaTextPlusPlus.language;

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

import com.PaulMakles.JavaTextPlusPlus.JavaText;
import com.PaulMakles.JavaTextPlusPlus.actions.Exit;
import com.PaulMakles.JavaTextPlusPlus.actions.Settings;
import com.PaulMakles.JavaTextPlusPlus.functions.CreateFile;

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
	
	public static void getLanguagesFromFile(Boolean loadingExclusions) {
		String exc = "";
		if (loadingExclusions) {exc = "excluded ";}
		System.out.println("Loading "+exc+"languages file...");
		File file;
		if (loadingExclusions) {file = new File("languages-exclusions.jtsettings");}else{file = new File("languages.jtsettings");}
		Boolean loadSettings = false;
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			loadSettings = true;
		} catch (FileNotFoundException e1) {
			if (loadingExclusions) {System.out.println("languages-exclusions.jtsettings not found, attempting to generate settings file.");}else{System.out.println("languages.jtsettings not found, attempting to generate settings file.");}
			LanguageManager.getLanguages();
			String newFile = "";
			if (loadingExclusions){
				for (String path : LanguageManager.languagePathsExclude) {
					newFile += path+"-";
				}
			}else{
				for (String path : LanguageManager.languagePaths) {
					newFile += path+"-";
				}
			}
			if (loadingExclusions) {CreateFile.createFile("languages-exclusions.jtsettings", newFile);}else{CreateFile.createFile("languages.jtsettings",newFile);}
			saveLanguages(loadingExclusions);
			System.out.println("IOException. "+exc+"Languages will not be read.");
		}
		if (loadSettings==true) {
			System.out.println("Attempting to read "+exc+"languages file");
			try {
				String line = reader.readLine();
				String[] languages = line.split("-");
				
				for (String language : languages) {
					if (loadingExclusions) {LanguageManager.languagePathsExclude.add(language);}else{LanguageManager.languagePaths.add(language);}
				}
				
				reader.close();
				
				System.out.println("Successfully read "+exc+"languages file.");
			} catch (IOException e1) {System.out.println("IOException. "+exc+"Languages will not be read.");}
		}
	}
	
	public static void saveLanguages(Boolean savingExclusions) {
		File file;
		if (!savingExclusions) {file = new File("languages.jtsettings");}else{file = new File("languages-exclusions.jtsettings");}
		FileWriter w;
		try {
			w = new FileWriter(file);
			String newFile = "";
			if (!savingExclusions) {
				for (String path : LanguageManager.languagePaths) {
					newFile += path+"-";
				}
			}else{
				for (String path : LanguageManager.languagePathsExclude) {
					newFile += path+"-";
				}
			}
			w.write(newFile);
			w.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(JavaText.frame, LanguageParser.getWords(46));
		}
	}

}
