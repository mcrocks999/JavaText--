package com.awsomeman.javatext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

public class Help implements ActionListener {
	
	public static Action Help;
	
	public Help() {
		Help = new AbstractAction("Help") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(JavaText.frame, "JavaText - Version 1.0.0.2.1 \nMade By: 12AwsomeMan34");
			}
		};
	}

	public void actionPerformed(ActionEvent e) {}
}
