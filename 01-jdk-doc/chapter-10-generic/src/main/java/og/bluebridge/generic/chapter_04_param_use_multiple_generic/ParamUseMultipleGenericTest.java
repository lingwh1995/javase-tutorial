package og.bluebridge.generic.chapter_04_param_use_multiple_generic;

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
        Point<Integer, String> integerStringPoint = new Point<>();
        integerStringPoint.setX(10);
        integerStringPoint.setY(10);
        integerStringPoint.setDesc("第一个坐标点");
        System.out.println(integerStringPoint);

        // 创建一个泛型 T 类型为 Float，U 类型为 String 的 Point 对象
        Point<Float, String> floatStringPoint = new Point<>();
        floatStringPoint.setX(20f);
        floatStringPoint.setY(20f);
        floatStringPoint.setDesc("第二个坐标点");
        System.out.println(floatStringPoint);

        // 创建一个泛型 T 类型为 Object，U 类型为 String 的 Point 对象
        Point<Object, String> objectStringPoint = new Point<>();
        objectStringPoint.setX(30);
        objectStringPoint.setY("30");
        objectStringPoint.setDesc("第三个坐标点");
        System.out.println(objectStringPoint);
    }
}
