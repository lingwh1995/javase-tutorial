package org.bluebridge.cas;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author lingwh
 * @desc 多条件判断简化写法
 * @date 2026/7/10 00:00
 */
public class _06_MultiConditionJudgeTest {

  @Test
  public void test() {
    // 普通写法
    String food = "banana";
    if (food.equals("banana") || food.equals("apple") || food.equals("orange")) {
      System.out.println("食物的种类是水果......");
    }

    // 优化写法
    if (Arrays.asList("banana", "apple", "orange").contains(food)) {
      System.out.println("食物的种类是水果......");
    }
  }
}
