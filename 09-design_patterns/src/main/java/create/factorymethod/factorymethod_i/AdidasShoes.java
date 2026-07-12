package create.factorymethod.factorymethod_i;

/**
 * Adidas鞋子
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AdidasShoes implements IShoes {

    @Override
    public void produce() {
        System.out.println("Adidas Shoes produce ok...");
    }
}
