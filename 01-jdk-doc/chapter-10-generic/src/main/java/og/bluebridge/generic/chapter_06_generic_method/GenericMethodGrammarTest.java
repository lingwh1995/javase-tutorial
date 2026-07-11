package og.bluebridge.generic.chapter_06_generic_method;

/**
 * @author lingwh
 * @desc 泛型方法语法测试
 * @date 2026/7/9 00:00
 */
public class GenericMethodGrammarTest {

    public static void main(String[] args) {
        // 测试静态泛型方法
        // 调用方式一: 直接调用
        String desc = "[静态泛型方法]调用方式一: 直接调用";
        GenericMethodGrammarTest.staticMethod(desc, 100);
        GenericMethodGrammarTest.staticMethod(desc, 100f);
        GenericMethodGrammarTest.staticMethod(desc, "100");
        // 调用方式二: 调用时添加泛型约束
        desc = "[静态泛型方法]调用方式二: 调用时添加泛型约束";
        GenericMethodGrammarTest.<Integer>staticMethod(desc, 100);
        GenericMethodGrammarTest.<Float>staticMethod(desc, 100f);
        GenericMethodGrammarTest.<String>staticMethod(desc, "100");

        System.out.println("---------------------------------------------------------");

        // 测试常规泛型方法
        // 调用方式一: 直接调用
        desc = "[常规泛型方法]调用方式一: 直接调用";
        GenericMethodGrammarTest genericFunctionTest = new GenericMethodGrammarTest();
        genericFunctionTest.normalMethod(desc, 100);
        genericFunctionTest.normalMethod(desc, 100f);
        genericFunctionTest.normalMethod(desc, "100");
        // 调用方式二: 调用时添加泛型约束
        desc = "[常规泛型方法]调用方式二: 调用时添加泛型约束";
        genericFunctionTest.<Integer>normalMethod(desc, 100);
        genericFunctionTest.<Float>normalMethod(desc, 100f);
        genericFunctionTest.<String>normalMethod(desc, "100");
    }

    /**
     * 在静态函数上使用泛型，注意 <T> 的位置
     */
    public static <T> void staticMethod(String desc, T a) {
        System.out.println(desc + "-staticMethod: " + a.toString());
    }

    /**
     * 普通函数，注意 <T> 的位置
     */
    public <T> void normalMethod(String desc, T a) {
        System.out.println(desc + "-normalMethod: " + a.toString());
    }
}
