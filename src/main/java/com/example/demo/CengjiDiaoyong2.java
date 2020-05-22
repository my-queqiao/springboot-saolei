package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controller.LiuyanSaoleiController;
import com.example.pojo.ProjectAllMethods;

public class CengjiDiaoyong2 {
	public static void main(String[] args) throws Exception {
		String gen = System.getProperty("user.dir");
		System.out.println("gen:"+gen);
//		listAllFile(new File(gen));
		listAllFile(new File(gen+"/src/main/java"));
		Class c = LiuyanSaoleiController.class;
		
	}
	public static void listAllFile(File f) throws Exception {
		File[] files = f.listFiles();
		for (File file : files) {
			//System.out.println(file);
			String fileLujin = file.toString();
			
			int end=fileLujin.lastIndexOf(".java");
			 if(end==fileLujin.length()-5){ // 必须是java文件
				 getAllMethods(fileLujin);
			}
			 /**层级调用问题。*/
			
			if (file.isDirectory()) {
				listAllFile(file);
			}
		}
	}
	/**
	 * 	得到当前javaweb项目的所有java方法（用反射）
	 * @param fileLujin
	 * @throws ClassNotFoundException
	 */
	public static void getAllMethods(String fileLujin) throws Exception {
		// fileLujin:E:\my-workspace\springboot-saolei-lite\src\main\java\com\example\acl\LiuyanRank.java
		 //System.out.println("fileLujin:"+fileLujin);
		 String lujing = fileLujin.split("com")[1];
		 String a = "com\\"+lujing;
		 String replace = a.replace("\\", ".");
		 String replace2 = replace.replace("..", ".");
		 // className:com.example.controller.SaoleiController // 包名、和类名
		 String className = replace2.substring(0, replace2.length()-5);
		 //System.out.println(className);
		 Class<?> forName = Class.forName(className); // 得到类的class对象
		 /**得到该类的所有的方法*/
		 Method[] declaredMethods = forName.getDeclaredMethods();
		 List<ProjectAllMethods> list = new ArrayList<>();
		 for(Method m : declaredMethods) {
			 System.out.println(m.toString()); // 类路径，方法名，参数类型
			 System.out.println(m.getName()); // 单纯的方法名
			 String str = m.toString();
			 Map<String, String> map = getZiduan(str);
			 /**保存到数据库*/
			 ProjectAllMethods m2 = new ProjectAllMethods();
			 m2.setPackageUrl(map.get("packageUrl"));
			 m2.setJavabeanName(map.get("javabeanName"));
			 m2.setMethodName(map.get("methodName"));
			 m2.setParamType(map.get("param")==null?"empty":map.get("param")); // null不能建立组合字段唯一索引
			 list.add(m2);
		 }
		 insertBatch(list);
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
	public static Connection conn() throws Exception {
		//1。加载驱动
		Class.forName("com.mysql.jdbc.Driver");     
		//2.获取连接conn
		Connection conn=DriverManager.getConnection("jdbc:mysql://39.106.188.246:3306/wuyuan", "root", "131122ab");
		return conn;
	}
	public static void insert(ProjectAllMethods m) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = conn();
			stmt = conn.prepareStatement(
					"INSERT INTO project_all_methods(package_url,javabean_name,method_name,param_type) VALUES(?,?,?,?)");
			stmt.setString(1, m.getPackageUrl()); // 包路径
			stmt.setString(2, m.getJavabeanName()); // 类名称
			stmt.setString(3, m.getMethodName()); // 方法名称
			stmt.setString(4, m.getParamType()); // 参数类型
			//stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
			int i = stmt.executeUpdate();
			if(i>0){
				System.out.println("success");
			}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				close(stmt, conn);
			}
	}
	public static void insertBatch(List<ProjectAllMethods> ms) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = conn();
			for(ProjectAllMethods m:ms) {
				stmt = conn.prepareStatement(
						"INSERT INTO project_all_methods(package_url,javabean_name,method_name,param_type) VALUES(?,?,?,?)");
				stmt.setString(1, m.getPackageUrl()); // 包路径
				stmt.setString(2, m.getJavabeanName()); // 类名称
				stmt.setString(3, m.getMethodName()); // 方法名称
				stmt.setString(4, m.getParamType()); // 参数类型
				//stmt.setDate(5, new java.sql.Date(System.currentTimeMillis()));
				int i = stmt.executeUpdate();
				if(i>0){
					System.out.println("success");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			close(stmt, conn);
		}
	}
	public static void close(Statement stmt,Connection conn) {
		if(stmt!=null){
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
		}
		stmt = null;
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
		}
		conn = null;
		}
	}
	/**
	 * 	获取包路径、类名、方法名、参数
	 * @param a "public static java.sql.Connection com.example.demo.CengjiDiaoyong2.conn(java.lang.String) throws java.lang.Exception"
	 * @return 
	 */
	public static Map<String, String> getZiduan(String a) {
		Map<String,String> map = new HashMap<String, String>();
		String packageUrl = null;
		String javabeanName = null;
		String methodName = null;
		String param = null;
		if(a.contains("throws")) {
			String a2 = a.split("throws")[0].trim();
			String[] split = a2.split(" ");
			String a3 = split[split.length-1]; // com.example.demo.CengjiDiaoyong2.conn()
			if(a3.contains("()")) {
				String[] split2 = a3.split("\\."); // .是转义字符？
				javabeanName = split2[split2.length-2];
				System.out.println(javabeanName);
				String[] split3 = a3.split("\\."+javabeanName);
				packageUrl = split3[0];
				System.out.println(packageUrl);
				methodName = split3[1].substring(1,split3[1].length()-2);
				System.out.println(methodName);
			}else { // 有参数
				String[] split2 = a3.split("\\(");
				param = split2[1].substring(0,split2[1].length()-1);
				System.out.println(param);
				String baoBeanMethod = split2[0];// com.example.demo.CengjiDiaoyong2.readFileContent
				String[] split3 = baoBeanMethod.split("\\.");
				javabeanName = split3[split3.length-2];
				System.out.println(javabeanName);
				String[] split4 = baoBeanMethod.split("\\."+javabeanName+".");
				packageUrl = split4[0];
				methodName = split4[1];
				System.out.println(packageUrl);
				System.out.println(methodName);
			}
		}else { // 没有throws
			String[] split = a.split(" ");
			String a2 = split[split.length-1]; // com.example.demo.CengjiDiaoyong2.conn(java.lang.String)
			if(a.contains("()")) {
				String[] split2 = a2.split("\\."); // .是转义字符？
				javabeanName = split2[split2.length-2];
				System.out.println(javabeanName);
				String[] split3 = a2.split("\\."+javabeanName); // com.example.pojo.Lei.getLeiIds()
				packageUrl = split3[0];
				System.out.println(packageUrl);
				methodName = split3[1].substring(1,split3[1].length()-2);
				System.out.println(methodName);
			}else { // 有参数
				String[] split2 = a2.split("\\(");
				param = split2[1].substring(0,split2[1].length()-1);
				System.out.println(param);
				String baoBeanMethod = split2[0];// com.example.demo.CengjiDiaoyong2.readFileContent
				String[] split3 = baoBeanMethod.split("\\.");
				javabeanName = split3[split3.length-2];
				System.out.println(javabeanName);
				String[] split4 = baoBeanMethod.split("\\."+javabeanName+".");
				packageUrl = split4[0];
				methodName = split4[1];
				System.out.println(packageUrl);
				System.out.println(methodName);
			}
		}
		map.put("packageUrl", packageUrl);
		map.put("javabeanName", javabeanName);
		map.put("methodName", methodName);
		map.put("param", param);
		return map;
	}
}
