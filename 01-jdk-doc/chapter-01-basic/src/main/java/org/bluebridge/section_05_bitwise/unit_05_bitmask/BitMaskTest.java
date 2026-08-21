package org.bluebridge.section_05_bitwise.unit_05_bitmask;

import java.util.ArrayList;
import java.util.List;

/**
 * 常见位运算在掩码中的应用测试 - 掩码就是一个二进制数，配合按位与/或/异或等运算，用来选中或者屏蔽掉某些二进制位的模板数字。
 *
 * 常见位运算在掩码中的作用
 * 1. & 按位与: 用掩码提取指定位(判断某一位是否为 1)，或用掩码清零某些位(屏蔽)
 *    & 0 -> 把指定位清0 -> 低8位置为0或高8位置为0
 *    & 1 -> 保留指定位 -> 只保留低8位或只保留高8位
 * 2. | 按位或: 用掩码把某些位置为 1(置位)
 *    | 1 -> 把指定位设置为1 -> 给某权限位赋值为1，不影响其他位
 *    | 0 -> 保持不变 -> 掩码为0的位原样保留
 * 3. ^ 按位异或: 用掩码翻转某些位(0 变 1，1 变 0)
 *    ^ 1 -> 翻转指定位 -> 0变1，1变0，常用于状态开关切换
 *    ^ 0 -> 保持不变 -> 掩码为0的位原样保留
 * 4. ~ 按位取反: 生成反掩码，配合 & 使用可以清除指定的某些位
 *    ~ 掩码 -> 掩码位变0，其他位变1 -> x & ~0x0F 清除低4位，保留高4位
 * 5. << 左移: 生成掩码，1 << n 表示只选中第 n 位
 *    1 << n -> 生成第 n 位为1的掩码 -> 如 1 << 3 = 0b1000，只选中第3位
 *    n << k -> 数值整体左移k位 -> 相当于 n * 2^k
 * 6. >> 右移: 把目标位移到低位，方便与 0x1 等掩码做提取
 *    n >> k -> 数值整体右移k位 -> 相当于 n / 2^k
 *    (x >> 8) & 0xFF -> 先右移8位再与掩码提取 -> 取出第 8-15 位(第二个字节)
 *
 * 位运算总结
 * 1. 运算 符号 作用
 * 2. 与 AND & 提取 / 清除位
 * 3. 或 OR | 设置位
 * 4. 异或 XOR ^ 翻转位
 * 5. 取反 NOT ~ 所有位取反
 * 6. 左移 << 乘以 2^n
 * 7. 右移 >> 除以 2^n
 *
 * 常用的生成掩码的方式
 * 1. 左移（<<）n 位 : 1 << n 表示第 n 位值为1，其他位值为 0
 * 2. 左移（<<）n 位 - 1 : 连续 n 个 1 的掩码（位域最常用）
 * 3. 直接写字面常量 : int mask = 0b11110000;
 *
 * 典型应用场景
 * 1. 权限控制: 用 int 的每一位代表一个权限(读/写/执行)，一个 int 最多存 32 个权限
 * 2. 状态标志: 用每一位代表一个开关状态，例如网络连接状态
 * 3. 颜色打包: ARGB 各占 8 位，打包进一个 int
 * 4. 数据压缩/序列化: 多个布尔值/小整数打包进一个 int 或 long
 * 5. 位操作: 用位操作实现快速的位级计算，例如位移、按位与/或/异或等
 * 6. 物联网行业: 使用运算来快速获取第 n 位的值是0还是1，如 0 代表有后续帧，1 代表没有后续帧
 * 6. 物联网行业经典应用: 网络中传递的字节数组是无符号的，使用 java 语言的数组接收时，假设得到一字节有符号数组
 *    - 如果需要解析为有符号数，直接解析即可
 *    - 如果需要解析为无有符号数，使用位运算 & 0xFF 把符号位抹去
 *    int i = -126;
 *    int i = i & 0xFF = -126 & 0xFF = 1000 0010（补码） -> 1111 1101（反码） -> 1111 1110（原码）  & 1111 1111 = 1111 1110
 *
 * @author lingwh
 * @date 2026/8/20 20:49
 */
public class BitMaskTest {

    /**
     * 示例: 基于掩码的权限控制
     * 用 int 的 3 位表示 3 种权限，1 表示有，0 表示无
     */
    private static final int PERMISSION_EXECUTE = 1 << 0; // 0b001 = 1
    private static final int PERMISSION_WRITE = 1 << 1; // 0b010 = 2
    private static final int PERMISSION_READ = 1 << 2; // 0b100 = 4

