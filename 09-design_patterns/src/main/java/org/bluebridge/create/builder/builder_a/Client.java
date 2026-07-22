package org.bluebridge.create.builder.builder_a;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2019/3/23 10:38
 */
public class Client {

    public static void main(String[] args) {
        // Dragon号飞船
        DragonAirShipBuilder dragonAirShipBuilder = new DragonAirShipBuilder();
        AirShipDirector airShipDirector = new AirShipDirector(dragonAirShipBuilder);
        AirShip dragonAirShip = airShipDirector.directAirShip();
        dragonAirShip.lanuch();

        // 重置具体建造者
        PanadaAirShipBuilder panadaAirShipBuilder = new PanadaAirShipBuilder();
        airShipDirector.setAirShipBuilder(panadaAirShipBuilder);
        AirShip panadaAirShip = airShipDirector.directAirShip();
        panadaAirShip.lanuch();
    }
}
