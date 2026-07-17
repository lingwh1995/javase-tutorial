package create.factorymethod.factorymethod_i;

/**
 * Puma裤子
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class PumaTrousers implements ITrousers {

    @Override
    public void produce() {
        System.out.println("Puma Trousers produce ok...");
    }
}
