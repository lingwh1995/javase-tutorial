package stack.stack_c;

/**
 * @author lingwh
 * @desc 逆波兰表达式测试
 * @date 2026/7/9 00:00
 */
public class PolandNatationTest {
    public static void main(String[] args) {
        // (3+4)*5-6 => 3 4 + 5 * 6 -
        String epression = "3 4 + 5 * 6 -";
        int i = PolandNatation.calcResult(epression);
        System.out.println(i);
    }
}
