package org.bluebridge.enumeration;

/**
 * 响应状态码枚举
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public enum ResponseCodeEnum {

    SUCCESS(200, "操作成功"),
    ERROR(500, "内部错误");

    private final Integer code;
    private final String desc;

    ResponseCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
