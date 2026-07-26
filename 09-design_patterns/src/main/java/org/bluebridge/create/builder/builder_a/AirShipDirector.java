package org.bluebridge.create.builder.builder_a;

/**
 * 装配 AirShip
 *
 * @author lingwh
 * @date 2019/3/23 7:53
 */
public class AirShipDirector {

    private AbstractAirShipBuilder airShipBuilder;

    public AirShipDirector(AbstractAirShipBuilder airShipBuilder) {
        this.airShipBuilder = airShipBuilder;
    }

    public void setAirShipBuilder(AbstractAirShipBuilder airShipBuilder) {
        this.airShipBuilder = airShipBuilder;
    }

    public AirShip directAirShip() {
        // 装配飞船逻辑步骤
        airShipBuilder.buildOrbitalModule();
        airShipBuilder.buildEngin();
        airShipBuilder.buildEscapeTower();
        return airShipBuilder.buildAirShip();
    }
}
