package org.bluebridge.section_02_jdk2.unit_03_jit;

import org.junit.Test;

/**
 * JDK 1.2 JIT(Just-In-Time)编译器特性测试
 *
 * JDK 1.2 引入了 JIT(Just-In-Time, 即时编译)编译器, 这是 Java 性能的重要里程碑:
 * 1. JIT 编译器在运行时将热点代码(Hot Spot)编译为本地机器码, 大幅提升执行效率
 * 2. 热点代码是指被频繁执行的方法或循环, JIT 会对其进行编译优化
 * 3. 编译后的本地机器码直接由 CPU 执行, 避免了重复解释执行的开销
 * 4. JVM 参数 -XX:+PrintCompilation 可以打印 JIT 编译信息
 * 5. JIT 编译器会进行方法内联、循环展开、逃逸分析等优化
 *
 * 注意: 本测试通过循环密集型操作对比执行时间来演示 JIT 的优化效果。
 * 由于 JIT 需要预热, 多次执行同一方法会越来越快。
 *
 * @author lingwh
 * @date 2026/08/05 19:03
 */
public class JITCompilerTest {

    /**
     * 测试 JIT 编译器的预热效果: 演示重复执行同一方法后性能提升
     *
     * 说明: JIT 编译器会识别热点代码(如循环次数达到阈值的方法),
     * 将其编译为本地机器码, 后续执行不再需要解释执行, 因此执行时间会显著下降。
     */
    @Test
    public void testJITWarmUpEffect() {
        System.out.println("========== JIT 预热效果测试 ==========");
        System.out.println("说明: 重复执行计算密集型方法, 观察执行时间变化");
        System.out.println("JIT 会将热点代码编译为本地机器码, 后续执行会更快");
        System.out.println();

        // 重复执行 10 轮, 每轮执行一次计算密集型方法
        for (int round = 1; round <= 10; round++) {
            long startTime = System.nanoTime();

            // 执行计算密集型操作
            long result = performCalculation(10_000_000);

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000; // 转为毫秒

            System.out.println("第 " + round + " 轮: 结果=" + result + ", 耗时=" + duration + " ms");
        }

        System.out.println();
        System.out.println("结论: 通常前几轮较慢(解释执行), 后续轮次会变快(JIT 编译优化)");
        System.out.println("可通过 JVM 参数 -XX:+PrintCompilation 查看 JIT 编译日志");
    }

