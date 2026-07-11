package org.bluebridge.enumeration;

/**
 * @author lingwh
 * @desc 响应状态码枚举
 * @date 2026/7/9 00:00
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
