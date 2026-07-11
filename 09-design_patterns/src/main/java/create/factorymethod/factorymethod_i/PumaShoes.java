package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Puma鞋子
 * @date 2026/7/9 00:00
 */
public class PumaShoes implements IShoes {

    @Override
    public void produce() {
        System.out.println("Puma Shoes produce ok...");
    }
}
