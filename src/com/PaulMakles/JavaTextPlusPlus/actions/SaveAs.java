package com.PaulMakles.JavaTextPlusPlus.actions;

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

import com.PaulMakles.JavaTextPlusPlus.JavaText;
import com.PaulMakles.JavaTextPlusPlus.language.LanguageParser;

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
		FileFilter filter;
		if (Settings.fileFormat==".txt") {
			filter = new FileNameExtensionFilter(LanguageParser.getWords(37), "txt");
		}else if(Settings.fileFormat==".html"){
			filter = new FileNameExtensionFilter(LanguageParser.getWords(38), "html");
		}else if(Settings.fileFormat.isEmpty()){
			filter = new FileNameExtensionFilter(LanguageParser.getWords(39), "");
		}else{
			filter = new FileNameExtensionFilter(LanguageParser.getWords(40)+" "+Settings.fileFormat, Settings.fileFormat);
		}
		save.setFileFilter(filter);
		save.setVisible(true);
		
		int returnValue = save.showSaveDialog(JavaText.frame);
		File newFile = new File (save.getSelectedFile() + Settings.fileFormat);
		if (save.getSelectedFile().getName().endsWith(Settings.fileFormat)) {
			newFile = new File (save.getSelectedFile() + "");
		}
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
				
				Save.saveFile(newFile.getPath(), fileName, true);
				SaveAs.setEnabled(false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void actionPerformed(ActionEvent e) {}
}
