package com.awsomeman.javatext;

import java.awt.BorderLayout;
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

import com.awsomeman.javatext.actions.Exit;
import com.awsomeman.javatext.actions.About;
import com.awsomeman.javatext.actions.Help;
import com.awsomeman.javatext.actions.KeyShortcutSettings;
import com.awsomeman.javatext.actions.New;
import com.awsomeman.javatext.actions.Open;
import com.awsomeman.javatext.actions.Save;
import com.awsomeman.javatext.actions.SaveAs;
import com.awsomeman.javatext.actions.Settings;
import com.awsomeman.javatext.actions.autosave.DisableAutosave;
import com.awsomeman.javatext.actions.autosave.EnableAutosave;
import com.awsomeman.javatext.actions.autosave.SetMS;
import com.awsomeman.javatext.functions.AutoSave;
import com.awsomeman.javatext.functions.KeyShortcuts;

public class JavaText extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static String currentFile = "Untitled";
	public static String currentFilePath = "";
	public static JTextArea textArea = new JTextArea();
	final static UndoManager undo = new UndoManager();
    Document doc = textArea.getDocument();
	public static JavaText frame = new JavaText();
	public static JFileChooser dialog = new JFileChooser(System.getProperty("user.dir"));
	public static boolean changed = false;
	
	private JPanel contentPane;

	New n;
	Open o;
	Save s;
	SaveAs sa;
	Exit e;
	Settings set;
	KeyShortcutSettings ksset;
	EnableAutosave ea;
	DisableAutosave da;
	SetMS sm;
	About a;
	Help h;
	
	public JavaText() {
		Settings.loadSettings();
		AutoSave AutoSaveThread = new AutoSave();
		AutoSaveThread.start();
		
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

		JMenuItem newMenuItem = new JMenuItem("New");
		JMenuItem openMenuItem = new JMenuItem("Open");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		JMenuItem saveAsMenuItem = new JMenuItem("Save As");
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		JMenuItem cutMenuItem = new JMenuItem(new DefaultEditorKit.CutAction());
		JMenuItem copyMenuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
		JMenuItem pasteMenuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
		JMenuItem optionsMenuItem = new JMenuItem("Options");
		JMenuItem keyShortcutSettingsMenuItem = new JMenuItem("Key Shortcut Settings");
		JMenuItem enableAutosaveMenuItem = new JMenuItem("Enable Autosave");
		JMenuItem disableAutosaveMenuItem = new JMenuItem("Disable Autosave");
		JMenuItem setMSAutosaveMenuItem = new JMenuItem("Set Autosave (ms)");
		JMenuItem aboutMenuItem = new JMenuItem("About");
		JMenuItem helpMenuItem = new JMenuItem("Help");
		JMenuItem seperatorMenuItem = new JMenuItem("------------------------------");
		JMenuItem seperator2MenuItem = new JMenuItem("----------");
		
		cutMenuItem.setText("Cut");
		copyMenuItem.setText("Copy");
		pasteMenuItem.setText("Paste");

		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.add(saveAsMenuItem);
		fileMenu.add(exitMenuItem);
		
		editMenu.add(cutMenuItem);
		editMenu.add(copyMenuItem);
		editMenu.add(pasteMenuItem);
		
		settingsMenu.add(optionsMenuItem);
		settingsMenu.add(keyShortcutSettingsMenuItem);
		settingsMenu.add(seperatorMenuItem);
		settingsMenu.add(enableAutosaveMenuItem);
		settingsMenu.add(disableAutosaveMenuItem);
		settingsMenu.add(setMSAutosaveMenuItem);
		
		helpMenu.add(helpMenuItem);
		helpMenu.add(seperator2MenuItem);
		helpMenu.add(aboutMenuItem);

		n = new New();
		o = new Open();
		s = new Save();
		sa = new SaveAs();
		e = new Exit();
		set = new Settings();
		ksset = new KeyShortcutSettings();
		ea = new EnableAutosave();
		da = new DisableAutosave();
		sm = new SetMS();
		a = new About();
		h = new Help();
		newMenuItem.addActionListener(New.New);
		openMenuItem.addActionListener(Open.Open);
		saveMenuItem.addActionListener(Save.Save);
		saveAsMenuItem.addActionListener(SaveAs.SaveAs);
		exitMenuItem.addActionListener(Exit.Exit);
		optionsMenuItem.addActionListener(Settings.Settings);
		keyShortcutSettingsMenuItem.addActionListener(KeyShortcutSettings.Settings);
		enableAutosaveMenuItem.addActionListener(EnableAutosave.EnableAutosave);
		disableAutosaveMenuItem.addActionListener(DisableAutosave.DisableAutosave);
		setMSAutosaveMenuItem.addActionListener(SetMS.SetMS);
		aboutMenuItem.addActionListener(About.Help);
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
			e1.printStackTrace();
		}
		
		doc.addUndoableEditListener(new UndoableEditListener() {
	        public void undoableEditHappened(UndoableEditEvent evt) {
	            undo.addEdit(evt.getEdit());
	        }
	    });
	    
		setupKeyFunctions();
		KeyShortcuts.setDefaultBindings();
		
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
	
	public static void setupKeyFunctions() {
		System.out.println("'ello");
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
		textArea.getActionMap().put("Save",Save.Save);
		textArea.getActionMap().put("SaveAs",SaveAs.SaveAs);
		textArea.getActionMap().put("Open",Open.Open);
	}
}
