package com.hubo.java.nio.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * copy 文件
 */
public class ChannelCopy {
    private static final int BSIZE=1024;
    
    public static void main(String[] args) throws Exception{
        FileChannel in = new FileInputStream("D:\\test\\nio\\jdbc.properties").getChannel();
        FileChannel out = new FileOutputStream("D:\\test\\nio\\aa\\abc.properties").getChannel();
        ByteBuffer buffer=ByteBuffer.allocate(BSIZE);
        while (in.read(buffer) != -1) {
            buffer.flip();
            out.write(buffer);
            buffer.clear();
        }
        
//        in.transferTo(0, in.size(), out);
//        out.transferFrom(in, 0, in.size());
    }
}
