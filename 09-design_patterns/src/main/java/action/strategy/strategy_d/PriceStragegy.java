package action.strategy.strategy_d;

/**
 * @author lingwh
 * @desc 算法策略接口
 * @date 2019/8/2 15:06
 */
public interface PriceStragegy {

    /**
     * 抽象的计算策略
     *
     * @param price 具体的价格
     * @return
     */
    double calcPrice(Double price);
}
