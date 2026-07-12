package create.builder.builder_b;

/**
 * 抽象房屋建造者
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class AbstractHouseBuilder {

    protected House house = new House();

    public abstract void buildBasic();

    public abstract void buildWalls();

    public abstract void buildRoof();

    public House buildHouse() {
        buildBasic();
        buildWalls();
        buildRoof();
        return house;
    }
}
