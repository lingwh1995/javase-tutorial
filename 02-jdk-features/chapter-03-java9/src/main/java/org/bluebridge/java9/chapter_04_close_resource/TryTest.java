package org.bluebridge.java9.chapter_04_close_resource;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lingwh
 * @desc Java9资源关闭测试
 * @date 2026/7/9 00:00
 */
public class TryTest {

    public static void main(String[] args) {
        /**
         * Java8之前释放流资源
         */
        /*
        InputStreamReader reader = null;
        try {
            reader = new InputStreamReader(System.in);
            char[] buffer = new char[20];
            int len;
            if((len = reader.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.println(str);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(reader != null) {
                try {
                    reader.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }*/

        /**
         * Java8中资源关闭操作 Java8中可以实现资源的自动关闭，但是要求要关闭的所有资源必须在try子句中初始化，否则编译时会报错
         */
        /*
        try(InputStreamReader reader = new InputStreamReader(System.in)) {
            char[] buffer = new char[20];
            int len;
            if((len = reader.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.println(str);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }*/

        /**
         * Java9中资源关闭操作 Java8中可以实现资源的自动关闭，要关闭的所有资源可以不在try子句中初始化
         */
        InputStreamReader reader = new InputStreamReader(System.in);
        try (reader) {
            char[] buffer = new char[20];
            int len;
            if ((len = reader.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
