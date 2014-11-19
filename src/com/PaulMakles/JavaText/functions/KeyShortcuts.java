package com.PaulMakles.JavaText.functions;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.PaulMakles.JavaText.JavaText;

public class KeyShortcuts {
	
	public static String currentUndo = "Z";
	public static String currentRedo = "Y";
	public static String currentSave = "S";
	public static String currentSaveAs = "S";
	public static String currentOpen = "O";
	
	public static void setUndo(String selectedKey) {
	    JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
		try {
			if (selectedKey.length()==1) {
				JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control "+selectedKey.toUpperCase()), "Undo");
			}else{
				JPanel panel = new JPanel(new GridLayout(0, 1));
	    		panel.add(new JLabel("Cannot set undo shortcut"));
	    		JOptionPane.showConfirmDialog(null, panel, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
		}catch (Exception e){
			JPanel panel = new JPanel(new GridLayout(0, 1));
    		panel.add(new JLabel("Cannot set undo shortcut"));
    		JOptionPane.showConfirmDialog(null, panel, "Error: Exception occoured", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
	}

	public static void setRedo(String selectedKey) {
		JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
		try {
			if (selectedKey.length()==1) {
				JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control "+selectedKey.toUpperCase()), "Redo");
			}else{
				JPanel panel = new JPanel(new GridLayout(0, 1));
	    		panel.add(new JLabel("Cannot set redo shortcut"));
	    		JOptionPane.showConfirmDialog(null, panel, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
		}catch (Exception e){
			JPanel panel = new JPanel(new GridLayout(0, 1));
    		panel.add(new JLabel("Cannot set redo shortcut"));
    		JOptionPane.showConfirmDialog(null, panel, "Error: Exception occoured", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void setSave(String selectedKey) {
		JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control S"), "Save");
		try {
			if (selectedKey.length()==1) {
				JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control "+selectedKey.toUpperCase()), "Save");
			}else{
				JPanel panel = new JPanel(new GridLayout(0, 1));
	    		panel.add(new JLabel("Cannot set save shortcut"));
	    		JOptionPane.showConfirmDialog(null, panel, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
		}catch (Exception e){
			JPanel panel = new JPanel(new GridLayout(0, 1));
    		panel.add(new JLabel("Cannot set save shortcut"));
    		JOptionPane.showConfirmDialog(null, panel, "Error: Exception occoured", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void setSaveAs(String selectedKey) {
		JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control shift S"), "SaveAs");
		try {
			if (selectedKey.length()==1) {
				JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control shift "+selectedKey.toUpperCase()), "SaveAs");
			}else{
				JPanel panel = new JPanel(new GridLayout(0, 1));
	    		panel.add(new JLabel("Cannot set save as shortcut"));
	    		JOptionPane.showConfirmDialog(null, panel, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
		}catch (Exception e){
			JPanel panel = new JPanel(new GridLayout(0, 1));
    		panel.add(new JLabel("Cannot set save as shortcut"));
    		JOptionPane.showConfirmDialog(null, panel, "Error: Exception occoured", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void setOpen(String selectedKey) {
		JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control O"), "Open");
		try {
			if (selectedKey.length()==1) {
				JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control "+selectedKey.toUpperCase()), "Open");
			}else{
				JPanel panel = new JPanel(new GridLayout(0, 1));
	    		panel.add(new JLabel("Cannot set open shortcut"));
	    		JOptionPane.showConfirmDialog(null, panel, "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
			}
		}catch (Exception e){
			JPanel panel = new JPanel(new GridLayout(0, 1));
    		panel.add(new JLabel("Cannot set open shortcut"));
    		JOptionPane.showConfirmDialog(null, panel, "Error: Exception occoured", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void setDefaultBindings() {
	    JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
	    JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
		JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control S"), "Save");
		JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control shift S"), "SaveAs");
		JavaText.textArea.getInputMap().put(KeyStroke.getKeyStroke("control O"), "Open");
	}
}
