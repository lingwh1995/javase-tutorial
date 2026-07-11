package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc 服装抽象类
 * @date 2026/7/9 00:00
 */
public abstract class Clothes {
    IHat hat;
    IJacket jacket;
    ITrousers trousers;
    IShoes shoes;

    void pack() {
        hat.produce();
        jacket.produce();
        trousers.produce();
        shoes.produce();
    }
}
