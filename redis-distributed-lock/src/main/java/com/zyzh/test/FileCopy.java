package com.zyzh.test;

import com.zyzh.controller.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 文件copy
 * <p>
 * 需求：1.txt --> 2.txt
 * 仅使用一个channel
 * 1、从通道读取数据到缓存区；
 * 2、把缓存区的数据写入到通道内；
 * 3、目标通道复制数据到当前通道；
 * 4、把数据从当前通道复制给目标通道；
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-02-08 8:52
 */
public class FileCopy {
    private static final Logger logger = LoggerFactory.getLogger(FileCopy.class);

    public static void main(String[] args) {
//        copy();
        copy2();
    }

    /**
     * 文件拷贝
     */
    public static void copy() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream("E:\\github\\TestProject\\redis-distributed-lock\\src\\file\\1.txt");
            FileChannel inChannel = fileInputStream.getChannel();
            fileOutputStream = new FileOutputStream("E:\\github\\TestProject\\redis-distributed-lock\\src\\file\\2.txt");
            FileChannel outChannel = fileOutputStream.getChannel();
            while (true) {
//                ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                ByteBuffer byteBuffer = ByteBuffer.allocate(5);
//                当缓存区不够大的时候需要复位
//                byteBuffer.clear();
                int read = inChannel.read(byteBuffer);
                logger.info("read:{}", read);
                if (read == -1) {
                    break;
                }
                byteBuffer.flip();
                outChannel.write(byteBuffer);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if (fileOutputStream == null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }

    public static void copy2() {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream("E:\\github\\TestProject\\redis-distributed-lock\\src\\file\\1.txt");
            FileChannel inChannel = fileInputStream.getChannel();
            fileOutputStream = new FileOutputStream("E:\\github\\TestProject\\redis-distributed-lock\\src\\file\\3.txt");
            FileChannel outChannel = fileOutputStream.getChannel();
            outChannel.transferFrom(inChannel,0,100);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
