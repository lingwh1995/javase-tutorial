package org.bluebridge.section_06_jdk6.unit_04_annotation;

import org.junit.Test;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

/**
 * JDK 6 注解改进测试
 *
 * JDK 6 在注解方面做了以下改进：
 * 1. @Override 注解可以用于接口方法的实现（之前只能用于覆盖父类方法）
 * 2. @SuppressWarnings 支持 value 属性指定要抑制的警告类型
 * 3. 可插拔注解处理 API（Pluggable Annotation Processing API，JSR 269）
 *    - 在编译期间处理注解，生成代码或进行验证
 *    - 核心接口：javax.annotation.processing.Processor
 *    - 核心类：javax.annotation.processing.AbstractProcessor
 *
 * @author lingwh
 * @date 2026/08/05 19:07
 */
public class AnnotationImprovementsTest {

    // ======================== 自定义注解定义 ========================

    /**
     * 自定义注解：用于标记需要特殊处理的类
     */
    @Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface SpecialAnnotation {
        String value() default "";

        int priority() default 0;
    }

    /**
     * 自定义注解：用于标记废弃的 API
     */
    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface DeprecatedApi {
        String since() default "";

        String replacement() default "";
    }

    // ======================== 接口定义 ========================

    /**
     * 示例接口：用于演示 @Override 在接口方法实现上的使用
     */
    interface Service {
        /**
         * 执行服务逻辑
         */
        void execute();

        /**
         * 获取服务名称
         */
        String getName();

        /**
         * 默认方法（JDK 8 引入，此处仅用于展示接口方法定义）
         */
        default String getDescription() {
            return "这是一个服务接口";
        }
    }

    // ======================== 接口实现类 ========================

    /**
     * 接口实现类：演示 JDK 6 中 @Override 可用于接口方法实现
     * JDK 6 之前，@Override 只能用于覆盖父类方法（不能用于接口方法实现）
     * JDK 6 放宽了这一限制，@Override 也可用于接口方法的实现
     */
    static class ServiceImpl implements Service {

        /**
         * 使用 @Override 注解标记接口方法实现
         * JDK 6 开始支持在接口方法实现上使用 @Override
         */
        @Override
        public void execute() {
            System.out.println("ServiceImpl.execute() 被调用");
        }

        /**
         * 使用 @Override 注解标记接口方法实现
         */
        @Override
        public String getName() {
            return "ServiceImpl";
        }

        /**
         * 使用 @Override 注解标记接口默认方法的重写
         */
        @Override
        public String getDescription() {
            return "这是 ServiceImpl 的实现";
        }
    }

    // ======================== 测试方法 ========================

    /**
     * 测试 @Override 注解在接口方法实现上的使用（JDK 6 改进）
     */
    @Test
    public void testOverrideAnnotationOnInterfaceMethod() {
        System.out.println("测试 @Override 在接口方法实现上的使用: ");
        Service service = new ServiceImpl();
        service.execute();
        System.out.println("  服务名称: " + service.getName());
        System.out.println("  服务描述: " + service.getDescription());
        System.out.println("--------------------------------------");
        System.out.println("JDK 6 之前: @Override 仅能用于覆盖父类方法");
        System.out.println("JDK 6 改进: @Override 也可以用于接口方法的实现");
        System.out.println("  这有助于编译器检查是否正确定义了接口方法签名");
        System.out.println("  如果接口方法签名变更，实现类会立即发现编译错误");
    }

    /**
     * 测试 @SuppressWarnings 注解的 value 属性用法
     */
    @Test
    @SuppressWarnings(value = {"unchecked", "rawtypes", "unused"})
    public void testSuppressWarningsValue() {
        System.out.println("测试 @SuppressWarnings 的 value 属性: ");
        // 未检查的转换（@SuppressWarnings("unchecked") 抑制警告）
        @SuppressWarnings("unchecked")
        java.util.List<String> list = (java.util.List<String>) (java.util.List<?>) new java.util.ArrayList();
        list.add("抑制未检查转换警告");
        System.out.println("  @SuppressWarnings(\"unchecked\") 抑制了未检查转换警告");
        // 原始类型使用（@SuppressWarnings("rawtypes") 抑制警告）
        @SuppressWarnings("rawtypes")
        java.util.List rawList = new java.util.ArrayList();
        rawList.add("原始类型");
        System.out.println("  @SuppressWarnings(\"rawtypes\") 抑制了原始类型警告");
        // 未使用变量（@SuppressWarnings("unused") 抑制警告）
        @SuppressWarnings("unused")
        String unusedVar = "未使用的变量（抑制警告）";
        System.out.println("  @SuppressWarnings(\"unused\") 抑制了未使用变量警告");
        System.out.println("--------------------------------------");
        System.out.println("常见的 @SuppressWarnings 值: ");
        System.out.println("  all      - 抑制所有警告");
        System.out.println("  unchecked - 抑制未检查转换警告");
        System.out.println("  rawtypes - 抑制原始类型使用警告");
        System.out.println("  unused   - 抑制未使用代码警告");
        System.out.println("  deprecation - 抑制废弃 API 使用警告");
        System.out.println("  serial   - 抑制 serialVersionUID 警告");
    }

