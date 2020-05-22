package com.example.demo;

public class ExecutionDataClient {
	public static void main(String[] args) {
		String a = "	/**	无效变更：\r\n" + 
				"	 * 	1、增删改注释\r\n" + 
				"	 * 	2、增删空格、回车\r\n" + 
				"	 */\r\n" + 
				"	public static void test() {\r\n" + 
				"		/*\r\n" + 
				"		 * 	测试2\r\n" + 
				"		 */\r\n" + 
				"		System.out.println(\"打印1\"); // 测试3\r\n" + 
				"		for(int i=0;i<3;i++) {\r\n" + 
				"			System.out.println(i);\r\n" + 
				"		}\r\n" + 
				"		System.out.println(\"打印2\");\r\n" + 
				"		// 测试4\r\n" + 
				"	}";
		String b = "	/**	无效变更：\r\n" + 
				"	 * 	1、增删改注释\r\n" + 
				"	 * 	2、增删空格、回车\r\n" + 
				"	 */\r\n" + 
				"	public static void test() {\r\n" + 
				"		/*\r\n" + 
				"		 * 	测试2\r\n" + 
				"		 */\r\n" + 
				"		System.out.println(\"打印1\"); // 测试3\r\n" + 
				"		for(int i=0;i<3;i++) {\r\n" + 
				"			System.out.println(i);\r\n" + 
				"		}\r\n" + 
				"		System.out.println(\"打印2\");\r\n" + 
				"		// 测试4\r\n" + 
				"	}";
		System.out.println(a.equals(b));
		//System.out.println(b);
		codeEquals(a, b);
	}
	/**
	 * 	比较两段是否相同
	 * 	1、剔除两段代码中的所有注释
	 * 	2、剔除每一行字符串的，两端的空格
	 * 	3、回车 （有些空格、回车可能是有效代码）
	 */
	public static void codeEquals(String a,String b) {
		System.out.println("a:"+a);
		// 1、删除所有注释  比如：String a = ""; // ceshi。  下面删除注释的方法，把换行符也删掉了。
		String all = a.replaceAll("\\/\\/[^\\n]*|\\/\\*([^\\*^\\/]*|[\\*^\\/*]*|[^\\**\\/]*)*\\*+\\/", "");
		System.out.println("all:"+all);
		System.out.println("=========");
		
		// 2、删除所有只含有空格、回车的行
		String[] hs = all.split("\r\n");
		for(String h : hs) {
			if(h.equals("")) {
				System.out.println("3");
			}
			// 1.1、剔除字符串前后空格
			h = h.trim();
			if(h.matches(".*[a-zA-Z]+.*")) { // 是否包含字母
				System.out.println("2");
			}
			System.out.println(h);
		}
	}
	
    //使用 Java 正则表达式,去除两边空格。
    public static String delSpace(String str) {  

        if (str == null) {  
            return null;  
        }  

        String regStartSpace = "^[　 ]*";  
        String regEndSpace = "[　 ]*$";  

        // 连续两个 replaceAll   
        // 第一个是去掉前端的空格， 第二个是去掉后端的空格   
        String strDelSpace = str.replaceAll(regStartSpace, "").replaceAll(regEndSpace, "");  

        return strDelSpace;  
    }
	
	/**	无效变更：
	 * 	1、增删改注释
	 * 	2、增删空格、回车
	 */
	public static void test() {
		/*
		 * 	测试2
		 */
		System.out.println("打印1"); // 测试3
		for(int i=0;i<3;i++) {
			System.out.println(i);
		}
		System.out.println("打印2");
		// 测试4
	}
}
