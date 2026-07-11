package create.factorymethod.factorymethod_i;

/**
 * @author lingwh
 * @desc 服装工厂
 * @date 2026/7/9 00:00
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
