package cn.itcast.n2;

import cn.itcast.Constants;
import cn.itcast.n2.util.FileReader;
import lombok.extern.slf4j.Slf4j;

/**
 * 同步读取文件
 *
 * @author lingwh
 * @date 2025/2/7 21:43
 */
@Slf4j(topic = "c.Sync")
public class Sync {

    public static void main(String[] args) {
        FileReader.read(Constants.MP4_FULL_PATH);
        log.debug("do other things ...");
    }
}
