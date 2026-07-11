package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc Puma裤子
 * @date 2026/7/9 00:00
 */
public class PumaTrousers implements ITrousers {

    @Override
    public void produce() {
        System.out.println("Puma Trousers produce ok...");
    }
}
