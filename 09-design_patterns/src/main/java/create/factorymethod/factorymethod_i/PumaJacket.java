package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Puma夹克
 * @date 2026/7/9 00:00
 */
public class PumaJacket implements IJacket {

    @Override
    public void produce() {
        System.out.println("Puma Jacket produce ok...");
    }
}
