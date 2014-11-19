package com.PaulMakles.JavaText.actions.autosave;

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

import com.PaulMakles.JavaText.JavaText;
import com.PaulMakles.JavaText.actions.Settings;

public class DisableAutosave implements ActionListener {
	
	public static Action DisableAutosave;
	
	public DisableAutosave() {
		DisableAutosave = new AbstractAction("DisableAutosave") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				Settings.autoSave = "false";
				try {
					Path path = Paths.get(JavaText.currentFilePath+"-autosave");
				    Files.deleteIfExists(path);
				} catch (NoSuchFileException x) {} catch (DirectoryNotEmptyException x) {} catch (IOException x) {}
			}
		};
	}

	public void actionPerformed(ActionEvent e) {}
}
