package com.zyzh.test;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * TODO 类描述
 *
 * @version 1.0.0.1
 * @author: LeoWey
 * @createTime: 2024-02-08 10:38
 */
public class MapedBuffer {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(
                    "E:\\github\\TestProject\\redis-distributed-lock\\src\\file\\1.txt","rw");
            FileChannel channel = randomAccessFile.getChannel();
            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
            System.out.println(map.get(0));
            map.put(0,(byte) ('Y'));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }


    }
}
