package com.awsomeman.javatext.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;

import com.awsomeman.javatext.JavaText;

public class Save implements ActionListener {
	
	public static String currentFile = JavaText.currentFile;
	
	public static Action Save;
	
	public Save() {
		Save = new AbstractAction("Save") {
			private static final long serialVersionUID = 1L;
			
			public void actionPerformed(ActionEvent e) {
				currentFile = JavaText.currentFile;
				if(!currentFile.equals("Untitled")) {
					saveFile(currentFile);
				} else {
					SaveAs.saveFileAs(currentFile);
				}
			}
		};
	}
	
	public static void saveFile(String fileName) {
		try {
			FileWriter w = new FileWriter(fileName);
			JavaText.textArea.write(w);
			w.close();
			JavaText.currentFile = fileName;
			JavaText.frame.setTitle("JavaText - " + currentFile);
			Save.setEnabled(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {}
}
