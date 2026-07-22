package org.bluebridge.action.state.state_f;

/**
 * 重复投票状态
 *
 * @author lingwh
 * @date 2019/9/23 18:05
 */
public class RepeatVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 重复投票，暂时不做处理
        System.out.println("请不要重复投票");
        // 重复投票完成，维护下一个状态，重复投票到5次，就算恶意投票了
        // 注意这里是判断大于等于4，因为这里设置的是下一个状态
        // 下一个操作次数就是5了，就应该算是恶意投票了
        if (voteManager.getMapVoteCount().get(user) >= 4) {
            voteManager.getMapState().put(user, new SpiteVoteState());
        }
    }
}
