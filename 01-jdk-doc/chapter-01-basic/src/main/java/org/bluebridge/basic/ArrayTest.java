package org.bluebridge.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 数组测试
 *
 * @author lingwh
 * @date 2019/3/12 16:58
 */
@Slf4j
public class ArrayTest {

    /**
     * 数组的声明
     *
     * 推荐
     *	  元素的数据类型[] 一维数组的名称;
     * 不推荐
     *	  元素的数据类型  一维数组名[];
     */
    @Test
    public void arrayStatement() {
        int[] arr;
        int arr1[];
        double[] arr2;
        // 引用类型变量数组
        String[] arr3;
    }

    /**
     * 数组的静态初始化
     *
     * 1. 一维数组声明和静态初始化格式1：
     * 	  先声明再初始化
     *	     数据类型[] 数组名;
     *		 组名 = new 数据类型[]{元素1,元素2,元素3,...};
     *    声明时直接初始化
     *  	 数据类型[] 数组名 = new 数据类型[]{元素1,元素2,元素3,...};
     * 2. 一维数组声明和静态初始化格式2：
     *    数据类型[] 数组名 = {元素1,元素2,元素3...};// 必须在一个语句中完成，不能分成两个语句写
     *
     * 数组的动态初始化
     *
     * 1. 先声明再初始化：
     *	  数组存储的数据类型[] 数组名字;
     *	  数组名字 = new 数组存储的数据类型[长度];
     * 2. 声明时直接初始化
     *	  数组存储的元素的数据类型[] 数组名字 = new 数组存储的元素的数据类型[长度];
     */
    @Test
    public void arrayInit() {
        int[] arr1;
        arr1 = new int[]{1,2,3,4,5};
        log.info("arr1.length: {}", arr1.length);

        int[] arr2 = new int[]{1,2,3,4,5};
        log.info("arr2.length: {}", arr2.length);

        int[] arr3 = {1,2,3,4,5};
        log.info("arr3.length: {}", arr3.length);
    }


    /**
     * 二维数组测试
     */
    @Test
    public void twoDimensionalArrayTest() {

        // 声明一个二维数组
        int[][] twoDimensionalArray1;

        // 初始化二维数组，创建一个3行4列的二维数组
        twoDimensionalArray1 = new int[3][4];

        // 声明并初始化二维数组方式一
        int[][] twoDimensionalArray2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        // 声明并初始化二维数组方式二
        int[][] twoDimensionalArray3 = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
    }
}
