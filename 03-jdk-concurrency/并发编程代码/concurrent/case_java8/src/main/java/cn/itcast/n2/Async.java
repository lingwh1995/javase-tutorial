package cn.itcast.n2;

import cn.itcast.Constants;
import cn.itcast.n2.util.FileReader;
import lombok.extern.slf4j.Slf4j;


/**
 * @author lingwh
 * @desc 异步读取文件
 * @date 2026/7/9 00:00
 */
@Slf4j(topic = "c.Async")
public class Async {

        public static void main(String[] args) {
                new Thread(() -> FileReader.read(Constants.MP4_FULL_PATH)).start();
                log.debug("do other things ...");
        }

}
