package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Nike夹克
 * @date 2026/7/9 00:00
 */
public class NikeJacket implements IJacket {

    @Override
    public void produce() {
        System.out.println("Nike Jacket produce ok...");
    }
}
