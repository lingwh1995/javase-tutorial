package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Adidas夹克
 * @date 2026/7/9 00:00
 */
public class AdidasJacket implements IJacket {

    @Override
    public void produce() {
        System.out.println("Adidas Jacket produce ok...");
    }
}
