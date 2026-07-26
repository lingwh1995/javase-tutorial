package org.bluebridge.action.state.state_h;

/**
 * 重复投票状态
 *
 * @author lingwh
 * @date 2019/9/23 18:36
 */
public class RepeatVoteState implements VoteState {

    /**
     * 处理状态对应的行为
     *
     * @param user 投票人
     * @param voteItem 投票项
     * @param voteManager 投票上下文，用来在实现状态对应的功能处理的时候，
     */
    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 重复投票，暂时不做处理
        System.out.println("请不要重复投票");
        // 重复投票完成，维护下一个状态，重复投票到 5 次，就算恶意投票了
        if (voteManager.getMapVoteCount().get(user) >= 4) {
            // 不要这么写了
            // voteManager.getMapState().put(user,new SpiteVoteState());
            // 直接把下一个状态的编码记录入数据库就好了
            MockDb.DB.put(user, VoteStateEnum.SPITE_VOTE.getCode());
        }
    }
}
