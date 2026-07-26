package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * 服装店抽象类
 *
 * @author lingwh
 * @date 2026/7/22 10:28
 */
public abstract class ClothesStore {

    /**
     * 工厂方法
     *
     * @return
     */
    abstract Clothes produce();

    void pack() {
        Clothes clothes = produce();
        clothes.pack();
    }
}
