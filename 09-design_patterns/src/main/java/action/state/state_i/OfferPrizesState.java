package action.state.state_i;

/**
 * 发放奖品状态
 *
 * @author lingwh
 * @date
 */
public class OfferPrizesState extends State {
    // 关联抽奖活动;
    private RaffleActivity raffleActivity;

    // 初始化;
    public OfferPrizesState(RaffleActivity raffleActivity) {
        this.raffleActivity = raffleActivity;
    }

    // 扣积分方法;
    @Override
    public void deductIntegral() {
        System.out.println("发奖品呢,扣什么积分啊!!!");
    }

    // 抽奖发放
    @Override
    public boolean raffle() {
        System.out.println("您已经在领奖了,还抽奖干嘛!");
        return false;
    }

    @Override
    public void offeringPrizes() {
        if (raffleActivity.getCount() > 0) {
            System.out.println("<==请领取您的奖品==>ლ(´ڡ`ლ)好吃的.");
            // 跳转到不能抽奖状态;
            raffleActivity.setState(raffleActivity.getNotRaffleState());
        } else {
            System.out.println("o((⊙﹏⊙))o抱歉,您来晚了,没奖品了,要不您在试试别的活动!");
            // 跳转到没有奖品状态;
            raffleActivity.setState(raffleActivity.getNoPrizesState());
        }
    }
}
