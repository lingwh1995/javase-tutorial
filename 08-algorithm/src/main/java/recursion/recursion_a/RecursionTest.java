package recursion.recursion_a;

/**
 * 递归测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
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
