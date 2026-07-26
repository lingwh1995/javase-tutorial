package org.bluebridge.create.simplefactory.simplefactory_b;

/**
 * pizza 的制作分为四个流程
 *
 * 1. prepare()
 * 2. bake()
 * 3. cut()
 * 4. box()
 *
 * @author lingwh
 * @date 2026/7/22 15:52
 */
public abstract class Pizza {

    public abstract void prepare();

    public void bake() {
        System.out.println("bake....");
    }

    public void cut() {
        System.out.println("cut.....");
    }

    public void box() {
        System.out.println("box.....");
    }
}
