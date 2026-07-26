package org.bluebridge.action.visitor.visitor_b;

/**
 * 人员抽象类
 *
 * 这里用了双分派
 * 1. 第一次分派：在客户端中，将具体的状态作为参数传递给 Woman 中
 * 2. 都二次分派：Woman 调类用作为参数的"具体方法"，调用 getWomanResult()，同时将自己 (this)作为参数传入，完成第二次分派
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public abstract class Person {

    // 提供一个方法，让访问者可以访问
    public abstract void accept(Action action);
}
