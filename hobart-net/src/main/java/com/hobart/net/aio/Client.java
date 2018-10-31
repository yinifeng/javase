package com.hobart.net.aio;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ExecutionException;

public class Client implements Runnable {
    private AsynchronousSocketChannel asc;
    
    public Client() throws Exception{
        this.asc=AsynchronousSocketChannel.open();
    }
    
    public void connect(){
        this.asc.connect(new InetSocketAddress("127.0.0.1",8765));
    }
    
    public void write(String request){
        try {
            this.asc.write(ByteBuffer.wrap(request.getBytes())).get();
            read();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void read() {
        try {
            ByteBuffer buffer=ByteBuffer.allocate(1024);
            this.asc.read(buffer).get();
            buffer.flip();
            byte[] respByte=new byte[buffer.remaining()];
            buffer.get(respByte);
            System.out.println(new String(respByte,"utf-8").trim());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            
        }
    }

    public static void main(String[] args) throws Exception{
        Client c1=new Client();
        c1.connect();

        Client c2=new Client();
        c2.connect();

        Client c3=new Client();
        c3.connect();
        
        new Thread(c1,"c1").start();
        new Thread(c2,"c2").start();
        new Thread(c3,"c3").start();
        
        Thread.sleep(1000);
        c1.write("c1 aaaa");
        c2.write("c2 bbbb");
        c3.write("c3 cccc");
        
    }
}
