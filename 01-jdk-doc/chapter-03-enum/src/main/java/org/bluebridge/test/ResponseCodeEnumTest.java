package org.bluebridge.test;

import org.bluebridge.enumeration.ResponseCodeEnum;
import org.junit.Test;

/**
 * ResponseCodeEnum测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class ResponseCodeEnumTest {

    /**
     * 测试响应状态码枚举
     */
    @Test
    public void testResponseCodeEnum() {
        ResponseCodeEnum success = ResponseCodeEnum.SUCCESS;
        System.out.println(success.getCode() + "," + success.getDesc());
        ResponseCodeEnum error = ResponseCodeEnum.ERROR;
        System.out.println(error.getCode() + "," + error.getDesc());
    }
}
