package create.builder.builder_a;

/**
 * 熊猫太空飞船
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class PanadaAirShipBuilder extends AbstractAirShipBuilder {

    @Override
    public void buildOrbitalModule() {
        System.out.println("构建Panada号轨道舱......");
        super.airShip.setOrbitalModule(new OrbitalModule("Panada号轨道舱"));
    }

    @Override
    public void buildEngin() {
        System.out.println("构建Panada号发动机......");
        super.airShip.setEngin(new Engin("Panada号发动机"));
    }

    @Override
    public void buildEscapeTower() {
        System.out.println("构建Panada号逃逸塔......");
        super.airShip.setEscapeTower(new EscapeTower("Panada号逃逸塔"));
    }
}
