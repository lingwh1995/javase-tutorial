package org.bluebridge.create.simplefactory.simplefactory_e;

/**
 * 客户端 - 简单工厂模式
 *
 * @author lingwh
 * @date 2026/7/22 16:42
 */
public class Client {

    public static void main(String[] args) {
        HumanCreator humanCreator = new HumanCreator();
        Human yellowHumanSkinColor = humanCreator.createHuman("yellow");
        Human blackHumanSkinColor = humanCreator.createHuman("black");
        Human whiteHumanSkinColor = humanCreator.createHuman("white");
        System.out.println("yellowHumanSkinColor:" + yellowHumanSkinColor.skinColor);
        System.out.println("blackHumanSkinColor:" + blackHumanSkinColor.skinColor);
        System.out.println("whiteHumanSkinColor:" + whiteHumanSkinColor.skinColor);
    }
}
