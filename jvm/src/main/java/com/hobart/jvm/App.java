package com.hobart.jvm;

/**
 * 查看堆内存大小
 * 
 * -XX:+TraceClassLoading 监控类的加载
 * 
 * -Xms 设置初始分配大小，默认物理内存的1/64
 * -Xmx 最大分配内存，默认为物理内存的1/4
 * 
 * -XX:+PrintGCDetails 输出详细的GC日志
 * 
 * 如VM参数：-Xms1024m -Xmx1024m -XX:+PrintGCDetails
 * 
 * 
 * 堆内存
 * 	新生代：
 * 		伊甸区（eden 6/8）
 * 		幸存s0区(from 1/8)
 * 		幸存s1区 (to 1/8)
 *  老年代：
 *  
 *  分配内存=新生代(1/3)+老年代(2/3)
 *  
 *  永久代（非堆）JDK1.8已经取消 叫  元空间
 *
 *
 *2、-Xmn、-XX:NewRatio、-XX:SurvivorRatio：
 *
 *	-Xmn
 *　　设置新生代大小
 *
 *	-XX:NewRatio
 *　　新生代（eden+2*s）和老年代（不包含永久区）的比值
 *
 *　　　　例如：4，表示新生代:老年代=1:4，即新生代占整个堆的1/5
 *
 *	-XX:SurvivorRatio（幸存代）
 *　　设置两个Survivor区和eden的比值
 *
 *　　　　例如：8，表示两个Survivor:eden=2:8，即一个Survivor占年轻代的1/10
 *
 */
public class App 
{
    public static void main( String[] args )
    {	
    	//返回Java 虚拟机试图使用 的最大内存
    	//默认物理内存的1/4
        long maxMemory=Runtime.getRuntime().maxMemory();
        //返回Java 虚拟机的内存总量
        //默认物理内存的1/64
        long totalMemory=Runtime.getRuntime().totalMemory();
        
        long freeMemory = Runtime.getRuntime().freeMemory();
        
        System.out.println("MAX_MEMORY ="+maxMemory +"(字节)、"+(maxMemory /(double)1024/1024)+"MB");
        System.out.println("TOTAL_MEMORY ="+totalMemory +"(字节)、"+(totalMemory /(double)1024/1024)+"MB");
        System.out.println("FREE_MEMORY ="+freeMemory +"(字节)、"+(freeMemory /(double)1024/1024)+"MB");
    }
}
