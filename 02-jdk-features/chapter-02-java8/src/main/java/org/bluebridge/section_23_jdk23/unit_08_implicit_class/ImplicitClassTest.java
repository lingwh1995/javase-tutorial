package org.bluebridge.section_23_jdk23.unit_08_implicit_class;

import org.junit.Test;

/**
 * JDK 23 隐式声明类测试(PREVIEW 预览特性)
 *
 * 隐式声明类(Implicitly Declared Classes, JEP 477, 第三次预览)
 * 是 JDK 23 的 PREVIEW 预览特性, 编译和运行都需要 --enable-preview 参数。
 *
 * 隐式声明类允许在没有显式类声明的情况下编写 Java 程序,
 * 编译器会自动为源代码生成一个隐式类。主要特性:
 *   1. 无需显式声明类: 可以直接编写 main 方法和其他顶级元素
 *   2. 自动导入: 隐式类自动导入 java.base 模块中的所有类型
 *   3. 简化学习曲线: 初学者无需理解类声明即可编写程序
 *   4. 增强脚本风格: 更适合小型程序和脚本式开发
 *
 * 演化历程:
 *   - JDK 21: JEP 445 第一次预览(未命名类)
 *   - JDK 22: JEP 458 第二次预览(隐式声明类)
 *   - JDK 23: JEP 477 第三次预览
 *   - JDK 25: 转正(最终确定的 API)
 *
 * @author lingwh
 * @date 2026/08/06 18:20
 */
public class ImplicitClassTest {

    /**
     * 测试隐式声明类的概念说明(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 隐式声明类允许编写没有显式类声明的源文件,
     * 编译器自动生成匿名类, 并自动导入 java.base 模块
     */
    @Test
    public void testImplicitClassConcept_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        // 隐式声明类允许直接编写以下代码:
        //
        // void main() {
        //     System.out.println("Hello, World!");
        // }
        //
        // 编译器会自动补全为:
        //
        // class XXX {
        //     void main() {
        //         System.out.println("Hello, World!");
        //     }
        // }
        System.out.println("===== 隐式声明类概念 =====");
        System.out.println();
        System.out.println("传统方式:");
        System.out.println("  public class HelloWorld {");
        System.out.println("      public static void main(String[] args) {");
        System.out.println("          System.out.println(\"Hello, World!\");");
        System.out.println("      }");
        System.out.println("  }");
        System.out.println();
        System.out.println("隐式声明类方式:");
        System.out.println("  void main() {");
        System.out.println("      System.out.println(\"Hello, World!\");");
        System.out.println("  }");
        System.out.println();
        System.out.println("隐式声明类的优势:");
        System.out.println("  1. 无需显式类声明, 减少样板代码");
        System.out.println("  2. 自动导入 java.base 模块");
        System.out.println("  3. 更适合初学者和脚本式编程");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试隐式声明类的自动导入特性(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 隐式类自动导入 java.base 模块中的所有类型,
     * 无需显式 import 语句即可使用 List, Map, File 等类
     */
    @Test
    public void testImplicitClassAutoImport_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        // 在隐式声明类中, 以下类型无需 import 即可使用:
        //   java.util.List, java.util.Map, java.io.File
        //   java.time.LocalDate, java.math.BigDecimal 等
        System.out.println("===== 隐式类自动导入 =====");
        System.out.println();
        System.out.println("隐式声明类自动导入 java.base 模块的所有导出包:");
        System.out.println("  - java.lang.*       (自动导入)");
        System.out.println("  - java.util.*       (自动导入)");
        System.out.println("  - java.io.*         (自动导入)");
        System.out.println("  - java.math.*       (自动导入)");
        System.out.println("  - java.time.*       (自动导入)");
        System.out.println("  - java.net.*        (自动导入)");
        System.out.println();
        System.out.println("这意味着在隐式类中可以直接使用:");
        System.out.println("  List.of(1, 2, 3)");
        System.out.println("  Map.of(\"key\", \"value\")");
        System.out.println("  LocalDate.now()");
        System.out.println("  BigDecimal.ONE");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试隐式声明类的 main 方法简化(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 隐式类的 main 方法不需要 public static void 修饰,
     * 直接写 void main() 或 main() 即可
     */
    @Test
    public void testImplicitClassSimplifiedMain_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        System.out.println("===== 简化的 main 方法 =====");
        System.out.println();
        System.out.println("传统方式:");
        System.out.println("  public static void main(String[] args) { ... }");
        System.out.println();
        System.out.println("隐式类方式:");
        System.out.println("  void main() { ... }");
        System.out.println("  或");
        System.out.println("  void main(String[] args) { ... }");
        System.out.println();
        System.out.println("在隐式声明类中, 编译器会自动生成:");
        System.out.println("  - 隐式类包装");
        System.out.println("  - public static void main(String[] args) 入口");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试隐式声明类的实例方法和字段(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 隐式类中的字段和方法都是实例成员, 无需 static 修饰
     */
    @Test
    public void testImplicitClassInstanceMembers_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        System.out.println("===== 隐式类的实例成员 =====");
        System.out.println();
        System.out.println("在隐式声明类中:");
        System.out.println("  1. 所有字段都是实例字段(无需 static)");
        System.out.println("  2. 所有方法都是实例方法(无需 static)");
        System.out.println("  3. 编译器自动生成实例并调用");
        System.out.println();
        System.out.println("示例:");
        System.out.println("  String message = \"Hello\";  // 实例字段");
        System.out.println();
        System.out.println("  void sayHello() {           // 实例方法");
        System.out.println("      System.out.println(message);");
        System.out.println("  }");
        System.out.println("--------------------------------------");
    }

    /**
     * 测试隐式声明类与传统类的对比(PREVIEW)
     * JDK 23 PREVIEW 特性，需要 --enable-preview
     * 对比传统类和隐式声明类的代码量差异
     */
    @Test
    public void testImplicitClassComparison_Preview() {
        // JDK 23 PREVIEW 特性，需要 --enable-preview
        System.out.println("===== 传统类 vs 隐式声明类对比 =====");
        System.out.println();
        System.out.println("传统类 (约 15 行):");
        System.out.println("  import java.util.List;");
        System.out.println("  import java.util.ArrayList;");
        System.out.println();
        System.out.println("  public class StringListExample {");
        System.out.println("      public static void main(String[] args) {");
        System.out.println("          var list = new ArrayList<String>();");
        System.out.println("          list.add(\"Java\");");
        System.out.println("          list.add(\"Python\");");
        System.out.println("          System.out.println(list);");
        System.out.println("      }");
        System.out.println("  }");
        System.out.println();
        System.out.println("隐式声明类 (约 5 行):");
        System.out.println("  void main() {");
        System.out.println("      var list = new ArrayList<String>();");
        System.out.println("      list.add(\"Java\");");
        System.out.println("      list.add(\"Python\");");
        System.out.println("      System.out.println(list);");
        System.out.println("  }");
        System.out.println();
        System.out.println("代码量减少约 66%!");
        System.out.println("--------------------------------------");
    }
}
