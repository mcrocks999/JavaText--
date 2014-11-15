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
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.Document;
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
import com.awsomeman.javatext.actions.fontModifiers.ModifyFontSize;
import com.awsomeman.javatext.actions.fontModifiers.ModifyFontface;
import com.awsomeman.javatext.functions.AutoSave;
import com.awsomeman.javatext.language.LanguageManager;

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
	ModifyFontface ff;
	ModifyFontSize fs;
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
		LanguageManager LngMgrThread = new LanguageManager();
		LngMgrThread.start();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setTitle("JavaText - " + currentFile);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu("Edit");
		JMenu fontMenu = new JMenu("Font");
		JMenu fontSizeMenu = new JMenu("Change Size");
		JMenu fontTypefaceMenu = new JMenu("Set Font");
		JMenu settingsMenu = new JMenu("Settings");
		JMenu settingsAutosaveMenu = new JMenu("Autosave");
		JMenu helpMenu = new JMenu("Help");
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(fontMenu);
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
		JMenuItem enlargeMenuItem = new JMenuItem("Enlarge by 1");
		JMenuItem enlargeFiveMenuItem = new JMenuItem("Enlarge by 5");
		JMenuItem enlargeTenMenuItem = new JMenuItem("Enlarge by 10");
		JMenuItem shrinkMenuItem = new JMenuItem("Shrink by 1");
		JMenuItem shrinkFiveMenuItem = new JMenuItem("Shrink by 5");
		JMenuItem shrinkTenMenuItem = new JMenuItem("Shrink by 10");
		JMenuItem setArialMenuItem = new JMenuItem("Arial");
		JMenuItem setComicMenuItem = new JMenuItem("Comic Sans MS");
		JMenuItem setTimesMenuItem = new JMenuItem("Times New Roman");
		JMenuItem optionsMenuItem = new JMenuItem("Options");
		JMenuItem keyShortcutSettingsMenuItem = new JMenuItem("Key Shortcut Settings");
		JMenuItem openSettingsFileMenuItem = new JMenuItem("Open Settings File");
		JMenuItem openKeyShortcutsFileMenuItem = new JMenuItem("Open Key Shortcuts File");
		JMenuItem enableAutosaveMenuItem = new JMenuItem("Enable Autosave");
		JMenuItem disableAutosaveMenuItem = new JMenuItem("Disable Autosave");
		JMenuItem setMSAutosaveMenuItem = new JMenuItem("Set Autosave (ms)");
		JMenuItem aboutMenuItem = new JMenuItem("About");
		JMenuItem helpMenuItem = new JMenuItem("Help");
		JMenuItem seperatorMenuItem = new JMenuItem("------------------------------");
		JMenuItem seperator2MenuItem = new JMenuItem("------------------------------");
		JMenuItem seperator3MenuItem = new JMenuItem("----------");
		
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
		
		fontMenu.add(fontTypefaceMenu);
		fontMenu.add(fontSizeMenu);

		fontTypefaceMenu.add(setArialMenuItem);
		fontTypefaceMenu.add(setComicMenuItem);
		fontTypefaceMenu.add(setTimesMenuItem);
		
		fontSizeMenu.add(enlargeMenuItem);
		fontSizeMenu.add(enlargeFiveMenuItem);
		fontSizeMenu.add(enlargeTenMenuItem);
		fontSizeMenu.add(shrinkMenuItem);
		fontSizeMenu.add(shrinkFiveMenuItem);
		fontSizeMenu.add(shrinkTenMenuItem);
		
		settingsMenu.add(optionsMenuItem);
		settingsMenu.add(keyShortcutSettingsMenuItem);
		settingsMenu.add(seperatorMenuItem);
		settingsMenu.add(openSettingsFileMenuItem);
		settingsMenu.add(openKeyShortcutsFileMenuItem);
		settingsMenu.add(seperator2MenuItem);
		settingsMenu.add(settingsAutosaveMenu);
		
		settingsAutosaveMenu.add(enableAutosaveMenuItem);
		settingsAutosaveMenu.add(disableAutosaveMenuItem);
		settingsAutosaveMenu.add(setMSAutosaveMenuItem);
		
		helpMenu.add(helpMenuItem);
		helpMenu.add(seperator3MenuItem);
		helpMenu.add(aboutMenuItem);

		n = new New();
		o = new Open();
		s = new Save();
		sa = new SaveAs();
		e = new Exit();
		ff = new ModifyFontface();
		fs = new ModifyFontSize();
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
		
		setArialMenuItem.addActionListener(ModifyFontface.setArial);
		setComicMenuItem.addActionListener(ModifyFontface.setComicSans);
		setTimesMenuItem.addActionListener(ModifyFontface.setTimesNewRoman);

		enlargeMenuItem.addActionListener(ModifyFontSize.enlarge);
		enlargeFiveMenuItem.addActionListener(ModifyFontSize.enlargeByFive);
		enlargeTenMenuItem.addActionListener(ModifyFontSize.enlargeByTen);
		shrinkMenuItem.addActionListener(ModifyFontSize.shrink);
		shrinkFiveMenuItem.addActionListener(ModifyFontSize.shrinkByFive);
		shrinkTenMenuItem.addActionListener(ModifyFontSize.shrinkByTen);
		
		optionsMenuItem.addActionListener(Settings.Settings);
		keyShortcutSettingsMenuItem.addActionListener(KeyShortcutSettings.Settings);
		openSettingsFileMenuItem.addActionListener(Settings.OpenSettingsFile);
		openKeyShortcutsFileMenuItem.addActionListener(KeyShortcutSettings.OpenSettingsFile);
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
		textArea.setFont(new Font(Settings.fontTypeface, Font.PLAIN, Settings.fontSize));
		contentPane.add(textArea, BorderLayout.CENTER);
		String tmp1 = "JavaText\n\n";
		String tmp2;
		if (!Settings.userName.isEmpty()) {
			tmp2 = "Welcome back, "+Settings.userName+"!";
		}else{
			tmp2 = "Created by: 12AwsomeMan34 and MCRocks999";
		}
		String tmp3 = tmp1+tmp2;
		textArea.setText(tmp3);
		
		doc.addUndoableEditListener(new UndoableEditListener() {
	        public void undoableEditHappened(UndoableEditEvent evt) {
	            undo.addEdit(evt.getEdit());
	        }
	    });
		
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
					setupKeyFunctions();
					KeyShortcutSettings.loadSettings();
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Initialized successfully.");
			}
		});
	}
	
	public static void setupKeyFunctions() {
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
