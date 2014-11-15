package com.awsomeman.javatext.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.AbstractAction;
import javax.swing.Action;
import com.awsomeman.javatext.JavaText;

public class New implements ActionListener {
	
	public static Action New;
	
	public New() {
		New = new AbstractAction("New") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				try {
					Path path = Paths.get(JavaText.currentFilePath+"-autosave");
				    Files.deleteIfExists(path);
				} catch (NoSuchFileException x) {} catch (DirectoryNotEmptyException x) {} catch (IOException x) {}
				JavaText.currentFile = "Untitled";
				JavaText.frame.setTitle("JavaText - Untitled");
				JavaText.textArea.setText("");
				JavaText.changed = false;
				Save.Save.setEnabled(false);
				SaveAs.SaveAs.setEnabled(false);
			}
		};
	}

	public void actionPerformed(ActionEvent e) {}
}