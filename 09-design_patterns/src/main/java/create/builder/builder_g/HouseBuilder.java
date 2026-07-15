package create.builder.builder_g;

/**
 * 抽象的建造者
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class HouseBuilder {

    protected House house = new House();

    // 地基
    public abstract void buildBasic();

    // 墙面
    public abstract void buildWalls();

    // 屋顶
    public abstract void buildRoof();

    // 建造好房子后，返回房子
    public abstract House build();
}
