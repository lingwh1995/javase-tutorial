package org.bluebridge.algorithm.kmp;

/**
 * 暴力匹配法
 *
 * @author lingwh
 * @date 2026/7/22 17:36
 */
public class ViolenceMatch {

    public static void main(String[] args) {
        String s1 = "12386497569";
        String s2 = "569";
        System.out.println(violenceMatch(s1, s2));
    }

    public static int violenceMatch(String s1, String s2) {
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int c1Length = c1.length;
        int c2Length = c2.length;

        // i 索引指向是 c1
        int i = 0;
        // j 索引指向是 c2
        int j = 0;
        while (i < c1Length && j < c2Length) {
            // 匹配成功
            if (c1[i] == c2[j]) {
                i++;
                j++;
            } else {
                // 匹配失败
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == c2Length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
