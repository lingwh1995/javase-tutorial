package org.bluebridge.section_06_jdk6.unit_02_compiler;

import org.junit.Test;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.io.StringWriter;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * JDK 6 Compiler API（JSR 199）测试
 *
 * JDK 6 引入了 javax.tools.JavaCompiler API，允许在 Java 程序中直接调用 Java 编译器，
 * 动态编译 Java 源代码。这对于需要动态生成和执行代码的应用（如 JSP 引擎、代码生成器等）非常有用。
 *
 * 核心接口和类：
 * - JavaCompiler：Java 编译器的主入口，通过 ToolProvider.getSystemJavaCompiler() 获取
 * - StandardJavaFileManager：标准文件管理器，管理编译单元的输入/输出
 * - DiagnosticCollector：收集编译过程中的诊断信息（错误、警告等）
 * - JavaFileObject：表示一个 Java 文件对象，可以是从内存中构造的源代码
 *
 * @author lingwh
 * @date 2026/08/05 19:06
 */
public class CompilerAPITest {

    /**
     * 测试获取 JavaCompiler 实例
     */
    @Test
    public void testGetJavaCompiler() {
        // 通过 ToolProvider 获取系统 Java 编译器
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler != null) {
            System.out.println("成功获取 JavaCompiler 实例");
            System.out.println("  编译器名称: " + compiler.getClass().getName());
            System.out.println("  支持的源码版本: 通过 StandardJavaFileManager 管理");
            // 获取编译器支持的选项
            System.out.println("  运行编译器的标准命令: javac");
        } else {
            System.out.println("未获取到 JavaCompiler 实例（可能未在 JDK 环境中运行）");
            System.out.println("提示: Compiler API 需要 JDK 环境，JRE 中不包含");
        }
        System.out.println("--------------------------------------");
        // 说明 Compiler API 的核心组件
        System.out.println("JavaCompiler 核心组件: ");
        System.out.println("  1. DiagnosticCollector - 收集编译诊断信息");
        System.out.println("  2. StandardJavaFileManager - 管理文件输入输出");
        System.out.println("  3. JavaFileObject - 表示编译单元（源代码文件）");
        System.out.println("  4. CompilationTask - 表示一次编译任务");
    }

    /**
     * 测试使用 JavaCompiler 和 StandardJavaFileManager 编译本地文件
     * 注意：此方法演示 StandardJavaFileManager 的获取和配置流程
     */
    @Test
    public void testCompilerWithFileManager() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.out.println("跳过测试：当前环境没有可用的 JavaCompiler");
            return;
        }
        // 创建 DiagnosticCollector 用于收集编译诊断信息
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
        // 获取标准 Java 文件管理器
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, null, null);
        try {
            // 获取文件管理器支持的选项
            System.out.println("StandardJavaFileManager 已创建");
            System.out.println("  支持的选项: " + fileManager.getLocation(javax.tools.StandardLocation.CLASS_OUTPUT));
            // 设置输出目录（如果指定）
            // fileManager.setLocation(StandardLocation.CLASS_OUTPUT, Arrays.asList(new File("target/classes")));
            System.out.println("  文件管理器可用于管理编译单元的输入/输出路径");
            // 关闭文件管理器
            fileManager.close();
        } catch (IOException e) {
            System.out.println("文件管理器操作异常: " + e.getMessage());
        }
        System.out.println("--------------------------------------");
        // 说明编译流程
        System.out.println("使用 Compiler API 编译 Java 文件的流程: ");
        System.out.println("  1. 获取 JavaCompiler 实例");
        System.out.println("  2. 创建 DiagnosticCollector 收集诊断信息");
        System.out.println("  3. 通过 compiler.getStandardFileManager() 获取文件管理器");
        System.out.println("  4. 获取待编译的 Java 文件对象（JavaFileObject）");
        System.out.println("  5. 调用 compiler.getTask() 创建编译任务");
        System.out.println("  6. 调用 CompilationTask.call() 执行编译");
        System.out.println("  7. 通过 DiagnosticCollector 获取编译结果");
    }

    /**
     * 测试使用 CompilationTask 编译代码并收集诊断信息
     * 演示从内存字符串编译 Java 源代码（不依赖物理文件）
     */
    @Test
    public void testCompileSourceCode() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.out.println("跳过测试：当前环境没有可用的 JavaCompiler");
            return;
        }
        // 创建一个内存中的 Java 源文件对象
        String sourceCode = "package demo;\n"
                + "public class HelloWorld {\n"
                + "    public static void main(String[] args) {\n"
                + "        System.out.println(\"Hello, Compiler API!\");\n"
                + "    }\n"
                + "}";
        // 封装源码为 JavaFileObject
        JavaFileObject sourceFile = new SimpleJavaFileObject(
                URI.create("string:///demo/HelloWorld.java"),
                JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return sourceCode;
            }
        };
        // 收集诊断信息
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
        // 获取文件管理器
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, null, null);
        try {
            // 准备编译单元列表
            List<JavaFileObject> compilationUnits = Arrays.asList(sourceFile);
            // 创建编译任务，指定编译选项为输出警告和错误
            List<String> options = new ArrayList<>();
            options.add("-Xlint:all");
            JavaCompiler.CompilationTask task = compiler.getTask(
                    null,                 // 输出流（null 使用 System.err）
                    fileManager,           // 文件管理器
                    diagnosticCollector,   // 诊断收集器
                    options,               // 编译选项
                    null,                  // 需要注解处理的类名（null 表示无）
                    compilationUnits       // 待编译的源文件
            );
            // 执行编译任务
            boolean success = task.call();
            System.out.println("编译结果: " + (success ? "成功" : "失败"));
            // 输出诊断信息
            System.out.println("诊断信息 (" + diagnosticCollector.getDiagnostics().size() + " 条): ");
            for (javax.tools.Diagnostic<? extends JavaFileObject> diagnostic : diagnosticCollector.getDiagnostics()) {
                String kind = diagnostic.getKind().toString();
                long line = diagnostic.getLineNumber();
                String message = diagnostic.getMessage(null);
                System.out.println("  [" + kind + "] 行 " + line + ": " + message);
            }
            // 关闭文件管理器
            fileManager.close();
        } catch (IOException e) {
            System.out.println("编译过程发生异常: " + e.getMessage());
        }
    }

    /**
     * 测试编译有语法错误的代码，观察诊断信息
     */
    @Test
    public void testCompileWithError() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.out.println("跳过测试：当前环境没有可用的 JavaCompiler");
            return;
        }
        // 包含语法错误的源代码
        String errorSource = "package demo;\n"
                + "public class ErrorClass {\n"
                + "    public static void main(String[] args) {\n"
                + "        System.out.println(\"Missing semicolon\") // 缺少分号\n"
                + "    }\n"
                + "}";
        // 封装为 JavaFileObject
        JavaFileObject sourceFile = new SimpleJavaFileObject(
                URI.create("string:///demo/ErrorClass.java"),
                JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return errorSource;
            }
        };
        // 收集诊断信息
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, null, null);
        try {
            // 创建编译任务
            JavaCompiler.CompilationTask task = compiler.getTask(
                    null, fileManager, diagnosticCollector, null, null, Arrays.asList(sourceFile));
            // 执行编译
            boolean success = task.call();
            System.out.println("编译结果: " + (success ? "成功" : "失败（预期失败，因为代码有语法错误）"));
            // 输出诊断信息（错误详情）
            System.out.println("诊断信息: ");
            for (javax.tools.Diagnostic<? extends JavaFileObject> diagnostic : diagnosticCollector.getDiagnostics()) {
                System.out.println("  级别: " + diagnostic.getKind());
                System.out.println("  行号: " + diagnostic.getLineNumber());
                System.out.println("  列号: " + diagnostic.getColumnNumber());
                System.out.println("  信息: " + diagnostic.getMessage(null));
                System.out.println("  源代码: " + diagnostic.getSource());
                System.out.println("  ---");
            }
            fileManager.close();
        } catch (IOException e) {
            System.out.println("编译过程发生异常: " + e.getMessage());
        }
    }

    /**
     * 测试自定义输出流和编译选项
     */
    @Test
    public void testCompilerOptions() {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            System.out.println("跳过测试：当前环境没有可用的 JavaCompiler");
            return;
        }
        // 创建一个简单的源代码
        String sourceCode = "package demo;\n"
                + "public class Compute {\n"
                + "    public int add(int a, int b) {\n"
                + "        return a + b;\n"
                + "    }\n"
                + "}";
        JavaFileObject sourceFile = new SimpleJavaFileObject(
                URI.create("string:///demo/Compute.java"),
                JavaFileObject.Kind.SOURCE) {
            @Override
            public CharSequence getCharContent(boolean ignoreEncodingErrors) {
                return sourceCode;
            }
        };
        // 使用自定义输出流捕获编译过程中的输出信息
        StringWriter outputStream = new StringWriter();
        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, null, null);
        try {
            // 编译选项：指定源码版本、目标版本、生成所有调试信息
            List<String> options = Arrays.asList(
                    "-source", "6",
                    "-target", "6",
                    "-g:lines,source,vars",
                    "-nowarn"
            );
            JavaCompiler.CompilationTask task = compiler.getTask(
                    outputStream,          // 编译输出定向到 ByteArrayOutputStream
                    fileManager,
                    diagnosticCollector,
                    options,
                    null,
                    Arrays.asList(sourceFile)
            );
            boolean success = task.call();
            System.out.println("使用编译选项 -source 6 -target 6 编译结果: " + (success ? "成功" : "失败"));
            // 输出编译过程的控制台信息
            String compilerOutput = outputStream.toString();
            if (!compilerOutput.isEmpty()) {
                System.out.println("编译器输出: " + compilerOutput);
            }
            // 检查诊断信息
            if (diagnosticCollector.getDiagnostics().isEmpty()) {
                System.out.println("编译无诊断信息（无错误、无警告）");
            } else {
                for (javax.tools.Diagnostic<? extends JavaFileObject> d : diagnosticCollector.getDiagnostics()) {
                    System.out.println("[" + d.getKind() + "] " + d.getMessage(null));
                }
            }
            fileManager.close();
        } catch (IOException e) {
            System.out.println("编译过程发生异常: " + e.getMessage());
        }
    }
}