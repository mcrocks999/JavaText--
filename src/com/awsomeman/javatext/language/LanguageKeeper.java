package com.awsomeman.javatext.language;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

import com.awsomeman.javatext.JavaText;
import com.awsomeman.javatext.functions.CreateFile;

public class LanguageKeeper {

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
