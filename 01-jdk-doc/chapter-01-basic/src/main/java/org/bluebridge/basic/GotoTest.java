package org.bluebridge.basic;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author lingwh
 * @desc goto测试
 * @date 2019/3/12 16:58
 */
@Slf4j
public class GotoTest {

    /**
     * java中没有goto,但是可以使用break和continue实现类似于goto的效果
     */
    @Test
    public void testGoto() {
        outer:
        for (int i = 0; i < 10; i++) {
            log.info("outer_loop: {}", i);
            for (int k = 0; i < 10; k++) {
                log.info("k: {}", k);
                int x = new Random().nextInt(10);
                if (x > 7) {
                    log.info("x == {}，结束inner循环，继续迭代执行outer循环了！", x);
                    continue outer;
                }
                if (x == 1) {
                    log.info("x == 1,跳出并结束整个outer和inner循环！");
                    break outer;
                }
            }
        }
        log.info("=>所有循环执行完毕！");
    }
}
