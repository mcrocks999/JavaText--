package com.PaulMakles.JavaText.functions;

import com.PaulMakles.JavaText.JavaText;
import com.PaulMakles.JavaText.actions.Save;
import com.PaulMakles.JavaText.actions.Settings;

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