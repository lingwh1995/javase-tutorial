package create.builder.builder_b;

/**
 * @author lingwh
 * @desc 客户端测试
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) {
        // 建造普通房子
        CommonHouseBuilder CommonHouseBuilder = new CommonHouseBuilder();
        CommonHouseBuilder.buildHouse();

        System.out.println("-------------------");
        // 建造高楼大厦
        HighHouseBuilder highHouseBuilder = new HighHouseBuilder();
        highHouseBuilder.buildHouse();
    }
}
