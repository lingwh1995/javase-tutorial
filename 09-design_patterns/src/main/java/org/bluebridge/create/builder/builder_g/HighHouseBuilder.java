package org.bluebridge.create.builder.builder_g;

/**
 * 具体的建造者，只负责产品的创建
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class HighHouseBuilder extends HouseBuilder {

    @Override
    public void buildBasic() {
        super.house.setBasic("高楼大厦地基......");
        System.out.println("建造高楼大厦地基......");
    }

    @Override
    public void buildWalls() {
        super.house.setWalls("高楼大厦墙壁......");
        System.out.println("建造高楼大厦墙壁......");
    }

    @Override
    public void buildRoof() {
        super.house.setRoof("高楼大厦屋顶......");
        System.out.println("建造高楼大厦屋顶......");
    }

    @Override
    public House build() {
        this.buildBasic();
        this.buildWalls();
        this.buildRoof();
        return house;
    }
}
