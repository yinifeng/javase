package com.hubo.java.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChanalTest {

    public static void main(String[] args) throws IOException {
        
        String fileName="he@llo.txt";
        //处理特殊符号 加密
        String fileNameEncode = URLEncoder.encode(fileName, "UTF-8");
        System.out.println("fileNameEncode="+fileNameEncode);
        //处理特殊符号 解密
        String fileNameDecode = URLDecoder.decode(fileName, "UTF-8");
        System.out.println("fileNameDecode="+fileNameDecode);

        File file =new File("D:\\tmp\\java\\"+fileNameEncode);
        
        
        String content="hello FileChannel222";
        String charsetName="UTF-8";
        //写文件，每次写覆盖
        writeFileContent(file, content,charsetName);
        
        //读取文件
        String fileContent = getFileContent(file, charsetName);
        
        System.out.println(fileContent);
        System.out.println("AbsolutePath="+file.getAbsolutePath());
        System.out.println("Name="+file.getName());
        System.out.println("Parent="+file.getParent());
        System.out.println("Path="+file.getPath());
        System.out.println("TotalSpace="+file.getTotalSpace());
        String lineSeparator = System.getProperty("line.separator");
        System.out.println("根据操作系统文件换行符"+lineSeparator);
    }
    
    
    public static boolean writeFileContent(File file,String content,String charsetName) throws IOException {
        if (!file.exists() && !file.createNewFile()){
            return false;
        }
        FileChannel channel = null;
        FileLock lock = null;
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file,"rw");
            channel = raf.getChannel();
            int i = 0;
            do {
                try {
                    lock = channel.tryLock();
                } catch (IOException e) {
                    ++i;
                    if (i > RETRY_COUNT){
                        System.err.println("write " + file.getName() + " fail;retryed time: " + i);
                        throw new IOException("write " + file.getAbsolutePath()
                                + " conflict", e);
                    }
                    try {
                        Thread.sleep(SLEEP_BASETIME * i);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    //log.warn("write " + file.getName() + " conflict;retry time: " + i);
                    System.err.println("write " + file.getName() + " conflict;retry time: " + i);
                }
            }while (lock == null);

            ByteBuffer sendBuffer = ByteBuffer.wrap(content.getBytes(charsetName));
            while (sendBuffer.hasRemaining()){
                channel.write(sendBuffer);
            }
            channel.truncate(content.length());
        } catch (FileNotFoundException e) {
            throw new IOException("file not exit");
        } finally {
            if (lock != null) {
                try {
                    lock.release();
                    lock = null;
                } catch (IOException e) {
                    System.err.println("close wrong");
                }
            }
            if (channel != null){
                try {
                    channel.close();
                    channel = null;
                } catch (IOException e) {
                    System.err.println("close wrong");
                }
            }
            if (raf != null){
                try {
                    raf.close();
                    raf = null;
                } catch (IOException e) {
                    System.err.println("close wrong");
                }
            }
        }
        return true;
    }


    public static String getFileContent(File file, String charsetName)
            throws IOException {
        RandomAccessFile fis = null;
        FileLock rlock = null;
        try {
            fis = new RandomAccessFile(file, "r");
            FileChannel fcin = fis.getChannel();
            int i = 0;
            do {
                try {
                    rlock = fcin.tryLock(0L, Long.MAX_VALUE, true);
                } catch (Exception e) {
                    ++i;
                    if (i > RETRY_COUNT) {
                        System.err.println("read " + file.getName() + " fail;retryed time: " + i);
                        throw new IOException("read " + file.getAbsolutePath()
                                + " conflict");
                    }
                    sleep(SLEEP_BASETIME * i);
                    System.err.println("read " + file.getName() + " conflict;retry time: " + i);
                }
            } while (null == rlock);
            int fileSize = (int)fcin.size();
            ByteBuffer byteBuffer = ByteBuffer.allocate(fileSize);
            fcin.read(byteBuffer);
            byteBuffer.flip();
            return byteBufferToString(byteBuffer, charsetName);
        } finally {
            if (rlock != null) {
                rlock.release();
                rlock = null;
            }
            if (fis != null) {
                fis.close();
                fis = null;
            }
        }
    }

    public static String byteBufferToString(ByteBuffer buffer,
                                            String charsetName) throws IOException {
        Charset charset = null;
        CharsetDecoder decoder = null;
        CharBuffer charBuffer = null;
        charset = Charset.forName(charsetName);
        decoder = charset.newDecoder();
        charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
        return charBuffer.toString();
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.err.println("sleep wrong");
        }
    }

    static final int RETRY_COUNT = 10;
    static final int SLEEP_BASETIME = 10;
    
}
