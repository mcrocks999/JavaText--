package com.awsomeman.javatext.language;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.awsomeman.javatext.actions.Exit;

public class LanguageManager extends Thread{
	public static ArrayList<String> languagePaths = new ArrayList<String>();
	public static ArrayList<String> languagePathsExclude = new ArrayList<String>();
	public static int currentLanguageUUID = 0;
	public static String currentLanguageName = "";
	public static String currentLanguageAuthor = "";
	public static ArrayList<String> currentLanguage = null;
	public static int loadLanguageBySettings = 0;
	
	public void run() {
		while(true){
			File f = new File("./languages");

		    File[] files = f.listFiles();
		    for (File file : files) {
		    	String fileName = file.getName();
		    	if (fileName.toLowerCase().endsWith(".lng")){
		    		try {
		    			if (!languagePaths.contains(file.getCanonicalPath())&&!languagePathsExclude.contains(file.getCanonicalPath())) {
		    				int result = JOptionPane.showConfirmDialog(null, "Language found:\n"+fileName+"\nAdd language?", "New language detected!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
							switch (result) {
							    case JOptionPane.YES_OPTION:
							    	languagePaths.add(file.getCanonicalPath());
							    	LanguageKeeper.saveLanguages();
							        break;
							    case JOptionPane.NO_OPTION:
							    	languagePathsExclude.add(file.getCanonicalPath());
							        break;
							}
		    			}
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	}
		    }
			 try {
				sleep(5000);
			 } catch (InterruptedException e) {}
		}
	}

	public static void getLanguages() {
		File f = new File("./languages");

	    File[] files = f.listFiles();
	    for (File file : files) {
	    	String fileName = file.getName();
	    	if (fileName.toLowerCase().endsWith(".lng")){
	    		try {
	    			if (!languagePaths.contains(file.getCanonicalPath())&&!languagePathsExclude.contains(file.getCanonicalPath())) {
						int result = JOptionPane.showConfirmDialog(null, "Language found:\n"+fileName+"\nAdd language?", "New language detected!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						switch (result) {
						    case JOptionPane.YES_OPTION:
						    	languagePaths.add(file.getCanonicalPath().toString());
						        break;
						    case JOptionPane.NO_OPTION:
						    	languagePathsExclude.add(file.getCanonicalPath().toString());
						        break;
						}
	    			}
				} catch (IOException e) {
					e.printStackTrace();
				}
	    	}
	    }
	    
	    if (languagePaths.isEmpty()) {
	    	JOptionPane.showConfirmDialog(null, "No languages have been added.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
	    	Exit.ExitMethod();
	    }
	}

	public static String getFirstLanguage() {
		if (loadLanguageBySettings==0) {
			return languagePaths.get(0).toString();
		}else{
			boolean hasFound = false;
			int someCount = 0;
			for (String language : languagePaths) {
				if (hasFound==false) {
					String[] returnedValue = LanguageParser.Parse(LanguageManager.loadLanguage(language));
					try {
						if (Integer.parseInt(returnedValue[2])==loadLanguageBySettings) {
							hasFound = true;
							return languagePaths.get(someCount).toString();
						}
					}finally{}
					someCount += 1;
				}
			}
			return languagePaths.get(0).toString();
		}
	}

	public static ArrayList<String> loadLanguage(String languageToLoad) {
		File file = new File(languageToLoad);
		BufferedReader br = null;
		ArrayList<String> languageFile = new ArrayList<String>();
		
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(file));
 
			while ((sCurrentLine = br.readLine()) != null) {
				languageFile.add(sCurrentLine);
			}
 
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return languageFile;
	}
}