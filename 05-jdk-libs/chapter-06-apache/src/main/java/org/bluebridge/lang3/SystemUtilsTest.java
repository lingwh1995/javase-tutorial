package org.bluebridge.lang3;

import java.io.File;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 系统工具类测试类
 * @date 2025/11/7 12:59
 */
@Slf4j
public class SystemUtilsTest {

    @Test
    public void testSystemUtils() {
        String hostName = SystemUtils.getHostName();
        log.info("hostName: {}", hostName);
        File javaHome = SystemUtils.getJavaHome();
        log.info("javaHome: {}", javaHome);
        File javaIoTmpDir = SystemUtils.getJavaIoTmpDir();
        log.info("javaIoTmpDir: {}", javaIoTmpDir);
        File userDir = SystemUtils.getUserDir();
        log.info("userDir: {}", userDir);
        File userHome = SystemUtils.getUserHome();
        log.info("userHome: {}", userHome);
        String fileEncoding = SystemUtils.FILE_ENCODING;
        log.info("fileEncoding: {}", fileEncoding);
        String javaVersion = SystemUtils.JAVA_VERSION;
        log.info("javaVersion: {}", javaVersion);
        String osName = SystemUtils.OS_NAME;
        log.info("osName: {}", osName);
        String osVersion = SystemUtils.OS_VERSION;
        log.info("osVersion: {}", osVersion);
        String userLanguage = SystemUtils.USER_LANGUAGE;
        log.info("userLanguage: {}", userLanguage);
        String userName = SystemUtils.USER_NAME;
        log.info("userName: {}", userName);
    }
}
