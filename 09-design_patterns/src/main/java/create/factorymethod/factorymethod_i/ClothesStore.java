package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc 服装店抽象类
 * @date 2026/7/9 00:00
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
