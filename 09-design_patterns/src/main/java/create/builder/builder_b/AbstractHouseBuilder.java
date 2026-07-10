package create.builder.builder_b;

/**
 * @author lingwh
 * @desc 抽象房屋建造者
 * @date 2026/7/9 00:00
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
