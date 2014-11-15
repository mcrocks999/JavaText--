package com.awsomeman.javatext.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
					try {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line = reader.readLine();
						
						String fileName = file.getName();
						
						int pos = fileName.lastIndexOf(".");
						
						if (pos > 0) {
						    fileName = fileName.substring(0, pos);
						}
						
						String newTitle = "JavaText - " + fileName;
						
						JavaText.textArea.setText(line);
						JavaText.frame.setTitle(newTitle);
						JavaText.currentFile = fileName;
						reader.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		};
	}

	public void actionPerformed(ActionEvent e) {}
}
