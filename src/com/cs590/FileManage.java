package com.project2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileManage {
	public FileManage(){
		
	}
	// Read file
	public void inputFile() {
		File file = new File("infile.dat");  
        BufferedReader reader = null;  
        String str = "";
        try {  
            reader = new BufferedReader(new FileReader(file));  
            String tempString = null;  
            while ((tempString = reader.readLine()) != null) {  
            	  str = str.concat(valid(tempString));
            }  
            reader.close(); 
            Huffman h = new Huffman(str);
	     } 
		catch (IOException e) {  
	            e.printStackTrace();  
	            return;  
	    }
	}
	
	// Output file
	public void outputFile(String s) {
		File file = new File("outfile.dat"); 
        if (file.exists()) {                   
            System.out.println("Creating Error! The file has already exsited");  
            return;              
        }  
        try {  
            if (file.createNewFile()) {
                java.io.OutputStream out = new FileOutputStream(file);  
                out.write(s.getBytes("utf-8"));  
                out.close();  
                return;
            } else {  
                System.out.println("Creating Error!");  
                return;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("Creating Error!" + e.getMessage());  
            return;  
        }  
		
	}
	// Ignore all blanks, all punctuation marks, all special symbols in file.
	private String valid(String s) {
		String str = "";
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9' ||s.charAt(i) >= 'a' && s.charAt(i) <= 'z' || s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
				str += s.charAt(i);
			}
		}
		return str;
	}
}
