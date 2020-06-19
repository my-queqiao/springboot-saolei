package com.example.demo;

import java.util.List;

import com.example.pojo.MethodInfo;

public class Temp {
	public static void main(String[] args) {
		String fileContent = MethodInfoUtil.readFileContent("C:\\Users\\tom\\Desktop\\User.java");
//		System.out.println(fileContent);
//		List<MethodInfo> methodInfo = MethodInfoUtil.getMethodInfo(fileContent);
//		 for (MethodInfo mi : methodInfo) {
//			System.out.println("方法名："+mi.getMethodName());
//			System.out.println("参数："+mi.getParamType());
//			System.out.println("方法体："+mi.getMethodBody());
//			System.out.println("=================================================================");
//		}
		String a = "String a = \"'\"'\";";
		boolean matches = a.matches("[\\s\\S]*'\"'[\\s\\S]*");
		System.out.println(matches);
	}
}


