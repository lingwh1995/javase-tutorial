package org.bluebridge;

import org.junit.Test;

/**
 * 题目列表 001.循环打印 a-g 方式一：不使用取余运算 002.循环打印 a-g 方式二：使用取余运算 003.求最大公约数 方式一：暴力穷举 004.求最大公约数 方式二：辗转相除法
 * 005.求最大公约数 方式三：更相减损术(辗转相减法) 006.求最小公倍数 方式一：暴力穷举 007.求最小公倍数 方式二：利用最大公约数 008.求最小公倍数 方式三：最小i值法
 * 009.输入三个整型数据，求最小值 010.输入三个整型数据，求中间值 011.输入三个整型数据，求最大值 012.统计字符串中一共有多少个单词
 * 013.小球从100米落下,每次落地后跳回原来的高度一半，再落下，求它在第10次落下时共经过多少米?反弹高度是多少?
 * 014.有一对兔子，从出生后第三个月起每个月都生一对兔子，小兔子长到第三个月后又生一对兔子，假如兔子都不死，问每个月有多少对兔子?（递归求解）
 * 015.有一对兔子，从出生后第三个月起每个月都生一对兔子，小兔子长到第三个月后又生一对兔子，假如兔子都不死，问每个月有多少对兔子?（非递归求解）
 * 016.牛顿迭代法求平方根，要求两次求出的平方根的差的绝对值小于0.00001 017.如果一个数等于它的因子之和，则这个数称为完数 6 = 1 + 2 + 3，找出1000以内的所有完数
 * 018.如果一个数等于它的因子之和，则这个数称为完数 6 = 1 + 2 + 3，找出1000以内的所有完数和该完数的因数（方式一：不使用结构体版）
 * 019.如果一个数等于它的因子之和，则这个数称为完数 6 = 1 + 2 + 3，找出1000以内的所有完数和该完数的因数（方式二：使用结构体版） 020.打印九九乘法表
 * 021.找出用户输入的一串数字中的最大数，程序需要提示用户一个一个的输入，当用户输入0或负数时，程序显示已经输入的最大非负数
 * 022.随机生成1-100之间的数字请人猜，如果是猜对了结束游戏，如果猜错则继续猜并提示所猜的数是大于还是小于所指定的数，最终提示猜对所用次数 023.多次输入年月日，输出最早的年月日（方式一）
 * 024.多次输入年月日，输出最早的年月日（方式二）
 *
 * @author lingwh
 * @desc 练习题测试
 * @date 2026/7/9 00:00
 */
public class ExercisesTest {

    /**
     * 001.循环打印 a-g 方式一：不使用取余运算
     */
    @Test
    public void testQuestion_001_LoopPrintAToG() {
        String str = "abcdefg";
        for (int i = 0; i < str.length(); i++) {
            int k = i;
            for (int j = 0; j < str.length(); j++) {
                System.out.print(str.charAt(k));
                if (++k == str.length()) {
                    k = 0;
                }
            }
            System.out.println();
        }
    }

    /**
     * 002.循环打印 a-g 方式二：使用取余运算
     */
    @Test
    public void testQuestion_002_LoopPrintAToG() {
        String str = "abcdefg";
        for (int i = 0; i < str.length(); i++) {
            int k = i;
            for (int j = 0; j < str.length(); j++) {
                System.out.print(str.charAt(k % str.length()));
                k++;
            }
            System.out.println();
        }
    }

    /**
     * 003.求最大公约数 方式一：暴力穷举 a.找出a和b中最小的数，把这个数赋值给c b.第一个满足 a%c==0 && b%c==0 的数就是a和b的最大公约数
     */
    @Test
    public void testQuestion_003_GreatestCommonDivisor() {
        int a = 48, b = 18, c = 0;
        c = a < b ? a : b;
        while (c > 1) {
            if (a % c == 0 && b % c == 0) {
                break;
            }
            c--;
        }
        System.out.println("最大公约数 = " + c);
    }

    /**
     * 004.求最大公约数 方式二：辗转相除法 公式：gcd(a,b) = gcd(b, a mod b); gcd(48,18) = gcd(18, 12); gcd(18,12) =
     * gcd(12, 6);
     */
    @Test
    public void testQuestion_004_GreatestCommonDivisor() {
        int a = 48, b = 18, mod = 0;
        while (a % b != 0) {
            mod = a % b;
            a = b;
            b = mod;
        }
        System.out.println("最大公约数 = " + b);
    }

    /**
     * 005.求最大公约数 方式三：更相减损术(辗转相减法) 公式：gcd(a, b) = gcd(b, a - b); 注意：a<b时，要先交换a、b两个数的位置再进行相减 gcd(48,18)
     * = gcd(18, 30); gcd(30,18) = gcd(18, 12); gcd(18,12) = gcd(12, 6);
     */
    @Test
    public void testQuestion_005_GreatestCommonDivisor() {
        int a = 48, b = 18, diff = 0;
        while (a - b != 0) {
            if (a < b) {
                int temp = a;
                a = b;
                b = temp;
            }
            diff = a - b;
            a = b;
            b = diff;
        }
        System.out.println("最大公约数 = " + a);
    }

    /**
     * 006.求最小公倍数 方式一：暴力穷举
     */
    @Test
    public void Question_006_LeastCommonMultiple() {
        int a = 48, b = 18, lcm = 0;
        lcm = a > b ? a : b;
        while (true) {
            if (lcm % a == 0 && lcm % b == 0) {
                break;
            }
            lcm++;
        }
        System.out.println("最小公倍数 = " + lcm);
    }
}
