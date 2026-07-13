package create.builder.builder_b;

/**
 * 普通房屋建造者
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
