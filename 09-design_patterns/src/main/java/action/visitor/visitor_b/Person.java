package action.visitor.visitor_b;

/**
 * 人员抽象类
 *
 * 这里用了双分派
 * 1. 第一次分派: 在客户端中,将具体的状态作为参数传递给Woman中
 * 2. 都二次分派: Woman调类用作为参数的"具体方法"，调用getWomanResult()，同时将自己(this)w作为参数传入，完成第二次分派
 *
 * @author lingwh
 * @desc 人员抽象类
 * @date 2026/7/9 00:00
 */
public abstract class Person {
    // 提供一个方法,让访问者可以访问
    public abstract void accept(Action action);
}