    public static void main(String[] args) {
        System.out.println("---------- 1. 生成掩码: 左移 << ----------");
        System.out.println("1 << 0 = " + showMemoryLayout(1 << 0)); // 00000001
        System.out.println("1 << 1 = " + showMemoryLayout(1 << 1)); // 00000010
        System.out.println("1 << 2 = " + showMemoryLayout(1 << 2)); // 00000100
        System.out.println("1 << 3 = " + showMemoryLayout(1 << 3)); // 00001000

        System.out.println("\n---------- 2. 按位与 & : 提取/判断指定位 ----------");
        int value = 0b1011;
        // 提取第 2 位: 用掩码 1 << 2 = 0b0100 做按位与, 非 0 说明该位为 1
        boolean isBit2Set = (value & (1 << 2)) != 0;
        System.out.println("value = " + showMemoryLayout(value) + ", 第 2 位是否为 1: " + isBit2Set); // true

        // 提取低 8 位(用于颜色等): 与掩码 0xFF 做按位与
        // 颜色格式 #AARRGGBB
        int color = 0xAB12CD34;
        int blue = color & 0xFF; // 取出低 8 位
        int green = (color >> 8) & 0xFF; // 右移 8 位后再用 0xFF 掩码提取
        int red = (color >> 16) & 0xFF; // 右移 16 位后再用 0xFF 掩码提取
        int alpha = (color >>> 24) & 0xFF; // 0xAB
        System.out.println("color = " + Integer.toHexString(color));
        System.out.println("blue  = " + Integer.toHexString(blue) + ", green = " + Integer.toHexString(green) + ", red = "
          + Integer.toHexString(red) + ", alpha = " + Integer.toHexString(alpha));

        System.out.println("\n---------- 3. 按位或 | : 置位(把某些位设为 1) ----------");
        int permission = 0; // 0b000, 初始无任何权限
        permission |= PERMISSION_READ; // 增加读权限 -> 0b100
        permission |= PERMISSION_EXECUTE; // 增加执行权限 -> 0b101
        System.out.println("permission = " + showMemoryLayout(permission));
        System.out.println("是否可读: " + ((permission & PERMISSION_READ) != 0)); // true
        System.out.println("是否可写: " + ((permission & PERMISSION_WRITE) != 0)); // false
        System.out.println("是否可执行: " + ((permission & PERMISSION_EXECUTE) != 0)); // true

        System.out.println("\n---------- 4. 取反 ~ + 按位与 &: 清位(把某些位设为 0) ----------");
        // 添加写权限
        permission |= PERMISSION_WRITE; // 0b101 | 0b010 = 0b111
        // 去掉读权限
        permission &= ~PERMISSION_READ; // 0b111 & ~0b100 = 0b011, 去掉读权限
        System.out.println("permission = " + showMemoryLayout(permission));
        System.out.println("去掉读权限后是否可读: " + ((permission & PERMISSION_READ) != 0)); // false

        System.out.println("\n---------- 5. 按位异或 ^ : 翻转指定位 ----------");
        int flags = 0b0000;
        flags ^= 1 << 1; // 翻转第 1 位: 0 -> 1
        System.out.println("一次异或翻转后 flags = " + showMemoryLayout(flags)); // 2
        flags ^= 1 << 1; // 再翻转第 1 位: 1 -> 0, 实现开关切换
        System.out.println("两次异或翻转后 flags = " + showMemoryLayout(flags)); // 0

        System.out.println("\n---------- 6. 掩码的其他经典用法 ----------");
        // 判断奇偶: 与掩码 1 做按位与
        System.out.println("7 & 1 = " + (7 & 1) + " (奇数), 8 & 1 = " + (8 & 1) + " (偶数)");
        // 掩码常量: 0xFF 取低 8 位, 0xF0 取高 4 位等
        System.out.println("0x3C & 0xF0 = " + showMemoryLayout(0x3C & 0xF0)); // 取高 4 位 -> 0x30
        System.out.println("0x3C & 0x0F = " + showMemoryLayout(0x3C & 0x0F)); // 取低 4 位 -> 0xc
        // 大小写转换: 字母 ASCII 码第 5 位(0x20)为 0 是大写, 为 1 是小写
        System.out.println("'a' & ~0x20 = " + (char) ('a' & ~0x20)); // A
        System.out.println("'A' | 0x20  = " + (char) ('A' | 0x20)); // a

        System.out.println("\n---------- 7. 使用掩码枚举 n 个元素的所有子集（得到的是子集的下标列表）----------");
        /**
         * 原理
         *   n = 3 个元素，每个元素只有"选/不选"两种状态，正好对应二进制的一位。一个 n 位的掩码就能表示一个子集：
         *   - 1 << n = 8 ，即 mask 从 0 到 7 共 2^3 = 8 个子集
         *   - 第 i 位为 1 表示元素 i 在子集中
         *
         * 执行过程（n=3，元素为 0,1,2）
         *   +------+---------+------------------+------------+
         *   | mask |  二进制  |  选中的元素(位为1) |     子集    |
         *   +------+---------+------------------+------------+
         *   | 0    | 000     | 无               | []         |
         *   | 1    | 001     | 第0位 -> 0       | [0]        |
         *   | 2    | 010     | 第1位 -> 1       | [1]        |
         *   | 3    | 011     | 第0、1位 -> 0,1  | [0, 1]     |
         *   | 4    | 100     | 第2位 -> 2       | [2]        |
         *   | 5    | 101     | 第0、2位 -> 0,2  | [0, 2]     |
         *   | 6    | 110     | 第1、2位 -> 1,2  | [1, 2]     |
         *   | 7    | 111     | 全部 -> 0,1,2    | [0, 1, 2]  |
         *   +-----+---------+------------------+------------+
         *   - 关键判断是 (mask & (1 << i)) != 0 ：用掩码 1 << i 提取 mask 的第 i 位，非 0 说明该位为 1，元素被选中。
         *
         * 复杂度
         *   O(2^n * n) ，适合 n ≤ 20 左右的规模。这是暴力枚举子集的经典写法，常用于状态压缩 DP、位运算求解等场景。
         */
        int n = 3;
        List<List<Integer>> subsets = new ArrayList<>();

        for (int mask = 0; mask < (1 << n); mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                  subset.add(i);
                }
            }
            subsets.add(subset);
            System.out.println(mask + " " + subset);
        }
    }

    /**
     * 补全到8位二进制，左侧补0
     *
     * @param val
     * @return
     */
    public static String showMemoryLayout(int val) {
        String bin = Integer.toBinaryString(val);
        // 截取后8位，不足8位前面补0
        return String.format("%8s", bin).replace(' ', '0');
    }
}
