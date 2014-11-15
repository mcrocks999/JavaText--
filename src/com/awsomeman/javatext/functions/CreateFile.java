package com.awsomeman.javatext.functions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateFile {
	public static void createFile(String filePath, String fileContents) {
		File newFile = new File (filePath);
		try {
			newFile.createNewFile();
			FileWriter fw = new FileWriter(newFile.getAbsoluteFile(), true);
			fw.write(fileContents);
			fw.close();
		} catch (IOException e) {}
	}
}