package com.example.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodInfoUtil {
	private static boolean quoteFlag = false, lineStarFlag = false;
	private MethodInfoUtil() {
	}
	public static void main(String[] args) {
		String fileContent = readFileContent("C:\\Users\\tom\\Desktop\\User.java");
		List<MethodInfo> methodInfos = MethodInfoUtil.getMethodInfo(fileContent);
		for (MethodInfo mi : methodInfos) {
			System.out.println(mi.getMethodBody());
			System.out.println("=========");
		}
//		System.out.println(fileContent);
	}
	public static List<MethodInfo> getMethodInfo(String fileContent) {
		// "public void test() "例如判断该字符串是否匹配正则。
		String endWithMethodName = "[\\s\\S]*\\)\\W*";
		// .java文件内容转为字符数组
		List<MethodInfo> mis = new ArrayList<>();
		// 得到方法名、参数类型、方法体，存放到集合中
		char[] charArray = fileContent.toCharArray();
		getBody2(charArray, fileContent, endWithMethodName,mis);
		return mis;
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
		if( qian.matches(endWithMethodName) || qian.matches( "[\\s\\S]*\\)\\W*throws[\\s\\S]*\\W*") ){
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
			if(body == 0) { // 收集到了一个完整的体(类体、方法体)
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
	            // sbf.append(tempStr+"\r\n");
	        	sbf.append(analysisLine(tempStr)); // 删除所有注释，原代码的所处的行号不变
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
	
	 /**
	  *  	分析 一行代码 是否有注释  
	  * @param code
	  * @return
	  */
    public static StringBuffer analysisLine(String code){
        StringBuffer sb = new StringBuffer();  
        if(code == null)  
            return sb;  
        for(int i = 0; i < code.length(); i++){
            if(lineStarFlag){// 斜线星（/*） 开始了（包含了 /** ）  
                if(i + 1 < code.length() && code.charAt(i) == '*' && code.charAt(i + 1) == '/'){  
                    lineStarFlag = false;  
                    i ++;  
                    continue;  
                }  
            }else if (!quoteFlag) {// 先判断是否有 双引号  
                if(code.charAt(i) == '"' // 双引号 开始了
                		// lz 特殊情况：如果有一行含有：char c = '"'; 类似这种半边双引号，会产生bug，后面的所有源码都会被认为是字符串
                		&& !code.matches("[\\s\\S]*'\"'[\\s\\S]*")){
                    sb.append(code.charAt(i));  
                    quoteFlag = true;  
                    continue;  
                }else{ // 不是 双引号   
                    if(i + 1 < code.length() && code.charAt(i) == '/'){  
                        if(code.charAt(i + 1) == '*'){// 以 /* 开始的注释代码  
                            lineStarFlag = true;  
                            i ++;  
                            continue;  
                        }else if(code.charAt(i + 1) == '/'){// 以 // 开始的注释代码  
                            i = code.length();// 直接 行结束了  
                        }else{  
                            sb.append(code.charAt(i));// 其他情况   
                        }  
                    }else{  
                        sb.append(code.charAt(i));  
                    }  
                }  
            }else{  
                if ( (code.charAt(i) == '"' && code.charAt(i - 1) != '\\')
                		|| (code.charAt(i) == '"' && code.charAt(i - 1) == '\\' && code.charAt(i - 2) == '\\')  ) { // 双引号结束了  
                    sb.append(code.charAt(i));  
                    quoteFlag = false;  
                }else{
                    // 双引号开始了 但是没有结束  
                	// lz 字符串中的 {	转为	℃ 	 }	转为	℉
                	if(code.charAt(i) == '{') {
                		char[] charArray = code.toCharArray();
                		charArray[i] = '℃';
                		code = String.valueOf(charArray);
                	}else if(code.charAt(i) == '}') {
                		char[] charArray = code.toCharArray();
                		charArray[i] = '℉';
                		code = String.valueOf(charArray);
                	}
                	
                    sb.append(code.charAt(i));  
                }  
            }  
        }  
//      if(sb.length() != 0) // 如果 为空串，则 不添加换行  
            sb.append("\n");  
        return sb;  
    } 
}


