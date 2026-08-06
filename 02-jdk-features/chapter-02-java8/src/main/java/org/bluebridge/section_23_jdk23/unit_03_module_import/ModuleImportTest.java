package org.bluebridge.section_23_jdk23.unit_03_module_import;

import org.junit.Test;

/**
 * JDK 23 模块导入声明测试（PREVIEW 预览特性）
 *
 * 模块导入声明(Module Import Declarations, JEP 476) 是 JDK 23
 * 的 PREVIEW 预览特性, 编译和运行都需要 --enable-preview 参数。
 *
 * 模块导入允许使用 `import module 模块名;` 语法一次性导入整个模块的所有导出包,
 * 无需逐一声明 import 语句。主要优势:
 *   1. 简化导入: 使用 `import module java.base;` 替代大量逐包 import
 *   2. 按需导入: 只导入模块中导出的公共类型, 不会导入模块内部类型
 *   3. 与现有导入共存: 模块导入可以与普通 import 共存
 *   4. 解决多导入繁琐问题: 调用同一个模块多个包时无需逐行 import
 *
 * 注意: 本文件使用 JDK 23 PREVIEW 特性的真实语法编写,
 *       编译命令: javac --enable-preview --release 23 ModuleImportTest.java
 *       运行命令: java --enable-preview ModuleImportTest
 *
 * @author lingwh
 * @date 2026/08/05 19:11
 */

// JDK 23 PREVIEW 特性，需要 --enable-preview
// import module java.base;  // 模块导入声明, 导入 java.base 模块的所有导出包
// 注意: 由于当前 IDE 可能不支持模块导入语法, 编译时需要 --enable-preview --release 23
// 这里使用注释形式展示, 实际使用时取消注释即可

public class ModuleImportTest {

    /// 测试模块导入声明的基本概念(PREVIEW)
    ///
    /// JDK 23 PREVIEW 特性，需要 --enable-preview
    /// 模块导入声明使用 `import module` 关键字, 格式为:
    /// ```java
    /// import module java.base;
    /// ```
    /// 导入后可以直接使用 java.base 模块中所有导出包的类型,
    /// 无需额外 import java.util.List, java.io.File 等。
    @Test
    public void testModuleImportConcept_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        // 如果使用了 import module java.base; 则无需以下 import 语句:
        //   import java.util.List;
        //   import java.util.Map;
        //   import java.io.File;
        //   import java.time.LocalDate;
        //
        // 这里显式使用完整限定名来演示, 实际使用模块导入后可以省略包名

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

    /// 测试模块导入实际使用场景(PREVIEW)
    ///
    /// JDK 23 PREVIEW 特性，需要 --enable-preview
    /// 演示使用 `import module java.base;` 后可以简化代码。
    /// 注意: 本方法使用完整限定名展示, 实际使用模块导入后可以省略包名。
    ///
    /// 传统方式 vs 模块导入方式:
    ///
    /// | 传统方式 | 模块导入方式 |
    /// |----------|-------------|
    /// | import java.util.List; | import module java.base; |
    /// | import java.util.Map;  | (无需额外导入) |
    /// | import java.util.stream.Collectors; | (无需额外导入) |
    @Test
    public void testModuleImportUsage_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        // 演示使用模块导入后可以省略的 import 语句
        System.out.println("===== 模块导入使用场景 =====");

        // 没有使用模块导入时, 需要显式 import 以下类:
        // import java.util.List;
        // import java.util.Map;
        // import java.util.ArrayList;
        // import java.util.HashMap;
        // import java.util.stream.Collectors;
        // import java.time.LocalDate;
        // import java.time.format.DateTimeFormatter;

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

    /// 测试模块导入与普通导入的对比(PREVIEW)
    ///
    /// JDK 23 PREVIEW 特性，需要 --enable-preview
    /// 模块导入的优势在于:
    /// 1. 减少 import 语句数量
    /// 2. 不需要记住每个类型所在的包名
    /// 3. 代码更简洁, 特别适合使用同一模块多个包的情况
    ///
    /// 注意: 模块导入只导入模块中声明的导出包, 不会导入未导出的包。
    @Test
    public void testModuleImportCompare_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
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

    /// 测试模块导入对不同模块的支持(PREVIEW)
    ///
    /// JDK 23 PREVIEW 特性，需要 --enable-preview
    /// 除了 `java.base` 外, 还可以导入其他模块:
    /// ```java
    /// import module java.sql;        // JDBC 相关
    /// import module java.xml;        // XML 处理
    /// import module java.desktop;    // Swing/AWT 桌面应用
    /// import module java.net.http;   // HTTP Client
    /// ```
    @Test
    public void testModuleImportOtherModules_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
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