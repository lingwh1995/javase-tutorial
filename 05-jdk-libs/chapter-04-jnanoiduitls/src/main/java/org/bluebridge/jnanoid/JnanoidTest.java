package org.bluebridge.jnanoid;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lingwh
 * @desc 测试JnanoidUtils工具类
 * @date 2025/11/7 10:55
 */
@Slf4j
public class JnanoidTest {

    public static void main(String[] args) {
        // 默认配置：生成21位URL安全ID（字符集：_-+字母+数字）
        String id = NanoIdUtils.randomNanoId();
        log.info("标准id: {}, 长度: {}", id, id.length());

        // 生成指定长度的 id
        id = NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, NanoIdUtils.DEFAULT_ALPHABET, 32);
        log.info("指定长度id: {}, 长度: {}", id, id.length());

        // 基于指定字母表生成 id
        char[] alphabet = {'a', 'b', 'c', 'd', 'e'};
        id = NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, alphabet, NanoIdUtils.DEFAULT_SIZE);
        log.info("指定长度id: {}, 长度: {}", id, id.length());

        // 基于指定字母表和长度生成 id
        id = NanoIdUtils.randomNanoId(NanoIdUtils.DEFAULT_NUMBER_GENERATOR, alphabet, 32);
        log.info("指定字母表+长度id: {}, 长度: {}", id, id.length());
    }
}
