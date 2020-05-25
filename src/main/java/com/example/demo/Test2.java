package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Test2 {
	 public static void main(String[] args) {
			    File file = new File("C:\\Users\\tom\\Desktop\\temp.txt");
			    BufferedReader reader = null;
			    try {
			        reader = new BufferedReader(new FileReader(file));
			        String tempStr = null;
			        while ((tempStr = reader.readLine()) != null) {
			        	String substring = tempStr.substring(3, tempStr.length());
			        	System.out.println(substring);
			        }
			        reader.close();
			    } catch (IOException e) {
			        e.printStackTrace();
			    } finally {
			        if (reader != null) {
			            try {
			                reader.close();
			            } catch (IOException e1) {
			                e1.printStackTrace();
			            }
			        }
			    }
	 }
}
