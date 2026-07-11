package action.state.state_e;

/**
 * @author lingwh
 * @desc
 * @date 2019/9/23 17:37
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
        System.out.println("进入警告黑名单,禁止登陆和使用系统三天......");
    }
}
