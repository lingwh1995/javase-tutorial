package org.bluebridge.create.builder.builder_a;

/**
 * 构建 Dragon 号发动机
 *
 * @author lingwh
 * @date 2019/3/23 12:27
 */
public class DragonAirShipBuilder extends AbstractAirShipBuilder {

    @Override
    public void buildOrbitalModule() {
        System.out.println("构建Dragon号轨道舱......");
        super.airShip.setOrbitalModule(new OrbitalModule("Dragon号轨道舱"));
    }

    @Override
    public void buildEngin() {
        System.out.println("构建Dragon号发动机......");
        super.airShip.setEngin(new Engin("Dragon号发动机"));
    }

    @Override
    public void buildEscapeTower() {
        System.out.println("构建Dragon号逃逸塔......");
        super.airShip.setEscapeTower(new EscapeTower("Dragon号逃逸塔"));
    }
}
