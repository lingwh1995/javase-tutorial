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
        String stringMessage = "424547494E000017009B08632150766232972608051112540000410700010177";
        String base64Message = Base64Util.stringToBase64(stringMessage);
        System.out.println("base64Message = " + base64Message);

        base64Message = "MFBVzvW5DiX917sncBQvwVTnAZATMoRP62c1a+vd5vA=";
        stringMessage = Base64Util.base64ToString(base64Message);
        System.out.println("stringMessage = " + stringMessage);
    }
}
