package com.example.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * 	该工具类使用说明：
 * 	1、readFileContent(fileName) // 获得指定文件的内容
 * 	2、getMethodInfo(content) // 入参是文件内容，返回方法名、参数、方法体
 * @author tom
 *
 */
public class MethodInfoUtil {
	private static boolean quoteFlag = false, lineStarFlag = false;
	private MethodInfoUtil() {
	}
	public static void main(String[] args) {
//		String fileContent = readFileContent("C:\\Users\\tom\\Desktop\\User.java");
//		List<MethodInfo> methodInfos = MethodInfoUtil.getMethodInfo(fileContent);
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
    /**
	 * 	根据方法体变更分析结果，查看是否变更
	 * @param changeAnalyse
	 * @return 变更了返回true，否则返回false
	 */
	public static boolean changeOrNot(String changeAnalyse) {
		String[] split = changeAnalyse.split("\n");
		for (String s : split) {
			if(s.startsWith("+") || s.startsWith("-") ) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 	返回两个方法体的对比结果。
	 * @param methodBody	主分支
	 * @param methodBody2	测试分支
	 * @return 说明：+增加的一行，-减少的一行。没有+-则改行未改变。整个返回结果中行头都没有+-，则方法体未改变。
	 */
	public static String changeAnalyse(String methodBody,String methodBody2) {
		// 根据换行符切割字符串
		String[] split = methodBody.split("\n");
		StringBuilder sb = new StringBuilder();
		for (String s : split) {
			if(!s.matches("\\s*")) { // 匹配多个空字符
				sb.append(s+"\n"); // 字符串中有换行符，打印时才会看到换行效果
			}
		}
		methodBody = sb.toString();
		// 根据换行符切割字符串
		String[] split2 = methodBody2.split("\n");
		StringBuilder sb2 = new StringBuilder();
		for (String s : split2) {
			if(!s.matches("\\s*")) { // 匹配多个空字符，排除空行
				sb2.append(s+"\n");
			}
		}
		methodBody2 = sb2.toString();
		// 比较差异，尝试按行比较
		/** 先比较相同的行，记录行号。
		 */
		Map<Integer,String> masterMap = new HashMap<>();
		Map<Integer,String> testMap = new HashMap<>();
		Map<Integer,String> change = new HashMap<>();
		String[] split3 = methodBody.split("\n");
		String[] split4 = methodBody2.split("\n");
		for (int i=0;i<split3.length;i++) {
			String master = split3[i].trim();
			masterMap.put(i, master);
		}
		for (int j=0;j<split4.length;j++) {
			String test = split4[j].trim();
			testMap.put(j, test);
		}
		// 如果主分支拥有测试分支改行代码：没改变。 不拥有：新增
		for (int j=0;j<split4.length;j++) {
			String test = split4[j].trim();
			if(masterMap.values().contains(test)) {
				change.put(j, test);
			}else {
				change.put(j, "+"+test);
			}
		}
		// 如果测试分支拥有主分支改行代码：没改变。 不拥有：删除
		for (int i=0;i<split3.length;i++) {
			String master = split3[i].trim();
			if(testMap.values().contains(master)) {
				//change.put(i, master);
			}else {
				if(change.containsKey(i)) {
					// 当前位置有值，依次后移
					mapHouyi(change, i);
					change.put(i, "-"+master);
				}else {
					change.put(i, "-"+master);
				}
			}
		}
		// 先把该两个分支下，同方法名、同参数的两个方法体的比较结果，打印出来
		Collection<String> values = change.values();
		StringBuilder sbsb = new StringBuilder();
		for(String s : values) {
			sbsb.append(s+"\n");
		}
		//System.out.println("查看最终结果："+sbsb.toString());
		return sbsb.toString();
	}
	/**
	 * Map<Integer,String>集合，某处有数据，那么该位置及后面的数据全部根据key+1后移。从最后面开始，依次后移
	 * @param change 
	 * @param i	如果i处有值，那么后面的值全部后移一个
	 */
	public static void mapHouyi(Map<Integer,String> change,int i) {
		if(change.containsKey(i)) {
			Set<Integer> keySet = change.keySet();
			for (int j = keySet.size()-1;j >= 0 ; j--) {
				change.put(j+1, change.get(j));
				if(i == j) { // 移完了
					return;
				}
			}
		}
	}
}


