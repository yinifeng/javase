package com.hobart.net.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;

public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,Server> {
    @Override
    public void completed(AsynchronousSocketChannel result, Server attachment) {
        //当有一个客户端接入的时候直接调用Server的accept方法，这样反复执行下去，保证多个客户端都可以阻塞
        attachment.getAssc().accept(attachment, this);
        read(result);
        
    }

    private void read(AsynchronousSocketChannel result) {
        //读取数据
        ByteBuffer buffer=ByteBuffer.allocate(1024);
        result.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer resultSize, ByteBuffer attachment) {
                //进行读取之后，重置标识位
                attachment.flip();
                //获的读取的字节数
                System.out.println(String.format("Server -> 收到客户端数据长度为：%d",resultSize));
                //获取读取的数据
                String resultData=new String(attachment.array()).trim();
                System.out.println(String.format("Server -> 收到客户端数据信息为：%s",resultData));
                String response=String.format("服务器响应，收到客户端发来的数据：%s",resultData);
                write(result,response);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }

    private void write(AsynchronousSocketChannel result, String response) {
        try {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put(response.getBytes());
            byteBuffer.flip();
            result.write(byteBuffer).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void failed(Throwable exc, Server attachment) {
        exc.printStackTrace();
    }
}
