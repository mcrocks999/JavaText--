package com.PaulMakles.JavaText.language;

import java.util.ArrayList;

public class LanguageParser {

	public static String[] Parse(ArrayList<String> parseThisLanguage) {
		int lineCount = 0;
		String[] languageInfo = null;
		for (String line : parseThisLanguage) {
			if (lineCount==0){
				languageInfo = line.split("=");
				lineCount += 1;
			}
		}
		LanguageManager.currentLanguage = parseThisLanguage;
		
		return languageInfo;
	}
	
	public static void ParseAndSet(ArrayList<String> parseThisLanguage) {
		String[] returnedValue = Parse(parseThisLanguage);
		LanguageManager.currentLanguageName = returnedValue[0];
		LanguageManager.currentLanguageAuthor = returnedValue[1];
	}
	
	public static String getWords(int line) {
		ArrayList<String> parseThisLanguage = LanguageManager.currentLanguage;
		int lineCount = 0;
		for (String line1 : parseThisLanguage) {
			if (lineCount==line){
				String[] languageLineSplit = line1.split("=");
				return languageLineSplit[1];
			}
			lineCount += 1;
		}
		return null;
	}

}