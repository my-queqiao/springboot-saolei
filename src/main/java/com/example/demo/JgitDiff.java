package com.example.demo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
/****
 // 如下，一个diff，下面可能有多个@@。
 // 每一个@@,表示一个差异段。
 // @@ -219,6 +219,9 @@   -219,6:表示源分支219行开始，往下数6行。    +219,9：表示目标分支219行起，往下数9行。
 // （接上）这两个分支中，该文件中，这两段代码的差异分析。-、+表示的是源分支、目标分支，并不表示减、或增。
  *
 // 但是，具体打印出来的分析中，每一行代码前面的-：表示目标分支删掉的代码行。 +：表示目标分支增加的代码行。
  
  * 	注意：既然是差异段，就有很大可能不包含方法名称。

diff --git a/src/main/java/com/example/controller/SaoLeiController.java b/src/main/java/com/example/controller/SaoLeiController.java
index fd80ab3..5ebfa73 100644
--- a/src/main/java/com/example/controller/SaoLeiController.java
+++ b/src/main/java/com/example/controller/SaoLeiController.java
@@ -219,6 +219,9 @@
 				lei.setRoundNum(0);
 				lei.setLeiIds("");
 			}
+			System.out.println("================测试增加代码1=============");
+			System.out.println("================测试增加代码2=============");
+			System.out.println("================测试增加代码3=============");
 			//
 			 //再统计当前方块周围的所有id
 			//
@@ -253,56 +256,20 @@
 				strb4.append(currentId+everyHangNum+1);
 				strb4.append(",");
 			}
+			//测试注释代码
 			//查询左（特殊位置id：31）
-			if(currentId >= 1 && (currentId-1)%everyHangNum != 0 ){//1、当前方块大于等于1。2、当前左边的方块不能被30整除
-				strb4.append(currentId-1);
-				strb4.append(",");
-			}
+//			if(currentId >= 1 && (currentId-1)%everyHangNum != 0 ){//1、当前方块大于等于1。2、当前左边的方块不能被30整除
+//				strb4.append(currentId-1);
 */
/*
 * 
 * Child 子版本号
 * Parent 父版本号
 */
public class JgitDiff {
	
	public JgitDiff() {
	}
	static String URL="E:\\my-workspace\\springboot-saolei\\.git";
	static Git git;
	public static Repository repository;
	public static void main(String[] args) {
		JgitDiff jgitDiff = new JgitDiff();
		
//		jgitDiff.diffMethod("Child","Parent");
		jgitDiff.diffMethod("master","test");
	}
	/*
	 * 
	 */
	public void diffMethod(String Child, String Parent){
		try {
			git=Git.open(new File(URL));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		repository=git.getRepository();
		ObjectReader reader = repository.newObjectReader();
		CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
	
		try {
			ObjectId old = repository.resolve(Child + "^{tree}");
			ObjectId head = repository.resolve(Parent+"^{tree}");
			oldTreeIter.reset(reader, old);
			CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
			newTreeIter.reset(reader, head);
			List<DiffEntry> diffs= git.diff()
                    .setNewTree(newTreeIter)
                    .setOldTree(oldTreeIter)
                    .call();
			
			 ByteArrayOutputStream out = new ByteArrayOutputStream();
			    DiffFormatter df = new DiffFormatter(out);
			    df.setRepository(git.getRepository());
			
			for (DiffEntry diffEntry : diffs) {
		         df.format(diffEntry);
		         String diffText = out.toString("UTF-8");
		         System.out.println(diffText);
		       //  out.reset();
			}
		} catch (IncorrectObjectTypeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (GitAPIException e) {
			e.printStackTrace();
		}
	}
}