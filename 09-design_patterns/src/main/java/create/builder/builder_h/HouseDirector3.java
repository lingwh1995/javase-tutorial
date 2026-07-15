package create.builder.builder_h;

/**
 * 指挥者
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class HouseDirector3 {

    private HouseBuilder houseBuilder;

    // 通过构造器传入HouseBuilder
    public HouseDirector3(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    // 用于重置建造者
    public void setHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }

    /**
     * 如何建造房子交给指挥者
     */
    public House buildeHouse() {
        houseBuilder.buildWalls();
        houseBuilder.buildRoof();
        houseBuilder.buildBasic();
        return houseBuilder.buildHouse();
    }
}
