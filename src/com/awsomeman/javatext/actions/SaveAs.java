package com.awsomeman.javatext.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.awsomeman.javatext.JavaText;

public class SaveAs extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
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
		JFileChooser save = new JFileChooser("Save");
		FileFilter filter = new FileNameExtensionFilter("Text Files .txt", "txt");
		save.setFileFilter(filter);
		save.setVisible(true);
		
		int returnValue = save.showSaveDialog(JavaText.frame);
		File newFile = new File (save.getSelectedFile() + ".txt");
		if(returnValue == JFileChooser.APPROVE_OPTION) {
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
				
				String newTitle = "JavaText - " + fileName;
				JavaText.currentFile = fileName;
				
				JavaText.frame.setTitle(newTitle);
				SaveAs.setEnabled(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {}
}
