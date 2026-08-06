package org.bluebridge;

import org.bluebridge.utils.Base64Util;
import org.junit.Test;

/**
 * Base64 工具测试类
 *
 * @author lingwh
 * @date 2026/2/3 10:30
 */
public class Base64UtilTest {

    @Test
    public void testBase64Util() {
        // IMEI 867860061213483
        String stringMessage = "68600092900100919999999999190300050826480005082620113814000000000000000005082620224114280000000000000020060080010054FC00002800D21D8100A100CC0900006E30434533373732413133413831000000000000868916";
        String base64Message = Base64Util.stringToBase64(stringMessage);
        System.out.println("base64Message = " + base64Message);

        base64Message = "MFBVzvW5DiX917sncBQvwVTnAZATMoRP62c1a+vd5vA=";
        stringMessage = Base64Util.base64ToString(base64Message);
        System.out.println("stringMessage = " + stringMessage);
    }
}
