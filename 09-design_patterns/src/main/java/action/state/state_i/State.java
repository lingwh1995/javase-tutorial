package action.state.state_i;

/**
 * @author lingwh
 * @desc 状态抽象类
 * @date 2019/8/2 8:50
 */
public abstract class State {
    // 扣除积分方法;
    public abstract void deductIntegral();

    // 抽奖;
    public abstract boolean raffle();

    // 发放奖品;
    public abstract void offeringPrizes();
}
