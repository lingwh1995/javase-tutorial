package og.bluebridge.generic.chapter_04_interface_use_single_generic;

import org.junit.Test;

/**
 * 使用单个泛型测试
 */
public class InterfaceUseSingleGenericTest {

    /**
     * 测试泛型透传
     */
    @Test
    public void testUseSingleGenericPassThrough() {
        // 创建一个泛型为 Integer 的Point对象
        PointImplPassThrough<Integer> integerPoint = new PointImplPassThrough<>();
        integerPoint.setX(10);
        integerPoint.setY(10);
        System.out.println(integerPoint);

        // 创建一个泛型为 Float 的Point对象
        PointImplPassThrough<Float> floatPoint = new PointImplPassThrough<>();
        floatPoint.setX(20f);
        floatPoint.setY(20f);
        System.out.println(floatPoint);

        // 创建一个泛型为 Object 的Point对象
        PointImplPassThrough<Object> objectPoint = new PointImplPassThrough<>();
        objectPoint.setX(30);
        objectPoint.setY("30");
        System.out.println(objectPoint);
    }

    /**
     * 测试泛型不透传
     */
    @Test
    public void testUseSingleGenericNoPassThrough() {
        PointImplNoPassThrough integerPoint = new PointImplNoPassThrough();
        integerPoint.setX(10);
        integerPoint.setY(10);
        System.out.println(integerPoint);
    }

}
