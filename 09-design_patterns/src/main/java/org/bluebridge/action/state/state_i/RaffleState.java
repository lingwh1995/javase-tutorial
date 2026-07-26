package org.bluebridge.action.state.state_i;

import java.util.Random;

/**
 * 抽奖状态
 *
 * @author lingwh
 * @date 2026/7/22 15:03
 */
public class RaffleState extends State {

    // 关联抽奖活动;
    private RaffleActivity raffleActivity;

    // 初始化;
    public RaffleState(RaffleActivity raffleActivity) {
        this.raffleActivity = raffleActivity;
    }

    // 扣除积分;
    @Override
    public void deductIntegral() {
        System.out.println("已扣过积分,参与抽奖吧1");
    }

    @Override
    public boolean raffle() {
        System.out.println("要抽奖啦!----->");
        Random random = new Random();
        int num = random.nextInt(10);
        if (num == 5) {
            // 只有 10% 的中奖机会; ==> 发放奖品状态;
            raffleActivity.setState(raffleActivity.getOfferPrizesState());
            return true;
        } else {
            System.out.println("没中奖啊,再接再厉===================>");
            // 跳转到不能抽奖的 状态;
            raffleActivity.setState(raffleActivity.getNotRaffleState());
            return false;
        }
    }

    @Override
    public void offeringPrizes() {
        System.out.println("没中奖,不给发奖品");
    }
}
