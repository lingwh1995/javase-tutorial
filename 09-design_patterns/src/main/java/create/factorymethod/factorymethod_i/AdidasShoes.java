package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Adidas鞋子
 * @date 2026/7/9 00:00
 */
public class AdidasShoes implements IShoes {

    @Override
    public void produce() {
        System.out.println("Adidas Shoes produce ok...");
    }
}
