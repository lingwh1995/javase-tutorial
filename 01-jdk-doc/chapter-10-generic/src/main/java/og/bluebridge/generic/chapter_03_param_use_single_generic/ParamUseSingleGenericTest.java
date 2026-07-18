package og.bluebridge.generic.chapter_03_param_use_single_generic;

import org.junit.Test;

/**
 * 使用单个泛型测试
 *
 * @author lingwh
 * @date 2026/7/8 18:39
 */
public class ParamUseSingleGenericTest {

    @Test
    public void testUseSingleGeneric() {
        // 创建一个泛型 T 类型为 String 的 Point 对象
        Point<String> stringPoint = new Point<>();
        stringPoint.setDesc("这是一个字符串点");
        System.out.println(stringPoint.getDesc());
    }
}
