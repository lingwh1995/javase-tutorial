package org.bluebridge.lang3;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.junit.Test;

/**
 * @author lingwh
 * @desc 类工具类测试类
 * @date 2025/11/7 12:48
 */
@Slf4j
public class ClassUtilsTest {

    /**
     * 获取类名
     */
    @Test
    public void testGetClassName() {
        // 获取不包含包名的简单类名
        String shortClassName = ClassUtils.getShortClassName(String.class);
        log.info("shortClassName: {}", shortClassName);
        shortClassName = ClassUtils.getShortClassName(Map.class);
        log.info("shortClassName: {}", shortClassName);
    }

    /**
     * 获取包名
     */
    @Test
    public void testGetPackageName() {
        // 获取不包含包名的简单类名
        String packageName = ClassUtils.getPackageName(String.class);
        log.info("packageName: {}", packageName);
        packageName = ClassUtils.getPackageName(Map.class);
        log.info("packageName: {}", packageName);
    }

    /**
     * 基本类型转换
     */
    @Test
    public void testClassConversion() {
        // 获取包装类对应的基本类型
        Class<?> c = ClassUtils.primitiveToWrapper(Integer.class);
        log.info("c: {}", c);
        c = ClassUtils.primitiveToWrapper(Double.class);
        log.info("c: {}", c);

        // 检查是否为基本类型
        boolean isPrimitiveOrWrapper = ClassUtils.isPrimitiveOrWrapper(int.class);
        log.info("int.class isPrimitiveOrWrapper: {}", isPrimitiveOrWrapper);
        isPrimitiveOrWrapper = ClassUtils.isPrimitiveOrWrapper(Integer.class);
        log.info("Integer.class isPrimitiveOrWrapper: {}", isPrimitiveOrWrapper);
        isPrimitiveOrWrapper = ClassUtils.isPrimitiveOrWrapper(String.class);
        log.info("String.class isPrimitiveOrWrapper: {}", isPrimitiveOrWrapper);
    }
}
