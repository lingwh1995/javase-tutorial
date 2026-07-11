package org.bluebridge.enumeration;

/**
 * @author lingwh
 * @desc 访问修饰符枚举
 * @date 2026/7/9 00:00
 */
public enum ModifyEnum {
    PUBLIC(1, "公有的"),
    PRIVATE(2, "私有的");

    private final Integer code;
    private final String desc;

    ModifyEnum(Integer code, String desc) {
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
