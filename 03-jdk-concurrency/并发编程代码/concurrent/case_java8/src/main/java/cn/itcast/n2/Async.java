package cn.itcast.n2;

import cn.itcast.Constants;
import cn.itcast.n2.util.FileReader;
import lombok.extern.slf4j.Slf4j;

/**
 * 异步读取文件
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
@Slf4j(topic = "c.Async")
public class Async {

        public static void main(String[] args) {
                new Thread(() -> FileReader.read(Constants.MP4_FULL_PATH)).start();
                log.debug("do other things ...");
        }

}
