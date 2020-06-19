package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.pojo.MethodInfo;
public class User {
	public void test() {
		new Runnable() {
			public void run() {
				System.out.println("123}");
			}
		};
		int a = 1;
		if(a == 2) {
			System.out.println("12}");
		}
		for(int i =1;i<2;i++) {
			System.out.println(i);
		}
	}
	public void test3() {
		if(2 == 2) {
			System.out.println("12}");
		}
	}
	public void test2(String a,Integer b) {
		System.out.println(a+b);
	}
	
	public static void main(String[] args) {
		String endWithMethodName = "[\\s\\S]*\\)\\W*"; // "public void test() "例如判断该字符串是否匹配正则。
		String readFileContent = readFileContent("C:\\Users\\tom\\Desktop\\User.java");
		// .java文件内容转为字符数组
		char[] charArray = braceToOther(readFileContent);
		readFileContent = String.valueOf(charArray);
		List<MethodInfo> mis = new ArrayList<>();
		// 得到方法名、参数类型、方法体，存放到集合中
		getBody2(charArray, readFileContent, endWithMethodName,mis);
		for (MethodInfo mi : mis) {
			System.out.println("==================");
		}
	}
	/**
	 * 获取方法名、参数、方法体
	 * @param charArray
	 * @param readFileContent
	 * @param endWithMethodName
	 */
	public static void  getBody2(char[] charArray,String readFileContent,String endWithMethodName,List<MethodInfo> mis) {
		String body = getBody(charArray);
		// 判断body是不是方法体
		String qian = readFileContent.substring(0, readFileContent.indexOf(body));
		if( qian.matches(endWithMethodName) ) {
			// 截取方法名称、参数类型
			String params = qian.substring(qian.lastIndexOf("(")+1, qian.lastIndexOf(")")); 
			params = params.trim();
			String substring = qian.substring(0, qian.lastIndexOf("(")).trim();
			String[] split = substring.split(" ");
			String methodName = split[split.length-1].trim();
			// System.out.println("方法体:"+body);
			MethodInfo mi = new MethodInfo();
			mi.setMethodName(methodName);
			mi.setParamType(params);
			String body3 = body.replaceAll("℃", "{");
			String body4 = body3.replaceAll("℉", "}");
			mi.setMethodBody(body4);
			// 存储
			mis.add(mi);
			// 移除该方法体，为了寻找后面的方法体。
			readFileContent = readFileContent.replace(body, "");
			getBody2(readFileContent.toCharArray(), readFileContent, endWithMethodName,mis);
		}else {
			if(!body.contains("{")) {
				return;
			}
			String body2 = body.substring(body.indexOf("{", 0)+1, body.lastIndexOf("}")); // 去掉最外层 {}
			getBody2(body2.toCharArray(), body, endWithMethodName,mis);
		}
	}
	/**
	 * 字符串中的 {	转为	℃ 	 }	转为	℉	，全部替换掉。
	 * @return 
	 */
	public static char[] braceToOther(String readFileContent) {
		char[] charArray = readFileContent.toCharArray();
		int startFor2yinhao = 0;
		int countFor2yinhao = 0;
		StringBuilder removeCharsFor2yinhao = new StringBuilder(); // 双引号
		int startFor1yinhao = 0;
		int countFor1yinhao = 0;
		StringBuilder removeCharsFor1yinhao = new StringBuilder(); // 单引号
		int startFor1hangzhushi = 0;
		StringBuilder removeCharsFor1hangzhushi = new StringBuilder(); // 单行注释
		int startFor9hangzhushi = 0;
		StringBuilder removeCharsFor9hangzhushi = new StringBuilder(); // 多行注释
		char specialChar1 = '℃';
		char specialChar2 = '℉';
		for(int i=0;i<charArray.length;i++) { // 每一个字符，都存入map中。
			char c = charArray[i];
			/*双引号*/
			if(c == '"') {
				startFor2yinhao = 1; // 开始收集
				countFor2yinhao+=1;
			}
			if(startFor2yinhao == 1) {
				removeCharsFor2yinhao.append(c);
				if(c == '{')charArray[i] = specialChar1; // ℃ 	替换	{
				if(c == '}')charArray[i] = specialChar2; // ℉	替换	}
				if(countFor2yinhao%2 == 0) {
					removeCharsFor2yinhao.append("@$@");
				}
			}
			if(c == '"' && countFor2yinhao%2 == 0) {
				startFor2yinhao = 0; // 停止收集
			}
			/*单引号*/
			if(c == '\'') {
				startFor1yinhao = 1; // 开始收集
				countFor1yinhao+=1;
			}
			if(startFor1yinhao == 1) {
				removeCharsFor1yinhao.append(c);
				if(c == '{')charArray[i] = specialChar1; // ℃ 	替换	{
				if(c == '}')charArray[i] = specialChar2; // ℉	替换	}
				if(countFor1yinhao%2 == 0) {
					removeCharsFor1yinhao.append("@$@");
				}
			}
			if(c == '\'' && countFor1yinhao%2 == 0) {
				startFor1yinhao = 0; // 停止收集
			}
			/*单行注释 // */
			if(c == '/') {
				if(c == charArray[i-1]) {
					startFor1hangzhushi = 1; // 开始收集
				}
			}
			if(startFor1hangzhushi == 1) {
				removeCharsFor1hangzhushi.append(c);
				if(c == '{')charArray[i] = specialChar1; // ℃ 	替换	{
				if(c == '}')charArray[i] = specialChar2; // ℉	替换	}
			}
			if(c == '\n') {
				startFor1hangzhushi = 0; // 停止收集
			}
			/*多行注释  removeCharsFor9hangzhushi */
			if(c == '/' && charArray[i+1] == '*') {
				startFor9hangzhushi = 1; // 开始收集
			}
			if(startFor9hangzhushi == 1) {
				removeCharsFor9hangzhushi.append(c);
				if(c == '{')charArray[i] = specialChar1; // ℃ 	替换	{
				if(c == '}')charArray[i] = specialChar2; // ℉	替换	}
			}
			if(i > 1 && charArray[i-1] == '*' && c == '/') {
				startFor9hangzhushi = 0; // 停止收集
				removeCharsFor9hangzhushi.append("@$@");
			}
		}
		return charArray;
	}
	/**
	 * 得到给定字符串的最外层 {} 内的内容，包括{}。如果有多个最外层{}，仅返回第一个
	 * @param charArray
	 * @return
	 */
	public static String getBody(char[] charArray) {
		StringBuilder sb = new StringBuilder();
		int body = 1; // 方法体开头时：body = 1。
		int count = 0;
		int start = 0;
		for(int i=0;i<charArray.length;i++) { // 再遍历一次字符数组
			char c = charArray[i];
			if(c == '{') {
				if(count == 0) { // 第一次遇到 ： {
					// 开始收集字符
					start = 1;
				}else { // 第二次及以上遇到： {
					body+=1;
				}
				count++;
			}
			if(c == '}') {
				body-=1;
			}
			if(start == 1)sb.append(c);
			if(body == 0) { // 收集到了一个完整的体
				start = 0;
			}
		}
		return sb.toString();
	}
	/**
	 * 	获取指定文件的内容
	 * @param fileName 文件的绝对路路径
	 * @return
	 */
	public static String readFileContent(String fileName) {
	    File file = new File(fileName);
	    BufferedReader reader = null;
	    StringBuffer sbf = new StringBuffer();
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempStr;
	        while ((tempStr = reader.readLine()) != null) {
	            sbf.append(tempStr+"\r\n");
	        }
	        reader.close();
	        return sbf.toString();
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
	    return sbf.toString();
	}
	class A{
		public void a() {
			System.out.println("内部类方法的方法体");
		}
	}
}


