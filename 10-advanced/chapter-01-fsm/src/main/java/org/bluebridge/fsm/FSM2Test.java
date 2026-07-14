package org.bluebridge.fsm;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.bluebridge.fsm.STATE.*;

enum STATE {
    BEGIN,
    IN_WORD,
    OUT_WORD,
    END
}

/**
 * 有限状态机测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
@Slf4j
public class FSM2Test {

    /**
     * 测试有限状态机，使用枚举作为状态码实现状态机
     */
    @Test
    public void testFSM2() {
        String s = " one  two   three    four     five ";
        STATE state = BEGIN;
        int wordCount = 0;
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
