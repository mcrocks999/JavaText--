package com.PaulMakles.JavaText.actions.fontModifiers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.PaulMakles.JavaText.actions.Settings;
import com.PaulMakles.JavaText.functions.UpdateFont;

public class ModifyFontface {

	public static Action setArial;
	public static Action setComicSans;
	public static Action setTimesNewRoman;
	
	public ModifyFontface() {
		setArial = new AbstractAction("setArial") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				Settings.fontTypeface = "Arial";
				UpdateFont.updateFont();
			}
			
		};
		setComicSans = new AbstractAction("setComicSans") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				Settings.fontTypeface = "Comic Sans MS";
				UpdateFont.updateFont();
			}
			
		};
		setTimesNewRoman = new AbstractAction("setTimesNewRoman") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				Settings.fontTypeface = "Times New Roman";
				UpdateFont.updateFont();
			}
			
		};
	}
}
