package create.abstractfactory.abstractfactory_b;

/**
 * @author lingwh
 * @desc 调用者
 * @date 2019/3/11 00:00
 */
public class Client {
    public static void main(String[] args) {
        Engine luxuryCarEngine = new LuxuryCarFactory().createEngine();
        Seat luxurySeat = new LuxuryCarFactory().createSeat();
        Tyre luxuryTyre = new LowCarFactory().createTyre();
        luxuryCarEngine.run();
        luxurySeat.massage();
        luxuryTyre.roll();

        System.out.println("-----------------------------------");
        Engine lowCarEngine = new LowCarFactory().createEngine();
        Seat lowCarSeat = new LowCarFactory().createSeat();
        Tyre lowCarTyre = new LowCarFactory().createTyre();
        lowCarEngine.run();
        lowCarSeat.massage();
        lowCarTyre.roll();
    }
}
