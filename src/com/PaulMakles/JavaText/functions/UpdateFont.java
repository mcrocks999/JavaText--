package com.PaulMakles.JavaText.functions;

import java.awt.Font;

import com.PaulMakles.JavaText.JavaText;
import com.PaulMakles.JavaText.actions.Settings;

public class UpdateFont {
	public static void updateFont() {
		JavaText.textArea.setFont(new Font(Settings.fontTypeface,Font.PLAIN,Settings.fontSize));
		Settings.saveSettings();
	}
}