package cn.itcast.n3;

import cn.itcast.Constants;
import cn.itcast.n2.util.FileReader;

/**
 * 线程状态测试
 *
 * @author lingwh
 * @date 2025/2/7 18:33
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
