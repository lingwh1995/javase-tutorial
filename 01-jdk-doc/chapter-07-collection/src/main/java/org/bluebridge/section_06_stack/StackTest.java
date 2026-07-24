package org.bluebridge.section_06_stack;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Stack;

/**
 * Stack栈测试
 *
 * @author lingwh
 * @date 2019/7/9 10:30
 */
@Slf4j
public class StackTest {

    @Test
    public void testStack() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        log.debug("stack: {}", stack);
        log.debug("------------1------------");

        stack.push(4);
        stack.push(5);
        stack.push(6);
        log.debug("stack: {}", stack);
        log.debug("------------2------------");

        Integer peek = stack.peek();
        log.debug("peek-1: {}", peek);
        log.debug("stack: {}", stack);
        peek = stack.peek();
        log.debug("peek-2: {}", peek);
        log.debug("stack: {}", stack);
        log.debug("------------3------------");

        Integer pop = stack.pop();
        log.debug("pop-1: {}", pop);
        log.debug("stack: {}", stack);
        pop = stack.pop();
        log.debug("pop-2: {}", pop);
        log.debug("stack: {}", stack);
        log.debug("------------4------------");

        log.debug("stack.get(0): {}", stack.get(0));
        log.debug("stack.get(1): {}", stack.get(1));
        log.debug("stack: {}", stack);
        log.debug("------------5------------");

        // 遍历
        while (stack.size() > 0) {
            log.debug("stack.pop(): {}", stack.pop());
        }
    }
}
