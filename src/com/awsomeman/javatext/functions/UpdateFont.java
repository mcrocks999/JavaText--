package com.awsomeman.javatext.functions;

import java.awt.Font;

import com.awsomeman.javatext.JavaText;
import com.awsomeman.javatext.actions.Settings;

public class UpdateFont {
	public static void updateFont() {
		JavaText.textArea.setFont(new Font(Settings.fontTypeface,Font.PLAIN,Settings.fontSize));
		Settings.saveSettings();
	}
}