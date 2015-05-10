package com.PaulMakles.JavaTextPlusPlus.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.PaulMakles.JavaTextPlusPlus.JavaText;

public class Save implements ActionListener {
	
	public static String currentFile = JavaText.currentFile;
	public static String currentFilePath = JavaText.currentFilePath;
	
	public static Action Save;
	
	public Save() {
		Save = new AbstractAction("Save") {
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				currentFile = JavaText.currentFile;
				currentFilePath = JavaText.currentFilePath;
				if(!currentFile.equals("Untitled")) {
					saveFile(currentFilePath, currentFile, true);
				} else {
					SaveAs.saveFileAs(currentFile);
				}
			}
		};
	}
	
	public static void saveFile(String filePath, String fileName, Boolean shouldSetTitle) {
		try {
			FileWriter w = new FileWriter(filePath);
			JavaText.textArea.write(w);
			w.close();
			if (shouldSetTitle==true) {
				JavaText.currentFile = fileName;
				JavaText.frame.setTitle("JavaText - " + fileName);
			}
			Save.setEnabled(false);
		} catch (IOException e) {}
	}
	
	public void actionPerformed(ActionEvent e) {}
}
