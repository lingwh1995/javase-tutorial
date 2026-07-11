package og.bluebridge.generic.chapter_01_nouse_generic;

import org.junit.Test;

/**
 * @author lingwh
 * @desc 不使用泛型测试
 * @date 2026/7/9 00:00
 */
public class NoUseGenericTest {

    @Test
    public void testNoUseGeneric() {
        // 创建一个IntegerPoint对象，x和y属性的值是 Integer类型
        IntegerPoint integerPoint = new IntegerPoint();
        integerPoint.setX(10);
        integerPoint.setY(10);
        System.out.println(integerPoint);

        // 创建一个FloatPoint对象，x和y属性的值是 Float类型
        FloatPoint floatPoint = new FloatPoint();
        floatPoint.setX(20f);
        floatPoint.setY(20f);
        System.out.println(floatPoint);

        // 创建一个ObjectPoint对象，x和y属性的值是 Object类型
        ObjectPoint objectPoint = new ObjectPoint();
        objectPoint.setX(30);
        objectPoint.setY("30");
        System.out.println(objectPoint);
    }
}
