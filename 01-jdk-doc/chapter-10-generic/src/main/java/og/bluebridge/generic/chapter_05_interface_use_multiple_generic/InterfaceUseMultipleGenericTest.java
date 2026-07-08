package og.bluebridge.generic.chapter_05_interface_use_multiple_generic;

import org.junit.Test;

public class InterfaceUseMultipleGenericTest {

    /**
     * 测试泛型透传
     */
    @Test
    public void testUseMultipleGenericPassThroughTest() {
        // 创建一个泛型T类型为Integer,U类型为String的Point对象
        PointImplPassThrough<Integer, String> integerStringPoint = new PointImplPassThrough<>();
        integerStringPoint.setX(10);
        integerStringPoint.setY(10);
        integerStringPoint.setDesc("第一个坐标点");
        System.out.println(integerStringPoint);

        // 创建一个泛型T类型为Float,U类型为String的Point对象
        PointImplPassThrough<Float, String> floatStringPoint = new PointImplPassThrough<>();
        floatStringPoint.setX(20f);
        floatStringPoint.setY(20f);
        floatStringPoint.setDesc("第二个坐标点");
        System.out.println(floatStringPoint);

        // 创建一个泛型T类型为Object,U类型为String的Point对象
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
        // 创建一个泛型T类型为Integer,U类型为String的Point对象
        PointImplNoPassThrough integerStringPoint = new PointImplNoPassThrough();
        integerStringPoint.setX(10);
        integerStringPoint.setY(10);
        integerStringPoint.setDesc("第一个坐标点");
        System.out.println(integerStringPoint);
    }

}
