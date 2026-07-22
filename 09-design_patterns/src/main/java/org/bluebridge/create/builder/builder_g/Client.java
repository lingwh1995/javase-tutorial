package org.bluebridge.create.builder.builder_g;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
