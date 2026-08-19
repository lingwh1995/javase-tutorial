package org.bluebridge.section_05_bitwise.unit_02_bitset;

import java.util.BitSet;

/**
 * jdk 中的位集合(BitSet)
 *
 * @author lingwh
 * @date 2026/3/14 15:07
 */
public class BitSetTest {

    public static void main(String[] args) {
        // 1. 创建位图(默认长度64位，可自动扩容)
        BitSet bitSet = new BitSet();

        // 2. 设置位：标记数字 5、10、100、100000 存在
        bitSet.set(0, 2, true);
        bitSet.set(5);
        bitSet.set(10);
        bitSet.set(100);
        bitSet.set(100000);

        // 3. 判断是否存在
        System.out.println(bitSet.get(5));
        System.out.println(bitSet.get(6));

        // 4. 清除位：删除数字 5
        bitSet.clear(5);
        System.out.println(bitSet.get(5));

        // 5. 统计存在的数字个数
        System.out.println(bitSet.cardinality());
    }
}
