package com.awsomeman.javatext.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class Exit implements ActionListener {
	
	public static Action Exit;
	
	public Exit() {
		Exit = new AbstractAction("Exit") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				System.out.println("Exiting...");
				System.exit(0);
			}
		};
	}

	public void actionPerformed(ActionEvent e) {}
}
