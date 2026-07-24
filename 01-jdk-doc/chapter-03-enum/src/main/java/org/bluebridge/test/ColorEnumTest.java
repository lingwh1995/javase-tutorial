package org.bluebridge.test;

import org.bluebridge.enumeration.ColorEnum;
import org.junit.Test;

/**
 * ColorEnum 测试
 *
 * @author lingwh
 * @date 2026/4/23 16:29
 */
public class ColorEnumTest {

    /**
     * 枚举 api
     * name() ：返回枚举的名称
     * ordinal() ：枚举的下标，下标从0开始
     * valueOf(Class<T> enumType, String name) ：得到枚举的对象
     *
     * 还有两个方法，都是这两个方法不在api里面，编译的时候生成两个方法
     * valueof(String name) 转换枚举对象
     * values() 获得所有枚举对象数组
     */

    /**
     * 测试枚举对象api：知道枚举的对象,得到枚举对象的名称和下标
     * name()：获取枚举对象名称
     * ordinal()：获取枚举对象下标
     */
    @Test
    public void testEnumNameAndOrdinalApi() {
        // 得到枚举对象
        ColorEnum red = ColorEnum.RED;
        // 得到枚举名称
        String name = red.name();
        System.out.println("枚举名称:" + name);
        // 得到枚举下标
        int ordinal = red.ordinal();
        System.out.println("枚举下标:" + ordinal);
    }

    /**
     * 测试根据枚举名称获取枚举对象：知道枚举的名称,得到枚举的对象和下标
     * valueOf()：根据枚举对象的名称获取枚举对象
     */
    @Test
    public void testEnumValueOfApi() {
        // 得到枚举名称
        String nameGreen = "GREEN";
        // 得到枚举对象，方法一
        ColorEnum green = ColorEnum.valueOf(nameGreen);
        // 得到枚举对象，方法二
        // Color green = Color.valueOf(Color.class, nameGreen);
        // 得到下标
        int ordinal = green.ordinal();
        System.out.println("枚举下标:" + ordinal);
    }

    /**
     * 测试获取所有枚举对象：一次性获取所有枚举对象，并且根据枚举下标，得到枚举名称和对象
     * values()：获取所有的枚举对象
     */
    @Test
    public void testEnumValuesApi() {
        // 得到枚举下标
        int idx = 2;
        // 根据下标得到枚举对象
        // 1.得到枚举数组
        ColorEnum[] colors = ColorEnum.values();
        // 2.根据下标得到对象
        ColorEnum yellow = colors[idx];

        // 得到枚举名称
        System.out.println("枚举名称:" + yellow.name());
    }
}
