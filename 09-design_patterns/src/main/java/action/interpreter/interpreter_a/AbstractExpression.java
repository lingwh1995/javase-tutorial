package action.interpreter.interpreter_a;

/**
 * @author lingwh
 * @desc 抽象表达式
 * @date 2019/8/27 13:50
 */
public abstract class AbstractExpression {

  /**
   * 解释的操作
   *
   * @param ctx 上下文对象
   */
  public abstract void interpret(Context ctx);
}
