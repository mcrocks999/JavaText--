package com.awsomeman.javatext.language;

import java.util.ArrayList;

public class LanguageParser {

	public static void Parse(ArrayList<String> parseThisLanguage) {
		int lineCount = 0;
		for (String line : parseThisLanguage) {
			if (lineCount==0){
				String[] languageInfo = line.split("=");
				LanguageManager.currentLanguageName = languageInfo[0];
				LanguageManager.currentLanguageAuthor = languageInfo[1];
				lineCount += 1;
			}
		}
		LanguageManager.currentLanguage = parseThisLanguage;
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