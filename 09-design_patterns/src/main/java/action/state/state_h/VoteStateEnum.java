package action.state.state_h;

/**
 * 投票状态枚举
 *
 * @author lingwh
 * @date 2019/9/24 8:51
 */
public enum VoteStateEnum {

    NORMAL_STATE("001", "正常状态", "action.state.state_h.NormalVoteState"),
    REPEAT_STATE("002", "重复状态", "action.state.state_h.RepeatVoteState"),
    BLACKWARN_VOTE("003", "黑名单警告状态", "action.state.state_h.BlackWarnVoteState"),
    BLACK_VOTE("004", "黑名单状态", "action.state.state_h.BlackVoteState"),
    SPITE_VOTE("005", "恶意投票状态", "action.state.state_h.SpiteVoteState");

    private String code;
    private String desc;
    private String className;

    VoteStateEnum(String code, String desc, String className) {
        this.code = code;
        this.desc = desc;
        this.className = className;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getClassName() {
        return className;
    }
}
