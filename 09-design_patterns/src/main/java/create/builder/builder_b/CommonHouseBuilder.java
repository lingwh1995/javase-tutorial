package create.builder.builder_b;

/**
 * @author lingwh
 * @desc 普通房屋建造者
 * @date 2026/7/9 00:00
 */
public class CommonHouseBuilder extends AbstractHouseBuilder {
    @Override
    public void buildBasic() {
        house.setBasic("地基5米");
        System.out.println("普通房子地基5米......");
    }

    @Override
    public void buildWalls() {
        house.setWalls("围墙20公分");
        System.out.println("普通房子围墙20公分......");
    }

    @Override
    public void buildRoof() {
        house.setRoof("一层屋顶");
        System.out.println("普通房子屋顶只有一层.......");
    }
}
