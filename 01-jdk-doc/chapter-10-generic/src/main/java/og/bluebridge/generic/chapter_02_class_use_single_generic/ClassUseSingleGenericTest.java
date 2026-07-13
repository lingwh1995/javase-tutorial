package og.bluebridge.generic.chapter_02_class_use_single_generic;

import org.junit.Test;

/**
 * 使用单个泛型测试
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
public class ClassUseSingleGenericTest {

    @Test
    public void testUseSingleGeneric() {
        // 创建一个泛型为 Integer 的Point对象
        Point<Integer> integerPoint = new Point<>();
        integerPoint.setX(10);
        integerPoint.setY(10);
        System.out.println(integerPoint);

        // 创建一个泛型为 Float 的Point对象
        Point<Float> floatPoint = new Point<>();
        floatPoint.setX(20f);
        floatPoint.setY(20f);
        System.out.println(floatPoint);

        // 创建一个泛型为 Object 的Point对象
        Point<Object> objectPoint = new Point<>();
        objectPoint.setX(30);
        objectPoint.setY("30");
        System.out.println(objectPoint);
    }
}
