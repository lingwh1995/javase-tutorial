package og.bluebridge.generic.section_04_param_use_multiple_generic;

import org.junit.Test;

/**
 * 类使用多个泛型测试
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
public class ParamUseMultipleGenericTest {

    @Test
    public void testUseMultipleGenericTest() {
        // 创建一个泛型 T 类型为 Integer，U 类型为 String 的 Point 对象
        Point<Integer, String> integerStringPoint = new Point<>(100, 10, "第一个坐标点");
        System.out.println(integerStringPoint);

        // 创建一个泛型 T 类型为 Float，U 类型为 String 的 Point 对象
        Point<Float, String> floatStringPoint = new Point<>(20f, 20f, "第二个坐标点");
        System.out.println(floatStringPoint);

        // 创建一个泛型 T 类型为 Object，U 类型为 String 的 Point 对象
        Point<Object, String> objectStringPoint = new Point<>(30, "30", "第三个坐标点");
        System.out.println(objectStringPoint);
    }
}
