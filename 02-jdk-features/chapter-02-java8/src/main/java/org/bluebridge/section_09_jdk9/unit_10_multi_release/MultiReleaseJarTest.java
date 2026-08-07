package org.bluebridge.section_09_jdk9.unit_10_multi_release;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

/**
 * JDK 9 多版本 JAR（Multi-Release JAR，JEP 238）测试
 *
 * 演化历程：
 *   JDK 9 引入了多版本 JAR 功能，允许同一个 JAR 包中包含针对不同 JDK 版本的类文件。
 *   当在更高版本的 JDK 上运行时，JVM 会自动加载对应版本的类文件，实现向后兼容。
 *
 * 机制说明：
 *   在 JAR 包的 META-INF/MANIFEST.MF 中设置 "Multi-Release: true" 属性，
 *   然后在 JAR 包中按版本目录结构放置不同版本的类文件：
 *     jar-root/
 *       ├── META-INF/
 *       │   ├── MANIFEST.MF  (包含 Multi-Release: true)
 *       │   └── versions/
 *       │       ├── 9/       (JDK 9 版本的类)
 *       │       ├── 10/      (JDK 10 版本的类)
 *       │       └── 11/      (JDK 11 版本的类)
 *       └── com/example/MyClass.class (基础版本，兼容低版本 JDK)
 *
 * @author lingwh
 * @date 2026/08/06 18:19
 */
public class MultiReleaseJarTest {

    /**
     * 测试多版本 JAR 的核心机制说明
     * 演示多版本 JAR 的工作原理和目录结构
     */
    @Test
    public void testMultiReleaseMechanism() {
        System.out.println("=== 多版本 JAR（Multi-Release JAR）机制 ===");
        System.out.println("JEP 238：Multi-Release JAR Files");
        System.out.println("引入版本：JDK 9");
        System.out.println();

        System.out.println("1. 工作原理：");
        System.out.println("   - JAR 包的 MANIFEST.MF 中声明 Multi-Release: true");
        System.out.println("   - 在 META-INF/versions/ 目录下按版本存放类文件");
        System.out.println("   - JVM 根据当前 JDK 主版本号自动选择对应版本的类");
        System.out.println("   - 低版本 JDK 无法识别多版本 JAR，使用根目录下的类");
        System.out.println();

        System.out.println("2. 目录结构示例：");
        System.out.println("   my-library.jar");
        System.out.println("   ├── META-INF/");
        System.out.println("   │   ├── MANIFEST.MF");
        System.out.println("   │   │   Main-Class: com.example.Main");
        System.out.println("   │   │   Multi-Release: true");
        System.out.println("   │   └── versions/");
        System.out.println("   │       ├── 9/");
        System.out.println("   │       │   └── com/example/MyClass.class");
        System.out.println("   │       ├── 10/");
        System.out.println("   │       │   └── com/example/MyClass.class");
        System.out.println("   │       └── 11/");
        System.out.println("   │           └── com/example/MyClass.class");
        System.out.println("   └── com/example/");
        System.out.println("       └── MyClass.class  (基础版本)");
        System.out.println();

        System.out.println("3. 版本选择规则：");
        System.out.println("   - 运行时 JDK 主版本号 = Runtime.version().feature()");
        System.out.println("   - 从 META-INF/versions/<version>/ 中加载类");
        System.out.println("   - 选择 <= 当前版本的最大版本号");
        System.out.println("   - 找不到对应版本时，使用根目录下的类");
        System.out.println();

        System.out.println("4. 适用场景：");
        System.out.println("   - 库/框架需要同时支持旧版 JDK 和新版 JDK 特性");
        System.out.println("   - 利用新版 JDK 的 API 优化性能，同时保持向后兼容");
        System.out.println("   - 例如：在 JDK 9+ 中使用 Module API，低版本使用 ClassLoader");
    }

