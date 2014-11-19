package com.PaulMakles.JavaText.actions;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultEditorKit;

import com.PaulMakles.JavaText.JavaText;

public class NewJFrame {
	
	public static JFrame frame = new JFrame();
	public static JTextArea textArea = new JTextArea();
	
	private JPanel contentPane;
	
	Open o;
	Save s;
	SaveAs sa;
	Exit e;
	Settings set;
	Help h;
	
	public NewJFrame() {
		frame.setBounds(100, 100, 500, 500);
		frame.setTitle("JavaText - " + JavaText.currentFile);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu settingsMenu = new JMenu("Settings");
		JMenu helpMenu = new JMenu("Help");
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(settingsMenu);
		menuBar.add(helpMenu);
		
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem saveAsMenuItem = new JMenuItem("Save As");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		JMenuItem cutMenuItem = new JMenuItem(new DefaultEditorKit.CutAction());
		JMenuItem copyMenuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
		JMenuItem pasteMenuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
		JMenuItem optionsMenuItem = new JMenuItem("Options");
		JMenuItem helpMenuItem = new JMenuItem("Help");
		
		cutMenuItem.setText("Cut");
		copyMenuItem.setText("Copy");
		pasteMenuItem.setText("Paste");
		
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(exitMenuItem);
		
		editMenu.add(cutMenuItem);
		editMenu.add(copyMenuItem);
		editMenu.add(pasteMenuItem);
		
		settingsMenu.add(optionsMenuItem);
		
		helpMenu.add(helpMenuItem);
		
		o = new Open();
		s = new Save();
		sa = new SaveAs();
		e = new Exit();
		set = new Settings();
		h = new Help();
		openMenuItem.addActionListener(Open.Open);
		saveMenuItem.addActionListener(Save.Save);
		saveAsMenuItem.addActionListener(SaveAs.SaveAs);
		exitMenuItem.addActionListener(Exit.Exit);
		optionsMenuItem.addActionListener(Settings.Settings);
		helpMenuItem.addActionListener(Help.Help);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(textArea, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
}
