package org.bluebridge.structure.adapter.adapter_j;

/**
 * 适配器模式客户端
 *
 * @author lingwh
 * @date 2019/9/11 9:00
 */
public class Client {

    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();
        WildTurkey wildTurkey = new WildTurkey();
        // 创建一个假鸭子，内核是火鸡
        TurkeyAdapter turkeyAdapter = new TurkeyAdapter(wildTurkey);
        // 调用假鸭子的鸣叫和飞行方法
        turkeyAdapter.quack();
        turkeyAdapter.fly();
    }
}
