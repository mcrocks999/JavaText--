package com.awsomeman.javatext;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import com.awsomeman.javatext.actions.Exit;
import com.awsomeman.javatext.actions.Help;
import com.awsomeman.javatext.actions.Open;
import com.awsomeman.javatext.actions.Save;
import com.awsomeman.javatext.actions.SaveAs;
import com.awsomeman.javatext.actions.Settings;

public class JavaText extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static String currentFile = "Untitled";
	public static JTextArea textArea = new JTextArea();
	final UndoManager undo = new UndoManager();
    Document doc = textArea.getDocument();
	public static JavaText frame = new JavaText();
	public static JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
	public static boolean changed = false;
	
	private JPanel contentPane;
	
	Open o;
	Save s;
	SaveAs sa;
	Exit e;
	Settings set;
	Help h;
	
	public JavaText() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setTitle("JavaText - " + currentFile);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
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
		setContentPane(contentPane);
		
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		contentPane.add(textArea, BorderLayout.CENTER);

		StyleContext sc = new StyleContext();
		Style style = sc.addStyle("yourStyle", null);

		try {
			doc.insertString(doc.getLength(), "JavaText\n\nCreated by: 12AwsomeMan34 and MCRocks999", style);
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		doc.addUndoableEditListener(new UndoableEditListener() {
	        public void undoableEditHappened(UndoableEditEvent evt) {
	            undo.addEdit(evt.getEdit());
	        }
	    });
	    
	    textArea.getActionMap().put("Undo",
	        new AbstractAction("Undo") {
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent evt) {
	                try {
	                    if (undo.canUndo()) {
	                        undo.undo();
	                    }
	                } catch (CannotUndoException e) {
	                }
	            }
	       });
	    
	    textArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
	    
	    textArea.getActionMap().put("Redo",
	        new AbstractAction("Redo") {
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent evt) {
	                try {
	                    if (undo.canRedo()) {
	                        undo.redo();
	                    }
	                } catch (CannotRedoException e) {
	                }
	            }
	        });
	    
	    textArea.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
		
		JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane, BorderLayout.CENTER);
	}
	
	public void keyListener() {
		@SuppressWarnings("unused")
		KeyListener k = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				changed = true;
				Save.Save.setEnabled(true);
				SaveAs.SaveAs.setEnabled(true);
			}
		};
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Initializing...");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Initialized successfully.");
			}
		});
	}
}