    /**
     * 测试可插拔注解处理 API 的概念和核心接口
     * 展示 javax.annotation.processing 包的结构和使用方式
     */
    @Test
    public void testPluggableAnnotationProcessing() {
        System.out.println("可插拔注解处理 API（JSR 269）: ");
        System.out.println("  JDK 6 引入了 javax.annotation.processing 包");
        System.out.println("  允许在编译时处理注解，生成代码或验证");
        System.out.println("--------------------------------------");
        System.out.println("核心接口和类: ");
        System.out.println("  Processor        - 注解处理器接口");
        System.out.println("  AbstractProcessor - 注解处理器抽象基类");
        System.out.println("  RoundEnvironment - 编译轮次环境");
        System.out.println("  SupportedAnnotationTypes - 支持的注解类型");
        System.out.println("  SupportedSourceVersion   - 支持的源码版本");
        System.out.println("--------------------------------------");
        System.out.println("工作流程: ");
        System.out.println("  1. 编译器发现注解");
        System.out.println("  2. 调用 Processor.init(ProcessingEnvironment) 初始化");
        System.out.println("  3. 调用 Processor.process(Set<? extends TypeElement>, RoundEnvironment)");
        System.out.println("  4. 处理器可以生成新的 Java 源文件或类文件");
        System.out.println("  5. 如果生成了新文件，编译器会启动新一轮处理");
        System.out.println("--------------------------------------");
        // 演示自定义注解处理器的基本结构
        System.out.println("自定义注解处理器示例结构: ");
        System.out.println("  @SupportedAnnotationTypes(\"org.bluebridge..*\")");
        System.out.println("  @SupportedSourceVersion(SourceVersion.RELEASE_6)");
        System.out.println("  public class MyProcessor extends AbstractProcessor {");
        System.out.println("      @Override");
        System.out.println("      public boolean process(Set<? extends TypeElement> annotations,");
        System.out.println("                             RoundEnvironment roundEnv) {");
        System.out.println("          // 处理注解逻辑");
        System.out.println("          return true;");
        System.out.println("      }");
        System.out.println("  }");
    }

    /**
     * 测试自定义注解处理器的注册和使用方式
     */
    @Test
    public void testCustomAnnotationProcessor() {
        System.out.println("自定义注解处理器注册方式: ");
        System.out.println("  方式一: 通过命令行 -processor 参数指定");
        System.out.println("    javac -processor com.example.MyProcessor SourceFile.java");
        System.out.println("  方式二: 通过 SPI 机制注册（META-INF/services/javax.annotation.processing.Processor）");
        System.out.println("    在 META-INF/services/ 目录下创建文件:");
        System.out.println("    文件名: javax.annotation.processing.Processor");
        System.out.println("    文件内容: com.example.MyProcessor");
        System.out.println("--------------------------------------");
        System.out.println("自定义注解处理器示例: 处理 @SpecialAnnotation 注解");
        // 模拟处理器处理逻辑
        simulateProcessor(ServiceImpl.class);
        System.out.println("--------------------------------------");
        System.out.println("可插拔注解处理 API 的应用场景: ");
        System.out.println("  1. Lombok 的 @Getter/@Setter 等注解处理");
        System.out.println("  2. 编译时验证（如 @NonNull 参数检查）");
        System.out.println("  3. 代码生成（如生成 Builder 模式代码）");
        System.out.println("  4. 依赖注入框架的注解处理");
    }

    /**
     * 模拟注解处理器的处理逻辑
     */
    private void simulateProcessor(Class<?> clazz) {
        System.out.println("  处理类: " + clazz.getName());
        // 获取类上的所有注解
        for (java.lang.annotation.Annotation annotation : clazz.getAnnotations()) {
            System.out.println("  发现注解: " + annotation.annotationType().getName());
        }
        // 模拟处理器生成代码
        System.out.println("  模拟处理器生成代码: ");
        System.out.println("    public class ServiceImpl_Generated {");
        System.out.println("        public static ServiceImpl create() {");
        System.out.println("            return new ServiceImpl();");
        System.out.println("        }");
        System.out.println("    }");
    }

    /**
     * 测试 @SuppressWarnings 在方法上的多种使用方式
     */
    @Test
    @SuppressWarnings("deprecation")
    public void testSuppressWarningsVariations() {
        System.out.println("@SuppressWarnings 的多种使用方式: ");
        // 方式一: 简写形式（当 value 属性是唯一需要赋值的属性时）
        @SuppressWarnings("unchecked")
        java.util.List<String> way1 = (java.util.List<String>) (java.util.List<?>) new java.util.ArrayList();
        System.out.println("  方式一: @SuppressWarnings(\"unchecked\") - 简写形式");
        // 方式二: 完整形式（显式指定 value 属性名）
        @SuppressWarnings(value = "unused")
        String way2 = "完整形式";
        System.out.println("  方式二: @SuppressWarnings(value = \"unused\") - 完整形式");
        // 方式三: 抑制多个警告（使用数组）
        @SuppressWarnings({"unchecked", "rawtypes"})
        java.util.List way3 = new java.util.ArrayList();
        way3.add("多个警告");
        System.out.println("  方式三: @SuppressWarnings({\"unchecked\", \"rawtypes\"}) - 多个警告");
        // 方式四: 在类级别上使用
        System.out.println("  方式四: 在类或方法上使用 @SuppressWarnings");
        System.out.println("--------------------------------------");
        System.out.println("注意: @SuppressWarnings 应谨慎使用，");
        System.out.println("  抑制警告可能会掩盖潜在的问题。");
        System.out.println("  良好的实践是: 尽量解决警告而不是抑制它们。");
    }
}