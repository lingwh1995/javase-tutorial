package org.bluebridge.create.builder.builder_f;

/**
 * 具体的建造者
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class CommonHouseBuilder extends HouseBuilder {

    @Override
    public HouseBuilder buildBasic(String basic) {
        super.house.setBasic(basic);
        System.out.println("普通房子地基......");
        return this;
    }

    @Override
    public HouseBuilder buildWalls(String walls) {
        super.house.setWalls(walls);
        System.out.println("普通房子墙壁......");
        return this;
    }

    @Override
    public HouseBuilder buildRoof(String roof) {
        super.house.setRoof(roof);
        System.out.println("普通房子屋顶......");
        return this;
    }

    @Override
    public House build() {
        return house;
    }
}
