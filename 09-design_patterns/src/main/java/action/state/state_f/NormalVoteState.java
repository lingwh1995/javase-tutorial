package action.state.state_f;

/**
 * @author lingwh
 * @date 2019/9/23 18:02
 */
public class NormalVoteState implements VoteState {
    /**
     * 处理状态对应的行为
     *
     * @param user 投票人
     * @param voteItem 投票项
     * @param voteManager 投票上下文，用来在实现状态对应的功能处理的时候，
     */
    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        // 正常投票，记录到投票记录中
        voteManager.getMapVote().put(user, voteItem);
        System.out.println("恭喜你投票成功");
        // 正常投票完成，维护下一个状态，同一个人再投票就重复了
        voteManager.getMapState().put(user, new RepeatVoteState());
    }
}
