package com.awsomeman.javatext.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import com.awsomeman.javatext.JavaText;

public class New implements ActionListener {
	
	public static Action New;
	
	public New() {
		New = new AbstractAction("New") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				JavaText.currentFile = "Untitled";
				JavaText.textArea.setText("");
			}
		};
	}

	public void actionPerformed(ActionEvent e) {}
}
