package org.bluebridge.create.builder.builder_f;

/**
 * 具体的建造者，只负责产品的创建
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class HighHouseBuilder extends HouseBuilder {

    @Override
    public HouseBuilder buildBasic(String basic) {
        super.house.setBasic(basic);
        System.out.println("高楼大厦地基......");
        return this;
    }

    @Override
    public HouseBuilder buildWalls(String walls) {
        super.house.setWalls(walls);
        System.out.println("高楼大厦墙壁......");
        return this;
    }

    @Override
    public HouseBuilder buildRoof(String roof) {
        super.house.setRoof(roof);
        System.out.println("高楼大厦屋顶......");
        return this;
    }

    @Override
    public House build() {
        return house;
    }
}
