package com.awsomeman.javatext.actions;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.awsomeman.javatext.functions.KeyShortcuts;

public class KeyShortcutSettings implements ActionListener {
	
	public static Action Settings;
	
	public KeyShortcutSettings() {
		Settings = new AbstractAction("Settings") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel(new GridLayout(0, 2));
				panel.add(new JLabel("Undo CTRL+"));
				JTextField undoShortcuttf = new JTextField(KeyShortcuts.currentUndo);
				panel.add(undoShortcuttf);
				panel.add(new JLabel("Redo CTRL+"));
				JTextField redoShortcuttf = new JTextField(KeyShortcuts.currentRedo);
				panel.add(redoShortcuttf);
				panel.add(new JLabel("Save CTRL+"));
				JTextField saveShortcuttf = new JTextField(KeyShortcuts.currentSave);
				panel.add(saveShortcuttf);
				panel.add(new JLabel("Save As CTRL+SHIFT+"));
				JTextField saveAsShortcuttf = new JTextField(KeyShortcuts.currentSaveAs);
				panel.add(saveAsShortcuttf);
				panel.add(new JLabel("Open CTRL+"));
				JTextField openShortcuttf = new JTextField(KeyShortcuts.currentOpen);
				panel.add(openShortcuttf);

				int result = JOptionPane.showConfirmDialog(null, panel, "Key Shortcut Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				switch (result) {
				    case JOptionPane.OK_OPTION:
				    	System.out.println(undoShortcuttf.getText());
				    	KeyShortcuts.setUndo(undoShortcuttf.getText());
				    	KeyShortcuts.setRedo(redoShortcuttf.getText());
				    	KeyShortcuts.setSave(saveShortcuttf.getText());
				    	KeyShortcuts.setSaveAs(saveAsShortcuttf.getText());
				    	KeyShortcuts.setOpen(openShortcuttf.getText());
				        break;
				    case JOptionPane.CANCEL_OPTION:
				        //...
				        break;
				}
			}
		};
	}
	
	public void actionPerformed(ActionEvent e) {}

	public static void loadSettings() {
		System.out.println("Loading settings...");
		File file = new File("settings.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			String fileName = file.getName();
			
			int pos = fileName.lastIndexOf(".");
			
			if (pos > 0) {
			    fileName = fileName.substring(0, pos);
			}
			
			reader.close();
		} catch (FileNotFoundException e1) {
			System.out.println("settings.txt not found, attempting to generate settings file.");
		} catch (IOException e2) {
			System.out.println("IOException. Settings will not be saved.");
		}
	}
}
