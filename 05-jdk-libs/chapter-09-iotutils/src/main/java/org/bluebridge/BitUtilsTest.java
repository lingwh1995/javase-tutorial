package org.bluebridge;

import org.bluebridge.utils.BitUtils;

/**
 * @author lingwh
 * @desc
 * @date 2026/3/14 14:12
 */
public class BitUtilsTest {

    public static void main(String[] args) {
        byte a = (byte) 0b10101100;
        System.out.printf("swap4: %02X\n", BitUtils.swap4Bits(a));
        System.out.printf("reverse8: %02X\n", BitUtils.reverse8(a));

        int state = 0;
        state = BitUtils.setBit(state, 2);
        state = BitUtils.setBit(state, 5);
        System.out.println("bit2 =" + BitUtils.getBit(state,2));
        System.out.println("bit5 =" + BitUtils.getBit(state,5));
    }
}