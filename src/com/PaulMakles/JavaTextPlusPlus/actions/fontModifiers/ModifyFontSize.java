package com.PaulMakles.JavaTextPlusPlus.actions.fontModifiers;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.PaulMakles.JavaTextPlusPlus.actions.Settings;
import com.PaulMakles.JavaTextPlusPlus.functions.UpdateFont;

public class ModifyFontSize {

	public static Action enlarge;
	public static Action shrink;
	public static Action enlargeByFive;
	public static Action shrinkByFive;
	public static Action enlargeByTen;
	public static Action shrinkByTen;
	
	public ModifyFontSize() {
		enlarge = new AbstractAction("enlarge") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				Settings.fontSize += 1;
				UpdateFont.updateFont();
			}
			
		};
		shrink = new AbstractAction("shrink") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				Settings.fontSize -= 1;
				UpdateFont.updateFont();
			}
			
		};
		enlargeByFive = new AbstractAction("enlargeByFive") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				Settings.fontSize += 5;
				UpdateFont.updateFont();
			}
			
		};
		shrinkByFive = new AbstractAction("shrinkByFive") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				Settings.fontSize -= 5;
				UpdateFont.updateFont();
			}
			
		};
		enlargeByTen = new AbstractAction("enlargeByTen") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				Settings.fontSize += 10;
				UpdateFont.updateFont();
			}
			
		};
		shrinkByTen = new AbstractAction("shrinkByTen") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent arg0) {
				Settings.fontSize -= 10;
				UpdateFont.updateFont();
			}
			
		};
	}
}
