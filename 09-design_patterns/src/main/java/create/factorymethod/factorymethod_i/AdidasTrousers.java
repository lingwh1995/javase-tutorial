package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Adidas裤子
 * @date 2026/7/9 00:00
 */
public class AdidasTrousers implements ITrousers {

    @Override
    public void produce() {
        System.out.println("Adidas Trousers produce ok...");
    }
}
