package org.bluebridge.utils;

import cn.hutool.core.io.checksum.crc16.*;

/**
 * HuTools 提供了常见的 CRC 实现 根据需要调用即可
 *
 * @author lingwh
 * @date 2025/9/16 10:30
 */
public class CRCUtil {

    public static long CCITT(byte[] bytes) {
        CRC16CCITT crc16CCITT = new CRC16CCITT();
        crc16CCITT.update(bytes);
        return crc16CCITT.getValue();
    }

    // 可指定数组长度
    public static long CCITT(byte[] bytes, int start, int end) {
        CRC16CCITT crc16CCITT = new CRC16CCITT();
        crc16CCITT.update(bytes, start, end);
        return crc16CCITT.getValue();
    }

    public static long CCITTFalse(byte[] bytes) {
        CRC16CCITTFalse crc16CCITTFalse = new CRC16CCITTFalse();
        crc16CCITTFalse.update(bytes);
        return crc16CCITTFalse.getValue();
    }

    public static long crc16Modbus(byte[] bytes) {
        CRC16Modbus crc16Modbus = new CRC16Modbus();
        crc16Modbus.update(bytes);
        return crc16Modbus.getValue();
    }

    public static long XModem(byte[] bytes) {
        CRC16XModem crc16XModem = new CRC16XModem();
        crc16XModem.update(bytes);
        return crc16XModem.getValue();
    }

    public static long crcIbm(byte[] bytes) {
        CRC16IBM crc16IBM = new CRC16IBM();
        crc16IBM.update(bytes);
        return crc16IBM.getValue();
    }

    public static long crcUsb(byte[] bytes) {
        CRC16USB crc16USB = new CRC16USB();
        crc16USB.update(bytes);
        return crc16USB.getValue();
    }

    public static long crcX25(byte[] bytes) {
        CRC16X25 crc16X25 = new CRC16X25();
        crc16X25.update(bytes);
        return crc16X25.getValue();
    }

    /**
     * 调用示例
     */
    public static void main(String[] args) {
        String hex = "68260030010001999999999999250719000000000d0000009999999999990004a0b598";
        // 测试数据
        byte[] testBytes = ParseUtil.hexStringToByte(hex);

        // CCITT
        CRC16CCITT crc16CCITT = new CRC16CCITT();
        // 可以指定数组起始位置和长度
        crc16CCITT.update(testBytes, 0, testBytes.length);
        String ccitt = crc16CCITT.getHexValue();
        System.out.println("CCITT = " + ccitt);

        // CCITTFalse
        CRC16CCITTFalse crc16CCITTFalse = new CRC16CCITTFalse();
        crc16CCITTFalse.update(testBytes);
        String ccittFalseHexValue = crc16CCITTFalse.getHexValue();
        System.out.println("ccittFalseHexValue = " + ccittFalseHexValue);

        // Modbus
        CRC16Modbus crc16Modbus = new CRC16Modbus();
        crc16Modbus.update(testBytes);
        String modbusHexValue = crc16Modbus.getHexValue();
        System.out.println("modbusHexValue = " + modbusHexValue);

        // XModem
        CRC16XModem crc16XModem = new CRC16XModem();
        crc16XModem.update(testBytes);
        String xModemHexValue = crc16XModem.getHexValue();
        System.out.println("xModemHexValue = " + xModemHexValue);

        // IBM
        CRC16IBM crc16IBM = new CRC16IBM();
        crc16IBM.update(testBytes);
        String ibmHexValue = crc16IBM.getHexValue();
        System.out.println("ibmHexValue = " + ibmHexValue);

        // USB
        CRC16USB crc16USB = new CRC16USB();
        crc16USB.update(testBytes);
        String usbHexValue = crc16USB.getHexValue();
        System.out.println("usbHexValue = " + usbHexValue);

        // X25
        CRC16X25 crc16X25 = new CRC16X25();
        crc16X25.update(testBytes);
        String x25HexValue = crc16X25.getHexValue();
        System.out.println("x25HexValue = " + x25HexValue);

        // CRC16Ansi
        CRC16Ansi crc16Ansi = new CRC16Ansi();
        crc16Ansi.update(testBytes);
        String crc16AnsiHexValue = crc16Ansi.getHexValue();
        System.out.println("crc16AnsiHexValue = " + crc16AnsiHexValue);

        // CRC16DNP
        CRC16DNP crc16DNP = new CRC16DNP();
        crc16DNP.update(testBytes);
        String crc16DNPHexValue = crc16DNP.getHexValue();
        System.out.println("crc16DNPHexValue = " + crc16DNPHexValue);

        // CRC16Maxim
        CRC16Maxim crc16Maxim = new CRC16Maxim();
        crc16Maxim.update(testBytes);
        String crc16MaximHexValue = crc16Maxim.getHexValue();
        System.out.println("crc16MaximHexValue = " + crc16MaximHexValue);
    }
}