    /**
     * 测试 Runtime.Version API：获取当前 JDK 版本信息
     * 多版本 JAR 的类加载机制依赖于 Runtime.Version 判断版本号
     */
    @Test
    public void testRuntimeVersion() {
        System.out.println("=== 当前 JDK 版本信息 ===");
        Runtime.Version version = Runtime.version();

        // 版本信息
        System.out.println("完整版本：" + version);
        System.out.println("feature（主版本号）：" + version.feature());
        System.out.println("interim（中间版本号）：" + version.interim());
        System.out.println("update（更新版本号）：" + version.update());
        System.out.println("patch（补丁版本号）：" + version.patch());
        System.out.println();

        // 版本比较
        System.out.println("版本比较：");
        System.out.println("  version.compareTo(Runtime.Version.parse(\"9\")) = "
                + version.compareTo(Runtime.Version.parse("9")));
        System.out.println("  version.compareTo(Runtime.Version.parse(\"17\")) = "
                + version.compareTo(Runtime.Version.parse("17")));
        System.out.println();

        // 版本解析
        Runtime.Version parsedVersion = Runtime.Version.parse("11.0.2+9");
        System.out.println("解析版本 \"11.0.2+9\"：");
        System.out.println("  feature：" + parsedVersion.feature());
        System.out.println("  interim：" + parsedVersion.interim());
        System.out.println("  update：" + parsedVersion.update());
        System.out.println("  patch：" + parsedVersion.patch());
        System.out.println("  pre/build 信息：" + parsedVersion.pre().orElse("无") + "/"
                + parsedVersion.build().orElse("无"));
    }

    /**
     * 测试 JarFile 的多版本 JAR 支持
     * JDK 9 为 JarFile 新增了多版本 JAR 相关的 API
     */
    @Test
    public void testJarFileMultiReleaseSupport() throws IOException {
        System.out.println("=== JarFile 多版本 JAR 支持 ===");

        // JarFile 的 RuntimeVersion 常量
        System.out.println("JarFile 中的多版本 JAR 相关常量：");
        System.out.println("  MANIFEST_NAME：META-INF/MANIFEST.MF");
        System.out.println("  MULTI_RELEASE：Multi-Release");
        System.out.println("  VERSIONING_DIR：META-INF/versions/");
        System.out.println();

        // 演示 JarFile 的版本感知 API
        System.out.println("JarFile 的版本感知 API：");
        System.out.println("  new JarFile(File file, boolean verify, int mode, Runtime.Version version)");
        System.out.println("  - mode：JarFile.OPEN_READ");
        System.out.println("  - version：指定版本，如 Runtime.version()");
        System.out.println("  - 当 version != null 且 JAR 声明了 Multi-Release: true 时，");
        System.out.println("    会自动加载对应版本的类文件");
        System.out.println();

        // 演示版本目录命名
        System.out.println("版本目录命名规则：");
        System.out.println("  META-INF/versions/<version>/");
        System.out.println("  - <version> 必须是 JDK 主版本号（如 9, 10, 11, ...）");
        System.out.println("  - 版本号必须是正整数");
        System.out.println("  - 不同版本的类文件必须保持相同的包名和类名");
        System.out.println();

        // 演示版本感知 URL 的格式
        System.out.println("版本感知 URL 格式：");
        System.out.println("  jar:file:///path/to/jar.jar!/com/example/MyClass.class");
        System.out.println("  jar:file:///path/to/jar.jar!/META-INF/versions/9/com/example/MyClass.class");
    }

    /**
     * 测试 JarURLConnection 的版本信息获取
     * 通过 URL 访问 JAR 包中的资源时，可以获取版本信息
     */
    @Test
    public void testJarURLVersionInfo() throws IOException {
        System.out.println("=== 通过 URL 获取 JAR 版本信息 ===");

        // 获取当前类路径中任意 JAR 文件的 URL 进行演示
        URL classUrl = getClass().getResource('/' + getClass().getName().replace('.', '/') + ".class");
        System.out.println("当前类 URL：" + classUrl);

        if (classUrl != null && "jar".equals(classUrl.getProtocol())) {
            // 如果当前类是从 JAR 包中加载的，尝试获取版本信息
            JarURLConnection jarConn = (JarURLConnection) classUrl.openConnection();
            System.out.println("JAR 文件路径：" + jarConn.getJarFileURL());

            // 获取 JAR 文件的属性
            try (JarFile jarFile = jarConn.getJarFile()) {
                Manifest manifest = jarFile.getManifest();
                if (manifest != null) {
                    Attributes mainAttributes = manifest.getMainAttributes();
                    System.out.println("Manifest 属性：");
                    for (Object key : mainAttributes.keySet()) {
                        System.out.println("  " + key + "：" + mainAttributes.get(key));
                    }
                }
            }
        } else {
            // 当前类是从目录中加载的（非 JAR 包），模拟演示
            System.out.println("当前类从目录加载，非 JAR 包环境，模拟演示 URL 版本信息获取：");
            System.out.println("JarURLConnection 常用方法：");
            System.out.println("  getJarFileURL()     - 获取 JAR 文件的 URL");
            System.out.println("  getEntryName()      - 获取 JAR 包中的入口名称");
            System.out.println("  getJarFile()        - 获取 JarFile 对象");
            System.out.println("  getAttributes()     - 获取入口的属性");
            System.out.println("  getMainAttributes() - 获取主 Manifest 属性");
        }
    }

