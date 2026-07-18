package og.bluebridge.generic.chapter_08_generic_method;

import java.util.Arrays;

/**
 * 泛型方法交换两个数测试
 *
 * @author lingwh
 * @date 2019/3/10 18:39
 */
public class GenericMethodSwapNumberTest {

    public static void main(String[] args) {
        /**
         * 交换两个整数
         */
        int[] nums = new int[] { 1, 2, 3, 4, 5 };
        int[] swapNums = swap(nums, 0, 1);
        System.out.println(Arrays.toString(swapNums));

        /**
         * 交换两个字符串
         */
        String[] strs = new String[] { "a", "b", "c", "d", "e" };
        String[] swapStrs = swap(strs, 0, 1);
        System.out.println(Arrays.toString(swapStrs));

        /**
         * 使用泛型方法
         *
         * 1. 定义一个类型，使用大写字母T表示，这个T表示任意的类型
         * 2. 在返回值void/String/int[]之前写 <T>，表示定义了一个类型，这个类型是T
         * 3. 在方法参数中使用泛型 T
         */
        // 传入一个int类型的数组会报错，泛型必须是一个类型，如:Integer/String/Person等，不能是基本数据类型
        // swapNums = swapGeneric(nums, 0, 1);

        swapStrs = swapGeneric(strs, 0, 1);
        System.out.println(Arrays.toString(swapStrs));
    }

    /**
     * @param i
     * @param j
     * @return T[]
     * @throws
     */
    private static <T> T[] swapGeneric(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }

    /**
     * 交换两个字符串
     *
     * @param strs
     * @param i
     * @param j
     * @return String []
     * @throws
     */
    private static String[] swap(String[] strs, int i, int j) {
        String temp = strs[i];
        strs[i] = strs[j];
        strs[j] = temp;
        return strs;
    }

    /**
     * 交换两个整数
     *
     * @param nums
     * @param i
     * @param j    参数
     * @return int[]
     * @throws
     */
    private static int[] swap(int[] nums, int i, int j) {
        nums[i] = nums[i] ^ nums[j];
        nums[j] = nums[i] ^ nums[j];
        nums[i] = nums[i] ^ nums[j];
        return nums;
    }
}
