package org.bluebridge.create.builder.builder_a;

/**
 * AirShip构建者接口
 *
 * @author lingwh
 * @date 2019年3月23日
 */
public abstract class AbstractAirShipBuilder {

    protected AirShip airShip = new AirShip();

    /**
     * 构建轨道舱
     *
     * @return
     */
    abstract void buildOrbitalModule();

    /**
     * 构建发动机
     *
     * @return
     */
    abstract void buildEngin();

    /**
     * 构建逃逸塔
     *
     * @return
     */
    abstract void buildEscapeTower();

    public AirShip buildAirShip() {
        return airShip;
    }
}
