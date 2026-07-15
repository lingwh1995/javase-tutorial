package create.builder.builder_f;

/**
 * 抽象的建造者
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public abstract class HouseBuilder {

    protected House house = new House();

    // 地基
    public abstract HouseBuilder buildBasic(String basic);

    // 墙面
    public abstract HouseBuilder buildWalls(String walls);

    // 屋顶
    public abstract HouseBuilder buildRoof(String roof);

    // 建造好房子后，返回房子
    public abstract House build();
}
