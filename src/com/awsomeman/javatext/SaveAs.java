package com.awsomeman.javatext;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;

public class SaveAs implements ActionListener {
	
	public static JFileChooser dialog = JavaText.dialog;
	public static Action SaveAs;
	public static boolean changed = JavaText.changed;
	public static String currentFile = JavaText.currentFile;
	
	public SaveAs() {
		SaveAs = new AbstractAction("Save As") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				saveFileAs(currentFile);
			}
		};
	}
	
	public static void saveFileAs(String fileName) {
		FileDialog save = new FileDialog(JavaText.frame, "Save", FileDialog.SAVE);
		save.setVisible(true);
		String path = save.getDirectory() + save.getFile();
		File newFile = new File(path);
		try {
			newFile.createNewFile();
			FileWriter fw = new FileWriter(newFile.getAbsoluteFile(), true);
			JavaText.textArea.write(fw);
			fw.close();
			fileName = newFile.getName();
			int pos = fileName.lastIndexOf(".");
			if (pos > 0) {
			    fileName = fileName.substring(0, pos);
			}
			JavaText.frame.setTitle("JavaText - " + fileName);
			SaveAs.setEnabled(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent e) {}
}
