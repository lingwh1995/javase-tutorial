package org.bluebridge.algorithm.hanoitowerproblem;

/**
 * 汉诺塔
 *
 * @author lingwh
 * @date 2026/7/22 09:33
 */
public class Hanoitower {

    public static void main(String[] args) {
        // hanoitower(1,'A','B','C');
        // hanoitower(2,'A','B','C');
        hanoitower(3, 'A', 'B', 'C');
    }

    public static void hanoitower(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            // 如果 n>=2，看成两个盘，最下面的一个盘，上面的所有盘
            // 先把上面的盘从 A->B，移动过程会使用到 C
            hanoitower(num - 1, a, c, b);
            // 把最下面的盘从 A->C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            // 把 B 塔所有的盘从 B->C，移动过程使用到 A 塔
            hanoitower(num - 1, b, a, c);
        }
    }
}
