package org.bluebridge;

import org.bluebridge.utils.CommandUtil;
import org.junit.Test;

/**
 * 命令工具测试类
 *
 * @author lingwh
 * @date 2026/2/3 10:30
 */
public class CommandUtilTest {

    /**
     * 测试构建报文数据区
     *
     * @throws Exception
     */
    @Test
    public void testBuildFinalDataArea1() throws Exception {
        // 主密钥
        String mainSecret = "312131415161718112223242526272821323334353637383";
        // 随机通信码
        String randomCode = "782e2a2a2e78782e58a2a6a6a258861a";
        // 数据区
        String dataAreaHex = "260812182652000400040c3230323630373132313130300000000000000000000000000000000000000000014543383030472d434e0000010075610001782e2a2a2e78782e58a2a6a6a258861affbd001c0000012679917598263836303036313036323237323738370000";
        // 是否加密数据区
        boolean isEncryp = false;
        // 是否计算 mac 并添加 mac 到数据区尾部
        boolean withMac = true;
        CommandUtil commandUtil = new CommandUtil(mainSecret, randomCode, dataAreaHex, isEncryp, withMac);
        String finalDataAreaHex = commandUtil.buildFinalDataArea();
        System.out.println("finalDataAreaHex = " + finalDataAreaHex);
    }

    /**
     * 测试构建报文数据区
     *
     * @throws Exception
     */
    @Test
    public void testBuildFinalDataArea() throws Exception {
        // 主密钥
        String mainSecret = "7A89EF7C62C83D154F25E31E121250CC";
        // 随机通信码
        String randomCode = "4D391818394D15F50D2D86862D0D467A";
        // 数据区
        String dataAreaHex = "0000250718003224";
        // 是否加密数据区
        boolean isEncryp = false;
        // 是否计算 mac 并添加 mac 到数据区尾部
        boolean withMac = true;
        CommandUtil commandUtil = new CommandUtil(mainSecret, randomCode, dataAreaHex, isEncryp, withMac);
        String finalDataAreaHex = commandUtil.buildFinalDataArea();
        System.out.println("finalDataAreaHex = " + finalDataAreaHex);
    }

    /**
     * 测试解析报文数据区
     *
     * @throws Exception
     */
    @Test
    public void testParseDataArea() throws Exception {
        // 主密钥
        String mainSecret = "31323334353637383930313233343536";
        // 随机通信码
        String randomCode = "4D391818394D15F50D2D86862D0D467A";
        // 数据区
        // 6868150015282499999999999999990009 01 21b9 16
        // 68680a009407019999999999999996910508be9cb04fec345f9ec2ced957691faab7330fb7c95782fc3d67e7c3a66728dab17c939713873ebb1def93b174f9bc4a07a39c4f1fb2866625ed3c1bd31fc05eae072ffa666731d1522212f0f2911072495d02924e94345cf5b6af8b81e81b0a770c8a0a2235e771b7ff80e3a418d9ee4fd4575d86aecb8d34694da27d4834cb68b116
        // 08be9cb04fec345f9ec2ced957691faab7330fb7c95782fc3d67e7c3a66728dab17c939713873ebb1def93b174f9bc4a07a39c4f1fb2866625ed3c1bd31fc05eae072ffa666731d1522212f0f2911072495d02924e94345cf5b6af8b81e81b0a770c8a0a2235e771b7ff80e3a418d9ee4fd4575d86aecb8d34694da27d4834cb
        String dataAreaHex = "08be9cb04fec345f9ec2ced957691faab7330fb7c95782fc3d67e7c3a66728dab17c939713873ebb1def93b174f9bc4a07a39c4f1fb2866625ed3c1bd31fc05eae072ffa666731d1522212f0f2911072495d02924e94345cf5b6af8b81e81b0a770c8a0a2235e771b7ff80e3a418d9ee4fd4575d86aecb8d34694da27d4834cb";
        // 是否加密数据区
        boolean isEncryp = true;
        // 是否计算 mac 并添加 mac 到数据区尾部
        boolean withMac = true;
        CommandUtil commandUtil = new CommandUtil(mainSecret, randomCode, dataAreaHex, isEncryp, withMac);
        String originalDataAreaHex = commandUtil.parseDataArea();
        System.out.println("originalDataAreaHex = " + originalDataAreaHex);
    }
}
