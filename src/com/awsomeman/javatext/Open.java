package com.awsomeman.javatext;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;

public class Open implements ActionListener {
	
	public static Action Open;
	
	public Open() {
		Open = new AbstractAction("Open") {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnValue = fc.showOpenDialog(JavaText.frame);
				
				if(returnValue == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					try {
						BufferedReader reader = new BufferedReader(new FileReader(file));
						String line = reader.readLine();
						
						new NewJFrame();
						NewJFrame.textArea.setText(line);
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
