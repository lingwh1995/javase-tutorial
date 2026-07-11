package action.interpreter.interpreter_b;

import java.util.HashMap;

/**
 * @author lingwh
 * @desc 客户端
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) {
        String expressionStr = "a-b";
        // String expressionStr ="a+b";
        Caculator caculator = new Caculator(expressionStr);
        HashMap<String, Integer> param = new HashMap<>();
        param.put("a", 10);
        param.put("b", 10);
        int result = caculator.run(param);
        System.out.println("计算结果:" + result);
    }
}
