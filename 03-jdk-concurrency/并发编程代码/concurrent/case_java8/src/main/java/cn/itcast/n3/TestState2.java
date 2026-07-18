package cn.itcast.n3;

import cn.itcast.Constants;
import cn.itcast.n2.util.FileReader;

/**
 * 线程状态测试
 *
 * @author lingwh
 * @date 2026/7/13 19:02
 */
public class TestState2 {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            FileReader.read(Constants.MP4_FULL_PATH);
            FileReader.read(Constants.MP4_FULL_PATH);
            FileReader.read(Constants.MP4_FULL_PATH);
        }, "t1").start();

        Thread.sleep(1000);
        System.out.println("ok");
    }
}
