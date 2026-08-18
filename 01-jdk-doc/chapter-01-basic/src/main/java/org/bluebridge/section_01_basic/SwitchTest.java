package org.bluebridge.section_01_basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * switch测试
 *
 * @author lingwh
 * @date 2019/7/10 13:39
 */
@Slf4j
public class SwitchTest {

    /**
     * 测试 switch 的嵌套
     */
    @Test
    public void testSwitchNest() {
        char condition1 = '3';
        char condition2 = 'a';
        switch (condition1) {
            case '1':
                log.info("1");
                break;
            case '2':
                log.info("2");
                break;
            case '3':
                log.info("3");
                switch (condition2) {
                    case 'a':
                        log.info("a");
                        break;
                    case 'b':
                        log.info("b");
                        break;
                    case 'c':
                        log.info("c");
                        break;
                    default:
                        log.info("default");
                        break;
                }
                break;
            default:
                log.info("0");
                break;
        }
    }

    /**
     * 测试 switch 配合枚举使用
     */
    @Test
    public void testSwitchEnum() {
        Color color = Color.RED;
        switch (color) {
            case RED:
                log.info("红色......");
                break;
            case GREEN:
                log.info("绿色......");
                break;
            case BLUE:
                log.info("蓝色......");
                break;
            default:
                log.info("暂无该颜色......");
                break;
        }
    }

    /**
     * 测试 case 穿透
     */
    @Test
    public void testCasePenetrate() {
        int i = 0;
        switch (i) {
            case 0:
            case 1:
            case 2:
                log.info("测试case穿透......");
                break;
            case 3:
                log.info("i的值是3......");
                break;
            default:
                break;
        }
    }
}

enum Color {
    RED,
    GREEN,
    BLUE
}
