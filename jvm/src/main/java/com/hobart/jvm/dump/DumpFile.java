package com.hobart.jvm.dump;

import java.util.ArrayList;
import java.util.List;

/**
 * dump 文件分析
 * 对内存溢出错误分析跟踪
 * eclipse下载插件http://download.eclipse.org/mat/1.7/update-site/
 * 
 * -XX:+HeapDumpOnOutOfMemoryError
 * OOM时导出堆到hprof文件
 * 
 * 设置堆内存  及输出dump文件
 * -Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
 * 
 * @author hobart
 *
 */
public class DumpFile {
	byte[] byteArray=new byte[1024*1024*1];//1MB
	
	public static void main(String[] args) {
		List<DumpFile> list=new ArrayList<DumpFile>();
		try {
			for (int i = 0; i < 40; i++) {
				list.add(new DumpFile());
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
