package og.bluebridge.generic.section_01_nouse_generic;

import org.junit.Test;

/**
 * 不使用泛型测试
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
public class NoUseGenericTest {

    @Test
    public void testNoUseGeneric() {
        // 创建一个 IntegerPoint 对象， x 和 y 属性的值是 Integer 类型
        IntegerPoint integerPoint = new IntegerPoint();
        integerPoint.setX(10);
        integerPoint.setY(10);
        System.out.println(integerPoint);

        // 创建一个 FloatPoint 对象， x 和 y 属性的值是 Float 类型
        FloatPoint floatPoint = new FloatPoint();
        floatPoint.setX(20f);
        floatPoint.setY(20f);
        System.out.println(floatPoint);

        // 创建一个 ObjectPoint 对象， x 和 y 属性的值是 Object 类型
        ObjectPoint objectPoint = new ObjectPoint();
        objectPoint.setX(30);
        objectPoint.setY("30");
        System.out.println(objectPoint);
    }
}
