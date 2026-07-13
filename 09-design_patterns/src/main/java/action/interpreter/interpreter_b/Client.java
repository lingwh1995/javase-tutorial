package action.interpreter.interpreter_b;

import java.util.HashMap;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
