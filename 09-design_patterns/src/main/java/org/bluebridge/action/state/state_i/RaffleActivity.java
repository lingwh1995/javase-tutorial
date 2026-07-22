package org.bluebridge.action.state.state_i;

/**
 * 抽奖活动
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class RaffleActivity {

    // 抽奖状态;
    State state = null;
    // 奖品数量;
    int count = 0;

    // 初始化;定义奖品数量;不能抽奖;
    public RaffleActivity(int count) {
        System.out.println("快来玩啊,抽奖活动开始啦");
        this.state = getNotRaffleState();
        this.count = count;
    }

    // 不能抽奖状态;
    State notRaffleState = new NotRaffleState(this);
    // 可抽奖状态;
    State raffleState = new RaffleState(this);
    // 发放奖品状态;
    State offerPrizesState = new OfferPrizesState(this);
    // 奖品发放结束状态;
    State noPrizesState = new NoPrizesState(this);

    // 扣除积分;
    public void deductIntegral() {
        state.deductIntegral();
    }

    // 抽奖;
    public void raffle() {
        if (state.raffle()) {
            // 抽奖了就做好发奖品的准备吧,
            state.offeringPrizes();
        }
    }

    // getter ,setter方法;
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getCount() {
        return count--;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public State getNotRaffleState() {
        return notRaffleState;
    }

    public void setNotRaffleState(State notRaffleState) {
        this.notRaffleState = notRaffleState;
    }

    public State getRaffleState() {
        return raffleState;
    }

    public void setRaffleState(State raffleState) {
        this.raffleState = raffleState;
    }

    public State getOfferPrizesState() {
        return offerPrizesState;
    }

    public void setOfferPrizesState(State offerPrizesState) {
        this.offerPrizesState = offerPrizesState;
    }

    public State getNoPrizesState() {
        return noPrizesState;
    }

    public void setNoPrizesState(State noPrizesState) {
        this.noPrizesState = noPrizesState;
    }
}
