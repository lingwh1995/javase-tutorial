package org.bluebridge.create.factorymethod.factorymethod_i;

/**
 * 服装工厂
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public interface IClothesProduceFactory {

    // 生产帽子
    IHat produceHat();

    // 生产夹克衫
    IJacket produceJacket();

    // 生产裤子
    ITrousers produceTrousers();

    // 生产鞋子
    IShoes produceShoes();
}
