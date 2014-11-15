package com.awsomeman.javatext.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;

import com.awsomeman.javatext.JavaText;

public class Open implements ActionListener {
	
	public static Action Open;
	
	public Open() {
		Open = new AbstractAction("Open") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				try {
					Path path = Paths.get(JavaText.currentFilePath+"-autosave");
				    Files.deleteIfExists(path);
				} catch (NoSuchFileException x) {} catch (DirectoryNotEmptyException x) {} catch (IOException x) {}
				final JFileChooser fc = new JFileChooser();
				int returnValue = fc.showOpenDialog(JavaText.frame);
				
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					openFile(file);
				}
			}
		};
	}

	public static void openFile(File file) {
		BufferedReader br = null;
		String txtFile = "";
		
		try {
 
			String sCurrentLine;
 
			br = new BufferedReader(new FileReader(file));
 
			while ((sCurrentLine = br.readLine()) != null) {
				txtFile += sCurrentLine + "\n";
			}
 
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			String fileName = file.getName();
			JavaText.textArea.setText(txtFile);
			JavaText.frame.setTitle("JavaText - "+fileName);
			JavaText.currentFile = fileName;
			JavaText.currentFilePath = file.getPath();
		}
	}

	public void actionPerformed(ActionEvent e) {}
}