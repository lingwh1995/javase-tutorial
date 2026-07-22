package og.bluebridge.generic.section_06_interface_use_single_generic;

import org.junit.Test;

/**
 * 使用单个泛型测试
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
public class InterfaceUseSingleGenericTest {

    /**
     * 测试泛型透传
     */
    @Test
    public void testUseSingleGenericPassThrough() {
        // 创建一个 Integer 类型 的 Point 对象
        PointImplPassThrough<Integer> integerPoint = new PointImplPassThrough<>();
        integerPoint.setX(10);
        integerPoint.setY(10);
        System.out.println(integerPoint);

        // 创建一个 Float 类型 的 Point 对象
        PointImplPassThrough<Float> floatPoint = new PointImplPassThrough<>();
        floatPoint.setX(20f);
        floatPoint.setY(20f);
        System.out.println(floatPoint);

        // 创建一个 Object 类型 的 Point 对象
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
