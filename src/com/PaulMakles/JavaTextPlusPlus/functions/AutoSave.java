package com.PaulMakles.JavaTextPlusPlus.functions;

import com.PaulMakles.JavaTextPlusPlus.JavaText;
import com.PaulMakles.JavaTextPlusPlus.actions.Save;
import com.PaulMakles.JavaTextPlusPlus.actions.Settings;

public class AutoSave extends Thread 
{
        public void run()
        {
	        while(true){
	        	if (Settings.autoSave=="true") {
	        		System.out.println("test2");
		        	if (JavaText.currentFile!="Untitled") {
		        		Save.saveFile(JavaText.currentFilePath+"-autosave","", false);
		        	}
	        	}
	        	try {
					sleep(Settings.autoSaveMS);
				} catch (InterruptedException e) {}
	        	System.out.println("test");
	        }
        }
}