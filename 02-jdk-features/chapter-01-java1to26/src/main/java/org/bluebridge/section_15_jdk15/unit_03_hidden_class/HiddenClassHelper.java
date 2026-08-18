package org.bluebridge.section_15_jdk15.unit_03_hidden_class;

/**
 * JDK 15 隐藏类测试辅助类(顶层类)
 *
 * 实际隐藏类一般由框架运行时动态生成字节码, 此处提供一个顶层类作为模板,
 * 其编译后的字节码会被 HiddenClassTest 通过 defineHiddenClass() 加载为隐藏类。
 *
 * 注意: 模板类必须是独立的顶层类, 不能是嵌套类。嵌套类的字节码带有
 * InnerClasses 属性(声明与宿主类的嵌套关系), 作为隐藏类加载时会与隐藏类名
 * 不匹配, 抛出 IncompatibleClassChangeError。
 *
 * @author lingwh
 * @date 2026/08/18 19:05
 */
public class HiddenClassHelper {

    /**
     * 演示方法, 验证隐藏类实例可以正常调用方法
     */
    public String sayHello() {
        return "Hello from HiddenClass!";
    }

    @Override
    public String toString() {
        return "HiddenClassHelper instance";
    }
}
