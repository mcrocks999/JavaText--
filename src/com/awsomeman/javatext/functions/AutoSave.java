package com.awsomeman.javatext.functions;

import com.awsomeman.javatext.JavaText;
import com.awsomeman.javatext.actions.Save;
import com.awsomeman.javatext.actions.Settings;

public class AutoSave extends Thread 
{
        public void run()
        {
	        while(true){
	        	if (Settings.autoSave=="true") {
		        	if (JavaText.currentFile!="Untitled") {
		        		Save.saveFile(JavaText.currentFile);
		        	}
	        	}
	        	try {
					sleep(Settings.autoSaveMS);
				} catch (InterruptedException e) {}
	        	System.out.println("test");
	        }
        }
}