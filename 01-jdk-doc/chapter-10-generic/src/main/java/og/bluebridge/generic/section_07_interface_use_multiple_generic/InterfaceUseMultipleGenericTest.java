package og.bluebridge.generic.section_07_interface_use_multiple_generic;

import org.junit.Test;

/**
 * 接口使用多个泛型测试
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
public class InterfaceUseMultipleGenericTest {

    /**
     * 测试泛型透传
     */
    @Test
    public void testUseMultipleGenericPassThroughTest() {
        // 创建一个泛型 T 类型为 Integer,U 类型为 String 的 Point 对象
        PointImplPassThrough<Integer, String> integerStringPoint = new PointImplPassThrough<>();
        integerStringPoint.setX(10);
        integerStringPoint.setY(10);
        integerStringPoint.setDesc("第一个坐标点");
        System.out.println(integerStringPoint);

        // 创建一个泛型 T 类型为 Float，U 类型为 String 的 Point 对象
        PointImplPassThrough<Float, String> floatStringPoint = new PointImplPassThrough<>();
        floatStringPoint.setX(20f);
        floatStringPoint.setY(20f);
        floatStringPoint.setDesc("第二个坐标点");
        System.out.println(floatStringPoint);

        // 创建一个泛型 T 类型为 Object，U 类型为 String 的 Point 对象
        PointImplPassThrough<Object, String> objectStringPoint = new PointImplPassThrough<>();
        objectStringPoint.setX(30);
        objectStringPoint.setY("30");
        objectStringPoint.setDesc("第三个坐标点");
        System.out.println(objectStringPoint);
    }

    /**
     * 测试泛型不透传
     */
    @Test
    public void testUseMultipleGenericNoPassThroughTest() {
        // 创建一个泛型 T 类型为 Integer，U 类型为 String 的 Point 对象
        PointImplNoPassThrough integerStringPoint = new PointImplNoPassThrough();
        integerStringPoint.setX(10);
        integerStringPoint.setY(10);
        integerStringPoint.setDesc("第一个坐标点");
        System.out.println(integerStringPoint);
    }
}
