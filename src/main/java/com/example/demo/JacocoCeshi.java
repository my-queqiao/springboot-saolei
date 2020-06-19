package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JacocoCeshi {  
      
    private static boolean quoteFlag = false, lineStarFlag = false;  
      
    public static void main(String[] args) {  
          
        // 指定 一个要删除注释的 根目录  
        fileRecursion(new File("C:\\Users\\tom\\Desktop"));  
    }  
    // 文件递归   
    public static void fileRecursion(File file){  
        if(file != null){  
            if(file.isDirectory()){  
                File[] files = file.listFiles();  
                for(int i = 0; files != null && i < files.length; i ++)  
                    fileRecursion(files[i]);  
            }else{  
                if(file.getName() != null && file.getName().endsWith(".java")){ // 只删除 .java文件中注释  
                    if(removeComments(file, file)){  
                        System.out.println(file.getName()+", remove:"+true);  
                    }else{  
                        System.out.println(file.getName()+", remove:"+false);  
                    }  
                }  
            }  
        }  
    }  
      
    // 开始 删除注释  
    public static boolean removeComments(File in, File out){  
        BufferedReader read = read(in);  
        StringBuffer sb = new StringBuffer(); // 删减注释后的代码  
        if(read != null){  
            String line = null;  
            try {  
                while((line = read.readLine()) != null){  
                    sb.append(analysisLine(line));  
                }  
            } catch (IOException e) {  
                e.printStackTrace();  
                return false;  
            }finally{  
                try {  
                    if(read != null)  
                        read.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
              
        }  
        return write(out, sb.toString());  
    }  
    // 读文件 到缓冲区  
    public static BufferedReader read(File file){  
        BufferedReader br = null;  
        try {  
            br = new BufferedReader(new FileReader(file));  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }  
        return br;  
    }  
    // 写文件  
    public static boolean write(File file, String content){  
        FileWriter fw = null;  
        try {  
            fw = new FileWriter(file);  
            fw.write(content);  
            fw.flush();  
        } catch (IOException e) {  
            e.printStackTrace();  
            return false;  
        }finally{  
            try {  
                if(fw != null)  
                    fw.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return true;  
    }  
      
    // 分析 一行代码 是否有注释  
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
                if(code.charAt(i) == '"'){// 双引号 开始了  
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
                if (code.charAt(i) == '"' && code.charAt(i - 1) != '\\') { // 双引号结束了  
                    sb.append(code.charAt(i));  
                    quoteFlag = false;  
                }else{  
                    // 双引号开始了 但是没有结束  
                    sb.append(code.charAt(i));  
                }  
            }  
        }  
//      if(sb.length() != 0) // 如果 为空串，则 不添加换行  
            sb.append("\n");  
        return sb;  
    }  
  
} 