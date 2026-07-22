package org.bluebridge.action.state.state_f;

/**
 * 黑名单状态
 *
 * @author lingwh
 * @date 2019/9/23 18:07
 */
public class BlackVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 黑名单，记入黑名单中，禁止登录系统了
        System.out.println("进入黑名单，将禁止登录和使用本系统");
    }
}
