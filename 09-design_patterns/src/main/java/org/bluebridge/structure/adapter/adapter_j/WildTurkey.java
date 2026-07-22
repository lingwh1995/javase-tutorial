package org.bluebridge.structure.adapter.adapter_j;

/**
 * 野火鸡
 *
 * @author lingwh
 * @date 2019/9/11 8:57
 */
public class WildTurkey implements Turkey {

    /**
     * 火鸡咯咯咯叫
     */
    @Override
    public void gobble() {
        System.out.println("Gobble gobble......");
    }

    @Override
    public void fly() {
        System.out.println("I am flying a short distinct......");
    }
}
