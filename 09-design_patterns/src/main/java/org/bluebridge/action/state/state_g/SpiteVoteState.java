package org.bluebridge.action.state.state_g;

/**
 * 恶意投票状态
 *
 * @author lingwh
 * @date 2019/9/23 18:06
 */
public class SpiteVoteState implements VoteState {

    /**
     * 处理状态对应的行为
     *
     * @param user 投票人
     * @param voteItem 投票项
     * @param voteManager 投票上下文，用来在实现状态对应的功能处理的时候，
     */
    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 恶意投票，取消用户的投票资格，并取消投票记录
        String s = voteManager.getMapVote().get(user);
        if (s != null) {
            voteManager.getMapVote().remove(user);
        }
        System.out.println("你有恶意刷票行为，取消投票资格");
        // 恶意投票完成，维护下一个状态，投票到 8 次，就进黑名单了
        // 注意这里是判断大于等于 7，因为这里设置的是下一个状态
        // 下一个操作次数就是 8 了，就应该算是进黑名单了
        if (voteManager.getMapVoteCount().get(user) >= 7) {
            // 不要这么写了
            // voteManager.getMapState().put(user,new BlackWarnVoteState());
            // 直接把下一个状态的编码记录入数据库就好了
            MockDb.DB.put(user, VoteStateEnum.BLACKWARN_VOTE.getCode());
        }
    }
}
