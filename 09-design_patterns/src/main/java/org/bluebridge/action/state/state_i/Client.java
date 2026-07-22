package org.bluebridge.action.state.state_i;

/**
 * 客户端
 *
 * @author lingwh
 * @date 2019/8/27 10:39
 */
public class Client {

    public static void main(String[] args) {
        // 开始活动;
        RaffleActivity raffleActivity = new RaffleActivity(1);
        // 抽奖10次;
        for (int i = 0; i < 10; i++) {
            System.out.println("第" + (i + 1) + "次抽奖--->");
            // 扣积分;抽奖
            raffleActivity.deductIntegral();
            raffleActivity.raffle();
        }
    }
}