    /**
     * 测试 JIT 对循环内联优化的效果
     *
     * 说明: JIT 编译器会进行方法内联(Method Inlining)优化,
     * 将频繁调用的小方法直接内联到调用处, 消除方法调用的开销。
     */
    @Test
    public void testJITMethodInlining() {
        System.out.println("========== JIT 方法内联优化测试 ==========");
        System.out.println("说明: JIT 会将频繁调用的小方法内联到调用处, 消除调用开销");
        System.out.println();

        // 预热阶段: 使 JIT 识别出热点代码
        System.out.println("预热阶段...");
        for (int i = 0; i < 10_000; i++) {
            addInline(1, 2);
        }

        // 测试阶段: 对比内联后的执行效率
        int iterations = 10_000_000;
        long startTime = System.nanoTime();

        long sum = 0;
        for (int i = 0; i < iterations; i++) {
            sum += addInline(i, 1);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("内联方法执行 " + iterations + " 次: 结果=" + sum + ", 耗时=" + duration + " ms");
    }

    /**
     * 测试 JIT 对循环展开(Loop Unrolling)的优化效果
     *
     * 说明: JIT 编译器会将循环体展开, 减少循环控制指令(如条件判断、计数器增减)的执行次数,
     * 从而提高指令级并行度, 发挥 CPU 流水线性能。
     */
    @Test
    public void testJITLoopUnrolling() {
        System.out.println("========== JIT 循环展开优化测试 ==========");
        System.out.println("说明: JIT 会展开循环体, 减少循环控制指令的开销");
        System.out.println();

        // 预热阶段
        System.out.println("预热阶段...");
        for (int i = 0; i < 10_000; i++) {
            processArrayUnrolled(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        }

        // 构造测试数据
        int[] data = new int[100_000];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }

        // 测试: 直接循环
        long startTime1 = System.nanoTime();
        long result1 = processArrayDirect(data);
        long endTime1 = System.nanoTime();
        System.out.println("直接循环: 结果=" + result1 + ", 耗时=" + (endTime1 - startTime1) / 1_000_000 + " ms");

        // 测试: 手动展开循环(每次处理 8 个元素)
        long startTime2 = System.nanoTime();
        long result2 = processArrayUnrolled(data);
        long endTime2 = System.nanoTime();
        System.out.println("展开循环: 结果=" + result2 + ", 耗时=" + (endTime2 - startTime2) / 1_000_000 + " ms");
    }

    /**
     * 测试 JIT 逃逸分析(Escape Analysis)优化
     *
     * 说明: JIT 编译器会进行逃逸分析, 判断对象是否逃逸出方法作用域。
     * 如果对象没有逃逸, JIT 会进行栈上分配(Stack Allocation), 消除同步锁(Lock Elision)等优化,
     * 从而减少垃圾回收的压力。
     */
    @Test
    public void testJITEscapeAnalysis() {
        System.out.println("========== JIT 逃逸分析优化测试 ==========");
        System.out.println("说明: 如果对象没有逃逸出方法, JIT 会进行栈上分配, 减少 GC 压力");
        System.out.println();

        // 预热阶段
        System.out.println("预热阶段...");
        for (int i = 0; i < 10_000; i++) {
            createPointAndSum(i, i + 1);
        }

        // 测试: 创建大量不逃逸的对象
        int iterations = 10_000_000;
        long startTime = System.nanoTime();

        long total = 0;
        for (int i = 0; i < iterations; i++) {
            total += createPointAndSum(i, i + 1);
        }

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1_000_000;
        System.out.println("创建 " + iterations + " 个不逃逸对象: 结果=" + total + ", 耗时=" + duration + " ms");
        System.out.println("提示: 添加 -XX:+PrintEscapeAnalysis 参数可查看逃逸分析详情");
    }

    /**
     * 简单加法方法(用于演示方法内联)
     */
    private int addInline(int a, int b) {
        return a + b;
    }

    /**
     * 直接循环处理数组
     */
    private long processArrayDirect(int[] array) {
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    /**
     * 手动展开循环处理数组(每次处理 8 个元素)
     */
    private long processArrayUnrolled(int[] array) {
        long sum = 0;
        int i = 0;
        // 每次处理 8 个元素
        int length = array.length - 7;
        for (; i < length; i += 8) {
            sum += array[i];
            sum += array[i + 1];
            sum += array[i + 2];
            sum += array[i + 3];
            sum += array[i + 4];
            sum += array[i + 5];
            sum += array[i + 6];
            sum += array[i + 7];
        }
        // 处理剩余元素
        for (; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    /**
     * 创建一个 Point 对象并返回坐标和(用于演示逃逸分析)
     * 该对象没有逃逸出方法, JIT 可进行栈上分配
     */
    private long createPointAndSum(int x, int y) {
        // Point 对象没有逃逸出此方法, JIT 可能将其分配到栈上而非堆上
        Point point = new Point(x, y);
        return point.getX() + point.getY();
    }

    /**
     * 简单的 Point 类(用于演示逃逸分析)
     */
    static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    /**
     * 测试 JIT 编译阈值(Compilation Threshold)
     *
     * 说明: JIT 编译器有一个编译阈值(默认 10,000 次调用),
     * 当方法被调用的次数超过这个阈值时, JIT 会将其编译为本地机器码。
     * 通过 -XX:CompileThreshold=10000 可以调整这个阈值。
     */
    @Test
    public void testCompilationThreshold() {
        System.out.println("========== JIT 编译阈值测试 ==========");
        System.out.println("说明: 默认编译阈值为 10,000 次调用(Client 模式)或 15,000 次(Server 模式)");
        System.out.println("     JVM 参数: -XX:CompileThreshold=10000");
        System.out.println("     JVM 参数: -XX:+PrintCompilation (打印编译日志)");
        System.out.println("     JVM 参数: -XX:+PrintGC (打印 GC 日志)");
        System.out.println();

        // 统计各阶段耗时
        long totalTime = 0;
        int iterations = 20_000;

        // 分阶段执行, 观察 JIT 编译后的性能变化
        for (int phase = 1; phase <= 5; phase++) {
            long startTime = System.nanoTime();

            long result = 0;
            for (int i = 0; i < iterations; i++) {
                result += fibonacci(20);
            }

            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1_000_000;
            totalTime += duration;

            System.out.println("阶段 " + phase + " (执行 " + iterations + " 次 fib(20)): 结果="
                    + result + ", 耗时=" + duration + " ms");
        }

        System.out.println();
        System.out.println("总耗时: " + totalTime + " ms");
        System.out.println("提示: 使用 -XX:+PrintCompilation 参数可看到类似以下输出:");
        System.out.println("  java.lang.String::hashCode (55 bytes)");
        System.out.println("  JITCompilerTest::fibonacci (X bytes)   made not entrant");
        System.out.println("  JITCompilerTest::fibonacci (X bytes)   made zombie");
    }

    /**
     * 递归计算斐波那契数列(用于演示 JIT 编译阈值)
     */
    private long fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * 展示 JIT 相关 JVM 参数说明
     */
    @Test
    public void testJITJVMParameters() {
        System.out.println("========== JIT 相关 JVM 参数说明 ==========");
        System.out.println();
        System.out.println("1. 编译模式参数:");
        System.out.println("   -client        : Client 模式(启动快, 编译阈值低)");
        System.out.println("   -server        : Server 模式(启动慢, 编译优化更激进)");
        System.out.println("   -d64           : 64 位模式");
        System.out.println();
        System.out.println("2. 编译日志参数:");
        System.out.println("   -XX:+PrintCompilation    : 打印 JIT 编译信息");
        System.out.println("   -XX:+PrintGC             : 打印 GC 信息");
        System.out.println("   -XX:+PrintInlining       : 打印方法内联信息");
        System.out.println("   -XX:+PrintEscapeAnalysis : 打印逃逸分析信息");
        System.out.println();
        System.out.println("3. 编译阈值参数:");
        System.out.println("   -XX:CompileThreshold=10000              : 设置编译阈值(默认: Client=10000, Server=15000)");
        System.out.println("   -XX:-TieredCompilation                  : 禁用分层编译");
        System.out.println("   -XX:TieredStopAtLevel=1                 : 设置分层编译停止级别");
        System.out.println();
        System.out.println("4. 编译优化参数:");
        System.out.println("   -XX:+DoEscapeAnalysis   : 开启逃逸分析(默认开启)");
        System.out.println("   -XX:+EliminateAllocations: 开启栈上分配(默认开启)");
        System.out.println("   -XX:+EliminateLocks      : 开启锁消除(默认开启)");
        System.out.println("   -XX:+UseCompressedOops   : 使用压缩对象指针(64 位默认开启)");
        System.out.println();
        System.out.println("5. 代码缓存参数:");
        System.out.println("   -XX:ReservedCodeCacheSize=240m  : 设置代码缓存大小");
        System.out.println("   -XX:InitialCodeCacheSize=64m    : 设置初始代码缓存大小");
        System.out.println();
        System.out.println("使用示例: java -server -XX:+PrintCompilation JITCompilerTest");
    }
}