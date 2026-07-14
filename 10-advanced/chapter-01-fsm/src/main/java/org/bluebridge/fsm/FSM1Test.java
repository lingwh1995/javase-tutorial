package org.bluebridge.fsm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 有限状态机，又称有限状态自动机，简称状态机 - 使用常量作为状态码实现状态机
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
@Slf4j
public class FSM1Test {

    private static final int BEGIN = 0;
    private static final int IN_WORD = 1;
    private static final int OUT_WORD = 2;
    private static final int END = 3;

    /**
     * 测试有限状态机，使用常量作为状态码实现状态机
     */
    @Test
    public void testFSM1() {
        String s = " one  two   three    four     five ";
        int wordCount = 0;
        int state = BEGIN;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println("c = " + c + ",state = " + state);
            switch (state) {
                case BEGIN:
                    if (Character.isLetter(c)) {
                        state = IN_WORD;
                    } else {
                        state = OUT_WORD;
                    }
                    break;
                case IN_WORD:
                    if (!Character.isLetter(c)) {
                        wordCount++;
                        state = OUT_WORD;
                    }
                    break;
                case OUT_WORD:
                    if (Character.isLetter(c)) {
                        state = IN_WORD;
                    }
                    break;
            }
        }
        if (state == IN_WORD) {
            wordCount++;
        }
        state = END;
        log.info("单词数量 = {}", wordCount);
        log.info("状态机状态 = {}", state);
    }
}
