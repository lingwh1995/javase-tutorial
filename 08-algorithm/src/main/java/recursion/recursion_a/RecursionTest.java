package recursion.recursion_a;

/**
 * @author lingwh
 * @desc 递归测试
 * @date 2026/7/9 00:00
 */
public class RecursionTest {
    public static void main(String[] args) {
        test(4);
    }

    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        } else {
            System.out.println("--------------");
            System.out.println("n:" + n);
            System.out.println("--------------");
        }
        System.out.println("n:" + n);
    }
}
