package action.state.state_g;

/**
 * 黑名单警告状态
 *
 * @author lingwh
 * @date 2019/9/23 18:23
 */
public class BlackWarnVoteState implements VoteState {

    /**
     * 处理状态对应的行为
     *
     * @param user 投票人
     * @param voteItem 投票项
     * @param voteManager 投票上下文，用来在实现状态对应的功能处理的时候，
     */
    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 待进黑名单警告状态
        System.out.println("禁止登录和使用系统3天");
        // 待进黑名单警告处理完成，维护下一个状态，投票到10次，就进黑名单了
        // 注意这里是判断大于等于9，因为这里设置的是下一个状态
        // 下一个操作次数就是10了，就应该算是进黑名单了
        if (voteManager.getMapVoteCount().get(user) >= 9) {
            // 不要这么写了
            // voteManager.getMapState().put(user, new BlackVoteState());
            // 直接把下一个状态的编码记录入数据库就好了
            MockDb.DB.put(user, VoteStateEnum.BLACK_VOTE.getCode());
        }
    }
}
