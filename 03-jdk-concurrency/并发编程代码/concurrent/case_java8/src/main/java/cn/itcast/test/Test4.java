package cn.itcast.test;

import cn.itcast.Constants;
import cn.itcast.n2.util.FileReader;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc 线程读取文件测试
 * @date 2026/7/9 00:00
 */
@Slf4j(topic = "c.Test4")
public class Test4 {

    public static void main(String[] args) {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running...");
                FileReader.read(Constants.MP4_FULL_PATH);
            }
        };

        t1.start();
        log.debug("do other things...");
    }
}
