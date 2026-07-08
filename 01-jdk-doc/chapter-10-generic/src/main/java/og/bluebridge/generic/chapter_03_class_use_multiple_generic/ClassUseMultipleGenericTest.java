package og.bluebridge.generic.chapter_03_class_use_multiple_generic;

import org.junit.Test;

public class ClassUseMultipleGenericTest {

    @Test
    public void testUseMultipleGenericTest() {
        // 创建一个泛型T类型为Integer,U类型为String的Point对象
        Point<Integer, String> integerStringPoint = new Point<>();
        integerStringPoint.setX(10);
        integerStringPoint.setY(10);
        integerStringPoint.setDesc("第一个坐标点");
        System.out.println(integerStringPoint);

        // 创建一个泛型T类型为Float,U类型为String的Point对象
        Point<Float, String> floatStringPoint = new Point<>();
        floatStringPoint.setX(20f);
        floatStringPoint.setY(20f);
        floatStringPoint.setDesc("第二个坐标点");
        System.out.println(floatStringPoint);

        // 创建一个泛型T类型为Object,U类型为String的Point对象
        Point<Object, String> objectStringPoint = new Point<>();
        objectStringPoint.setX(30);
        objectStringPoint.setY("30");
        objectStringPoint.setDesc("第三个坐标点");
        System.out.println(objectStringPoint);
    }

}
