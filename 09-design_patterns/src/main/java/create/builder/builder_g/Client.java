package create.builder.builder_g;

/**
 * @author lingwh
 * @desc 客户端
 * @date 2026/7/9 00:00
 */
public class Client {
    public static void main(String[] args) {
        // 创建普通房子建造者
        HouseBuilder commonHouseBuilder = new CommonHouseBuilder();
        // 建造普通房子
        House commonHouse = commonHouseBuilder.build();
        System.out.println(commonHouse);

        System.out.println("---------------------------");
        // 创建高楼大厦建造者
        HouseBuilder highHouseBuilder = new HighHouseBuilder();
        // 建造高楼大厦
        House highHouse = highHouseBuilder.build();
        System.out.println(highHouse);
    }
}
