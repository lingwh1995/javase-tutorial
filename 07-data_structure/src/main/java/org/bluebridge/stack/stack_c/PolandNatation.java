package org.bluebridge.stack.stack_c;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式
 *
 * @author lingwh
 * @date 2026/7/22 11:32
 */
public class PolandNatation {

    /**
     * 转换字符串，将字符串放入到 List 中
     *
     * @param src
     * @return
     */
    private static List<String> convert(String src) {
        Stack<String> elementStack = new Stack<>();
        String[] split = src.split(" ");
        for (int i = 0; i < split.length; i++) {
            elementStack.add(split[i]);
        }
        return elementStack;
    }

    /**
     * 根据逆波兰表达式计算结果
     *
     * @param src
     * @return
     */
    public static int calcResult(String src) {
        List<String> convert = convert(src);
        Iterator<String> iterator = convert.iterator();
        // 存放
        Stack<String> elementStack = new Stack<>();
        while (iterator.hasNext()) {
            String element = iterator.next();
            // 如果是数字
            if (element.matches("\\d+")) {
                elementStack.push(element);
            } else {
                Integer param1 = Integer.parseInt(elementStack.pop());
                Integer param2 = Integer.parseInt(elementStack.pop());
                switch (element) {
                    case "+": {
                        elementStack.push(String.valueOf(param1 + param2));
                    }
                        break;
                    case "-": {
                        elementStack.push(String.valueOf(param2 - param1));
                    }
                        break;
                    case "*": {
                        elementStack.push(String.valueOf(param1 * param2));
                    }
                        break;
                    case "/": {
                        elementStack.push(String.valueOf(param2 / param1));
                    }
                        break;
                    default:
                        break;
                }
            }
        }
        return Integer.parseInt(elementStack.pop());
    }
}
