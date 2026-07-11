package create.builder.builder_b;

/**
 * @author lingwh
 * @desc 高楼建造者
 * @date 2026/7/9 00:00
 */
public class HighHouseBuilder extends AbstractHouseBuilder {
    @Override
    public void buildBasic() {
        house.setBasic("地基50米");
        System.out.println("高楼大厦地基50米......");
    }

    @Override
    public void buildWalls() {
        house.setWalls("围墙40公分");
        System.out.println("高楼大厦围墙40公分......");
    }

    @Override
    public void buildRoof() {
        house.setRoof("屋顶三层");
        System.out.println("高楼大厦屋顶有三层.......");
    }
}
