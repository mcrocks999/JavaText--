package com.PaulMakles.JavaTextPlusPlus.functions;

import java.awt.Font;

import com.PaulMakles.JavaTextPlusPlus.JavaText;
import com.PaulMakles.JavaTextPlusPlus.actions.Settings;

public class UpdateFont {
	public static void updateFont() {
		JavaText.textArea.setFont(new Font(Settings.fontTypeface,Font.PLAIN,Settings.fontSize));
		Settings.saveSettings();
	}
}