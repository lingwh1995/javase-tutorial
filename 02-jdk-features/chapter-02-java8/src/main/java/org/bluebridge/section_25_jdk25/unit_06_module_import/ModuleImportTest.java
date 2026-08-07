package org.bluebridge.section_25_jdk25.unit_06_module_import;

import org.junit.Test;

/**
 * JDK 25 模块导入声明测试(PREVIEW 预览特性)
 *
 * 模块导入声明(Module Import Declarations, JEP 486, 第三次预览)
 * 是 JDK 25 的 PREVIEW 预览特性, 编译和运行都需要 --enable-preview 参数。
 *
 * 模块导入允许使用 import module 模块名; 语法一次性导入整个模块的所有导出包,
 * 无需逐一声明 import 语句。主要优势:
 *   1. 简化导入: 使用 import module java.base; 替代大量逐包 import
 *   2. 按需导入: 只导入模块中导出的公共类型, 不会导入模块内部类型
 *   3. 与现有导入共存: 模块导入可以与普通 import 共存
 *   4. 解决多导入繁琐问题: 调用同一个模块多个包时无需逐行 import
 *
 * 演化历程:
 *   - JDK 23: JEP 476 第一次预览
 *   - JDK 24: 第二次预览
 *   - JDK 25: JEP 486 第三次预览
 *   - 待定: 最终转正
 *
 * @author lingwh
 * @date 2026/08/06 18:21
 */
public class ModuleImportTest {

    // JDK 25 PREVIEW 特性，需要 --enable-preview
    // 实际使用时取消注释以下行:
    // import module java.base;

    /**
     * 测试模块导入声明的基本概念(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 模块导入声明使用 import module 关键字
     */
    @Test
    public void testModuleImportConcept_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        System.out.println("===== 模块导入声明概念 =====");
        System.out.println("import module java.base;  // 导入 java.base 模块所有导出包");
        System.out.println("--------------------------------------");
        System.out.println("java.base 模块包含的常用包:");
        System.out.println("  - java.lang       (自动导入)");
        System.out.println("  - java.util       (集合框架)");
        System.out.println("  - java.io         (输入输出)");
        System.out.println("  - java.nio        (NIO 缓冲)");
        System.out.println("  - java.math       (BigDecimal, BigInteger)");
        System.out.println("  - java.time       (日期时间 API)");
        System.out.println("  - java.net        (网络编程)");
        System.out.println("  - java.text       (文本格式化)");
        System.out.println("  - java.security   (安全框架)");
        System.out.println("--------------------------------------");
        System.out.println("使用 import module java.base; 后,");
        System.out.println("可以直接使用 List、Map、File、LocalDate 等类型,");
        System.out.println("无需逐一声明 import 语句。");
    }

    /**
     * 测试模块导入实际使用场景(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 演示使用模块导入后可以简化代码
     */
    @Test
    public void testModuleImportUsage_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        System.out.println("===== 模块导入使用场景 =====");

        System.out.println("传统方式需要导入的包:");
        System.out.println("  import java.util.List;");
        System.out.println("  import java.util.ArrayList;");
        System.out.println("  import java.util.Map;");
        System.out.println("  import java.util.HashMap;");
        System.out.println("  import java.util.stream.Collectors;");
        System.out.println("  import java.time.LocalDate;");
        System.out.println("  import java.time.format.DateTimeFormatter;");
        System.out.println("--------------------------------------");
        System.out.println("使用模块导入后:");
        System.out.println("  import module java.base;");
        System.out.println("  // 以上所有类型都可以直接使用");
        System.out.println("--------------------------------------");

        // 使用完整限定名演示功能
        java.util.List<String> list = java.util.List.of("Java", "Python", "Go");
        System.out.println("集合示例: " + list);

        java.time.LocalDate today = java.time.LocalDate.now();
        System.out.println("日期示例: " + today);

        java.math.BigDecimal decimal = new java.math.BigDecimal("3.14159");
        System.out.println("高精度数字示例: " + decimal);
    }

    /**
     * 测试模块导入与普通导入的对比(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 模块导入的优势在于减少 import 语句数量
     */
    @Test
    public void testModuleImportCompare_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        System.out.println("===== 传统导入 vs 模块导入对比 =====");
        System.out.println();
        System.out.println("传统方式 (需要 6 行 import):");
        System.out.println("  import java.util.List;");
        System.out.println("  import java.util.ArrayList;");
        System.out.println("  import java.util.Map;");
        System.out.println("  import java.util.HashMap;");
        System.out.println("  import java.io.File;");
        System.out.println("  import java.nio.file.Path;");
        System.out.println();
        System.out.println("模块导入方式 (1 行 import):");
        System.out.println("  import module java.base;");
        System.out.println();
        System.out.println("===== 适用场景 =====");
        System.out.println("1. 快速原型开发 - 不需要关心具体包名");
        System.out.println("2. 教学示例代码 - 减少 import 噪音");
        System.out.println("3. 小型工具类 - 使用 java.base 多个包");
        System.out.println("4. 脚本风格代码 - 快速编写一次性脚本");
        System.out.println();
        System.out.println("===== 注意事项 =====");
        System.out.println("1. 模块导入是 PREVIEW 特性, 需要 --enable-preview");
        System.out.println("2. 模块导入只导入导出包, 不导入模块内部包");
        System.out.println("3. 模块导入可以与普通 import 共存");
        System.out.println("4. 大型项目中建议仍然使用显式 import 以提高可读性");
    }

    /**
     * 测试模块导入对不同模块的支持(PREVIEW)
     * JDK 25 PREVIEW 特性，需要 --enable-preview
     * 除了 java.base 外, 还可以导入其他模块
     */
    @Test
    public void testModuleImportOtherModules_Preview() {
        // JDK 25 PREVIEW 特性，需要 --enable-preview
        System.out.println("===== 其他可导入模块 =====");
        System.out.println();
        System.out.println("import module java.sql;       // java.sql, javax.sql");
        System.out.println("import module java.xml;       // javax.xml, org.w3c.dom");
        System.out.println("import module java.desktop;   // javax.swing, java.awt");
        System.out.println("import module java.net.http;  // java.net.http");
        System.out.println("import module java.scripting; // javax.script");
        System.out.println("import module java.smartcardio; // javax.smartcardio");
        System.out.println();
        System.out.println("这些模块导入声明可以一次性导入整个模块的所有导出包,");
        System.out.println("极大简化多包导入的代码量。");
    }
}
