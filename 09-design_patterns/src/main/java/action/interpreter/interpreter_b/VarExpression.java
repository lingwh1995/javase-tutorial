package action.interpreter.interpreter_b;

import java.util.HashMap;

/**
 * @author lingwh
 * @desc 变量解释器
 * @date 2026/7/9 00:00
 */
public class VarExpression extends Expression {
    private String key; // a b c

    public VarExpression(String key) {
        this.key = key;
    }

    /**
     * @param var key:公式 value:变量对应的具体值,如:{a=10,b=20}
     * @return
     */
    @Override
    public int interpret(HashMap<String, Integer> var) {
        return var.get(key);
    }
}
