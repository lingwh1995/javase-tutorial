package org.bluebridge.action.state.state_g;

/**
 * 投票状态枚举
 *
 * @author lingwh
 * @date 2019/9/24 8:51
 */
public enum VoteStateEnum {

    NORMAL_STATE("001", "正常状态"),
    REPEAT_STATE("002", "重复状态"),
    BLACKWARN_VOTE("003", "黑名单警告状态"),
    BLACK_VOTE("004", "黑名单状态"),
    SPITE_VOTE("005", "恶意投票状态");

    private String code;
    private String desc;

    VoteStateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
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
}
