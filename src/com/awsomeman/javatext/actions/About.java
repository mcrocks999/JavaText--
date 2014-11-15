package com.awsomeman.javatext.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import com.awsomeman.javatext.JavaText;

public class About implements ActionListener {
	
	public static Action Help;
	
	public About() {
		Help = new AbstractAction("Help") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(JavaText.frame, "JavaText - Version 1.1 \n\nDeveloped By:\n12AwsomeMan34\nand\nMCRocks999");
			}
		};
	}

	public void actionPerformed(ActionEvent e) {}
}
