package com.awsomeman.javatext.language;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class LanguageManager extends Thread{
	public static ArrayList<String> languagePaths = new ArrayList<String>();
	
	public void run() {
		while(true){
			File f = new File("./languages"); // current directory

		    File[] files = f.listFiles();
		    for (File file : files) {
		    	String fileName = file.getName();
		    	if (fileName.toLowerCase().endsWith(".lng")){
		    		try {
						if (fileName.toLowerCase()=="english.lng"||fileName.toLowerCase()=="polish.lng") {
							languagePaths.add(file.getCanonicalPath());
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	}
		    }
			 try {
				sleep(5000);
			 } catch (InterruptedException e) {}
		}
	}
}