    /**
     * 测试 URLClassLoader 在多版本 JAR 中的行为
     * 演示类加载器如何根据版本加载类
     */
    @Test
    public void testClassLoaderBehavior() {
        System.out.println("=== 类加载器在多版本 JAR 中的行为 ===");

        // 获取当前类加载器
        ClassLoader classLoader = getClass().getClassLoader();
        System.out.println("当前类加载器：" + classLoader.getClass().getName());

        if (classLoader instanceof URLClassLoader) {
            URLClassLoader urlClassLoader = (URLClassLoader) classLoader;
            URL[] urls = urlClassLoader.getURLs();
            System.out.println("类路径中的 URL 数量：" + urls.length);
            for (int i = 0; i < Math.min(5, urls.length); i++) {
                System.out.println("  URL[" + i + "]：" + urls[i]);
            }
        } else {
            System.out.println("当前类加载器不是 URLClassLoader（可能是 JDK 9+ 的模块化类加载器）");
            System.out.println("JDK 9+ 使用模块化类加载器，不再使用 URLClassLoader");
        }

        System.out.println();
        System.out.println("多版本 JAR 类加载机制：");
        System.out.println("  1. JVM 调用 ClassLoader.loadClass() 加载类");
        System.out.println("  2. 如果类来自多版本 JAR，JVM 自动选择版本目录");
        System.out.println("  3. 选择规则：最高版本 <= 当前 JDK 版本");
        System.out.println("  4. 这个过程对开发者完全透明");
    }

    /**
     * 测试多版本 JAR 的版本兼容性策略
     * 说明多版本 JAR 在构建和部署时的最佳实践
     */
    @Test
    public void testVersionCompatibilityStrategy() {
        System.out.println("=== 多版本 JAR 版本兼容性策略 ===");

        System.out.println("1. 基础版本（根目录）：");
        System.out.println("   - 兼容最低支持的 JDK 版本");
        System.out.println("   - 不使用任何高版本特有的 API");
        System.out.println("   - 使用 -source/-target 编译为低版本字节码");
        System.out.println();

        System.out.println("2. 版本特定实现（META-INF/versions/）：");
        System.out.println("   - 使用对应版本 JDK 编译");
        System.out.println("   - 可以利用该版本特有的 API 和特性");
        System.out.println("   - 保持与基础版本相同的公开 API 签名");
        System.out.println();

        System.out.println("3. 构建工具支持：");
        System.out.println("   - Maven：maven-jar-plugin 3.2.0+ 支持");
        System.out.println("   - Gradle：5.1+ 支持多版本 JAR 构建");
        System.out.println("   - 需要配置 Multi-Release: true 和版本目录");
        System.out.println();

        System.out.println("4. 注意事项：");
        System.out.println("   - 低版本 JDK 无法加载多版本 JAR 中的版本特定类");
        System.out.println("   - 基础版本必须是低版本 JDK 可运行的");
        System.out.println("   - 所有版本必须提供相同的公开 API");
        System.out.println("   - 版本目录中的类文件字节码版本必须对应其 JDK 版本");
        System.out.println();

        System.out.println("5. 实际应用示例：");
        System.out.println("   库 my-lib 需要同时支持 JDK 8 和 JDK 17：");
        System.out.println("   - 根目录：JDK 8 字节码版本的 MyClass.class");
        System.out.println("   - META-INF/versions/9/：JDK 9 优化版本（使用模块化）");
        System.out.println("   - META-INF/versions/17/：JDK 17 优化版本（使用密封类等新特性）");
        System.out.println("   - 运行时自动选择：JDK 8 使用基础版，JDK 17 使用 17 版");
    }
}