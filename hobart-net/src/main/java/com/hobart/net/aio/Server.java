package com.hobart.net.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步非阻塞IO 
 */
public class Server {
    //线程池
    private ExecutorService executorService;
    //线程组
    private AsynchronousChannelGroup threadGroup;
    //服务通道
    private AsynchronousServerSocketChannel assc;
    
    public Server(int port){
        try {
            //创建一个缓冲池
            this.executorService= Executors.newCachedThreadPool();
            //创建线程组
            this.threadGroup=AsynchronousChannelGroup.withCachedThreadPool(this.executorService, 1);
            //创建服务器通道
            this.assc=AsynchronousServerSocketChannel.open(this.threadGroup);
            //进行绑定
            assc.bind(new InetSocketAddress(port));
            System.out.println(String.format("Server start ,port : %d",port));
            //进行阻塞
            assc.accept(this,new ServerCompletionHandler());
            //一直阻塞 不让服务器停止
            Thread.sleep(Long.MAX_VALUE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server(8765);
    }

    public AsynchronousServerSocketChannel getAssc() {
        return assc;
    }

    public void setAssc(AsynchronousServerSocketChannel assc) {
        this.assc = assc;
    }
}
