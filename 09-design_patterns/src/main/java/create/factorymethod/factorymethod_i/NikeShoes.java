package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Nike鞋子
 * @date 2026/7/9 00:00
 */
public class NikeShoes implements IShoes {

    @Override
    public void produce() {
        System.out.println("Nike Shoes produce ok...");
    }
}
