package org.bluebridge;

import org.bluebridge.utils.Base64Util;
import org.junit.Test;

public class Base64UtilTest {

    @Test
    public void testBase64Util() {
        // IMEI 867860061213483
        String stringMessage = "424547494EAA00700007086160608600968726062216151100807FD986409E14DB4515E360650F7210A0C0CD1931C632BCB91A3EFAEEA818FBE4605CFB304EA59D833A3CF566B64C92322D2582DBF60C9E301FC7BF3B8659565C5EB7DB1A42D7B2DD7EA98F2C1617A6325E00BE6F5BCA064C983ECBAD47FA48D7";
        String base64Message = Base64Util.stringToBase64(stringMessage);
        System.out.println("base64Message = " + base64Message);

        base64Message = "MFBVzvW5DiX917sncBQvwVTnAZATMoRP62c1a+vd5vA=";
        stringMessage = Base64Util.base64ToString(base64Message);
        System.out.println("stringMessage = " + stringMessage);
    }

}