package create.factorymethod.factorymethod_i;

/**
 * Adidas夹克
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class AdidasJacket implements IJacket {

    @Override
    public void produce() {
        System.out.println("Adidas Jacket produce ok...");
    }
}
