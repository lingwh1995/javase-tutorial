package org.bluebridge.create.factorymethod.factorymethod_h;

/**
 * 客户端测试
 *
 * @author lingwh
 * @date 2026/7/22 18:34
 */
public class Client {

    public static void main(String[] args) {
        HumanCreator yellowHumanCreator = new YellowHumanCreator();
        yellowHumanCreator.showSkinColor();
        HumanCreator blackHumanCreator = new BlackHumanCreator();
        blackHumanCreator.showSkinColor();
        HumanCreator whitewHumanCreator = new WhiteHumanCreator();
        whitewHumanCreator.showSkinColor();

        System.out.println("-------------------------------------");
        NvWa nvWa = new NvWa();
        nvWa.showSkinColor("yellow");
        nvWa.showSkinColor("black");
        nvWa.showSkinColor("white");
    }
}
