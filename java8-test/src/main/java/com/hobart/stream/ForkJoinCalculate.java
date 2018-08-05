package com.hobart.stream;

import java.util.concurrent.RecursiveTask;

/**
 * 并行计算
 * @author hobart
 *
 */
public class ForkJoinCalculate extends RecursiveTask<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long start;
	private long end;
	private static final long THRESHOLD=10000;
	
	public ForkJoinCalculate(long start,long end) {
		this.start=start;
		this.end=end;
	}
	
	@Override
	protected Long compute() {
		long len =end-start;
		if(len <= THRESHOLD){//计算
			long sum=0;
			for(long i=start;i <=end;i++){
				sum += i;
			}
			return sum;
		}else{//拆分子任务
			long middle=(start+end)/2;
			ForkJoinCalculate left=new ForkJoinCalculate(start, middle);
			left.fork();//拆分子任务，同时压入线程栈
			
			ForkJoinCalculate right=new ForkJoinCalculate(middle+1, end);
			right.fork();
			return left.join()+right.join();
		}
	}

}
