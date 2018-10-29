package com.hobart.net.bio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 利用线程池处理socket连接
 */
public class Server {
    
    static final int PORT=8765;

    public static void main(String[] args) {
        ServerSocket server=null;
        BufferedReader in=null;
        PrintWriter out=null;
        try {
            server=new ServerSocket(PORT);
            System.out.println("Server start....");
            Socket socket=null;
            HandlerExecutorPool executorPool=new HandlerExecutorPool(50,1000);
            while (true){
                socket=server.accept();
                executorPool.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in !=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
            if (out!=null){
                out.close();
            }
            
            if (server!=null){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
