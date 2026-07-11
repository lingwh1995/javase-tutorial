package action.interpreter.interpreter_b;

import java.util.HashMap;

/**
 * @author lingwh
 * @desc 表达式抽象类
 * @date 2026/7/9 00:00
 */
public abstract class Expression {
    /**
     * 解释公式和数值之间的关系
     *
     * @param var key:公式 value:变量对应的具体值,如:{a=10,b=20}
     * @return
     */
    public abstract int interpret(HashMap<String, Integer> var);
}
