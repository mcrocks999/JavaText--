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
import com.awsomeman.javatext.language.LanguageActions;
import com.awsomeman.javatext.language.LanguageManager;
import com.awsomeman.javatext.language.LanguageParser;

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
	LanguageActions la;
	
	public JavaText() {
		Settings.loadSettings();
		LanguageManager.getLanguages();
		LanguageParser.ParseAndSet(LanguageManager.loadLanguage(LanguageManager.getFirstLanguage()));
		
		AutoSave AutoSaveThread = new AutoSave();
		AutoSaveThread.start();
		LanguageManager LngMgrThread = new LanguageManager();
		LngMgrThread.start();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		currentFile = LanguageParser.getWords(2);
		setTitle("JavaText - " + currentFile);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu(LanguageParser.getWords(3));
		JMenu editMenu = new JMenu(LanguageParser.getWords(4));
		JMenu fontMenu = new JMenu(LanguageParser.getWords(5));
		JMenu fontSizeMenu = new JMenu(LanguageParser.getWords(6));
		JMenu fontTypefaceMenu = new JMenu(LanguageParser.getWords(7));
		JMenu settingsMenu = new JMenu(LanguageParser.getWords(8));
		JMenu settingsAutosaveMenu = new JMenu(LanguageParser.getWords(9));
		JMenu helpMenu = new JMenu(LanguageParser.getWords(10));
		JMenu languageMenu = new JMenu("Language/Jenzyk");
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(fontMenu);
		menuBar.add(settingsMenu);
		menuBar.add(helpMenu);

		JMenuItem newMenuItem = new JMenuItem(LanguageParser.getWords(11));
		JMenuItem openMenuItem = new JMenuItem(LanguageParser.getWords(12));
		JMenuItem saveMenuItem = new JMenuItem(LanguageParser.getWords(13));
		JMenuItem saveAsMenuItem = new JMenuItem(LanguageParser.getWords(14));
		JMenuItem exitMenuItem = new JMenuItem(LanguageParser.getWords(15));
		JMenuItem cutMenuItem = new JMenuItem(new DefaultEditorKit.CutAction());
		JMenuItem copyMenuItem = new JMenuItem(new DefaultEditorKit.CopyAction());
		JMenuItem pasteMenuItem = new JMenuItem(new DefaultEditorKit.PasteAction());
		JMenuItem enlargeMenuItem = new JMenuItem(LanguageParser.getWords(19));
		JMenuItem enlargeFiveMenuItem = new JMenuItem(LanguageParser.getWords(20));
		JMenuItem enlargeTenMenuItem = new JMenuItem(LanguageParser.getWords(21));
		JMenuItem shrinkMenuItem = new JMenuItem(LanguageParser.getWords(22));
		JMenuItem shrinkFiveMenuItem = new JMenuItem(LanguageParser.getWords(23));
		JMenuItem shrinkTenMenuItem = new JMenuItem(LanguageParser.getWords(24));
		JMenuItem setArialMenuItem = new JMenuItem("Arial");
		JMenuItem setComicMenuItem = new JMenuItem("Comic Sans MS");
		JMenuItem setTimesMenuItem = new JMenuItem("Times New Roman");
		JMenuItem optionsMenuItem = new JMenuItem(LanguageParser.getWords(25));
		JMenuItem keyShortcutSettingsMenuItem = new JMenuItem(LanguageParser.getWords(26));
		JMenuItem openSettingsFileMenuItem = new JMenuItem(LanguageParser.getWords(27));
		JMenuItem openKeyShortcutsFileMenuItem = new JMenuItem(LanguageParser.getWords(28));
		JMenuItem enableAutosaveMenuItem = new JMenuItem(LanguageParser.getWords(29));
		JMenuItem disableAutosaveMenuItem = new JMenuItem(LanguageParser.getWords(30));
		JMenuItem setMSAutosaveMenuItem = new JMenuItem(LanguageParser.getWords(31));
		JMenuItem aboutMenuItem = new JMenuItem(LanguageParser.getWords(32));
		JMenuItem helpMenuItem = new JMenuItem(LanguageParser.getWords(33));
		JMenuItem setPolishMenuItem = new JMenuItem("Zmien na Polski");
		JMenuItem setEnglishMenuItem = new JMenuItem("Change to English");
		JMenuItem seperatorMenuItem = new JMenuItem("------------------------------");
		JMenuItem seperator2MenuItem = new JMenuItem("------------------------------");
		JMenuItem seperator3MenuItem = new JMenuItem("----------");
		
		cutMenuItem.setText(LanguageParser.getWords(16));
		copyMenuItem.setText(LanguageParser.getWords(17));
		pasteMenuItem.setText(LanguageParser.getWords(18));

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
		helpMenu.add(languageMenu);
		helpMenu.add(seperator3MenuItem);
		helpMenu.add(aboutMenuItem);
		
		languageMenu.add(setPolishMenuItem);
		languageMenu.add(setEnglishMenuItem);

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
		la = new LanguageActions();
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
		setEnglishMenuItem.addActionListener(LanguageActions.setEnglish);
		setPolishMenuItem.addActionListener(LanguageActions.setPolish);
		
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
			tmp2 = LanguageParser.getWords(34)+" "+Settings.userName+"!";
		}else{
			tmp2 = LanguageParser.getWords(35);
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
