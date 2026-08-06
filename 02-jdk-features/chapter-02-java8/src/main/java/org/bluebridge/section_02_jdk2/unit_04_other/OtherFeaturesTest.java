package org.bluebridge.section_02_jdk2.unit_04_other;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * JDK 1.2 其他新特性测试
 *
 * JDK 1.2 除了集合框架和 Swing 外, 还引入了以下重要特性:
 * 1. java.lang.ref.SoftReference: 软引用, 内存不足时才会被回收
 * 2. java.lang.ref.WeakReference: 弱引用, 下次 GC 时就会被回收
 * 3. java.lang.ref.PhantomReference: 虚引用, 无法通过它获取对象实例
 * 4. java.lang.ref.ReferenceQueue: 引用队列, 与引用对象关联使用
 * 5. java.util.BitSet: 位集合, 高效存储和操作位数据
 * 6. java.util.Calendar: 日历类, 替代 Date 的大部分功能
 * 7. java.util.GregorianCalendar: 公历实现
 * 8. java.util.TimeZone: 时区处理
 *
 * @author lingwh
 * @date 2026/08/05 19:04
 */
public class OtherFeaturesTest {

    // ==================== 引用类型测试 ====================

    /**
     * 测试 SoftReference 软引用: 内存不足时才会被回收
     *
     * 软引用通常用于实现内存敏感的高速缓存, 例如图片缓存。
     * 当 JVM 内存充足时, 软引用对象不会被回收; 当内存不足时, GC 会回收软引用对象。
     */
    @Test
    public void testSoftReference() {
        System.out.println("========== SoftReference 软引用测试 ==========");

        // 创建一个强引用对象
        String strongRef = new String("Hello, SoftReference!");

        // 创建软引用
        SoftReference<String> softRef = new SoftReference<>(strongRef);

        // 通过软引用获取对象
        String value = softRef.get();
        System.out.println("通过软引用获取对象: " + value);

        // 解除强引用, 此时对象只有软引用指向它
        strongRef = null;

        // 手动触发 GC
        System.gc();

        // 软引用对象在内存充足时不会被回收
        String afterGC = softRef.get();
        System.out.println("GC 后通过软引用获取对象: " + afterGC);
        System.out.println("软引用特点: 内存充足时不会被回收, 适合做缓存");
        System.out.println("应用场景: 图片缓存、对象缓存等内存敏感的场景");
    }

    /**
     * 测试 WeakReference 弱引用: 下次 GC 时就会被回收
     *
     * 弱引用比软引用生命周期更短, 只要垃圾回收器扫描到只有弱引用指向的对象,
     * 无论当前内存是否充足, 都会回收该对象。
     */
    @Test
    public void testWeakReference() {
        System.out.println("========== WeakReference 弱引用测试 ==========");

        // 创建一个强引用对象
        String strongRef = new String("Hello, WeakReference!");

        // 创建弱引用
        WeakReference<String> weakRef = new WeakReference<>(strongRef);

        // 通过弱引用获取对象
        String value = weakRef.get();
        System.out.println("通过弱引用获取对象: " + value);

        // 解除强引用, 此时对象只有弱引用指向它
        strongRef = null;

        // 手动触发 GC
        System.gc();

        // 弱引用对象在下一次 GC 时会被回收
        String afterGC = weakRef.get();
        System.out.println("GC 后通过弱引用获取对象: " + afterGC);
        System.out.println("弱引用特点: 只要发生 GC 就会被回收, 生命周期到下一次 GC 为止");
        System.out.println("应用场景: ThreadLocal、WeakHashMap 等");
    }

    /**
     * 测试 ReferenceQueue 引用队列: 与引用对象关联使用
     *
     * 当引用对象(软引用、弱引用、虚引用)所引用的对象被 GC 回收时,
     * 引用对象本身会被加入到关联的引用队列中, 以便程序进行后续处理。
     */
    @Test
    public void testReferenceQueue() {
        System.out.println("========== ReferenceQueue 引用队列测试 ==========");

        // 创建引用队列
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();

        // 创建弱引用并关联引用队列
        String strongRef = new String("Hello, ReferenceQueue!");
        WeakReference<String> weakRef = new WeakReference<>(strongRef, referenceQueue);

        System.out.println("引用队列中是否有引用(回收前): " + referenceQueue.poll());

        // 解除强引用
        strongRef = null;

        // 手动触发 GC
        System.gc();

        // 当弱引用对象被回收后, 弱引用本身会被加入到引用队列中
        System.out.println("GC 后弱引用对象: " + weakRef.get());

        // 检查引用队列
        System.out.println("引用队列中是否有引用(回收后): " + (referenceQueue.poll() != null));
        System.out.println("应用场景: 资源清理、对象生命周期追踪");
    }

    // ==================== BitSet 测试 ====================

    /**
     * 测试 BitSet: 位集合, 高效存储和操作位数据
     *
     * BitSet 是一个按需增长的位向量, 每个位可以独立设置或清除。
     * 它内部使用 long 数组实现, 非常适合进行位运算和大规模标志位的存储。
     */
    @Test
    public void testBitSet() {
        System.out.println("========== BitSet 位集合测试 ==========");

        // 创建 BitSet
        BitSet bitSet = new BitSet();

        // 设置位(将指定索引位置的位设置为 true)
        bitSet.set(0);
        bitSet.set(2);
        bitSet.set(4);
        bitSet.set(6);
        bitSet.set(8);
        System.out.println("BitSet 内容(偶数位): " + bitSet);

        // 获取指定索引的位值
        System.out.println("索引 0 的值: " + bitSet.get(0));
        System.out.println("索引 1 的值: " + bitSet.get(1));

        // 清除位(将指定索引位置的位设置为 false)
        bitSet.clear(4);
        System.out.println("清除索引 4 后: " + bitSet);

        // 批量设置位
        BitSet another = new BitSet();
        another.set(1);
        another.set(3);
        another.set(5);
        another.set(7);
        another.set(9);
        System.out.println("另一个 BitSet(奇数位): " + another);

        // 位运算: 与(AND)
        BitSet andResult = (BitSet) bitSet.clone();
        andResult.and(another);
        System.out.println("AND 运算结果: " + andResult);

        // 位运算: 或(OR)
        BitSet orResult = (BitSet) bitSet.clone();
        orResult.or(another);
        System.out.println("OR 运算结果: " + orResult);

        // 位运算: 异或(XOR)
        BitSet xorResult = (BitSet) bitSet.clone();
        xorResult.xor(another);
        System.out.println("XOR 运算结果: " + xorResult);

        // 统计设置的位数
        System.out.println("bitSet 中设置的位数: " + bitSet.cardinality());

        // 获取下一个设置的位
        System.out.println("下一个设置的位(从索引 0 开始): " + bitSet.nextSetBit(0));

        // 获取下一个未设置的位
        System.out.println("下一个未设置的位(从索引 0 开始): " + bitSet.nextClearBit(0));

        // 获取 BitSet 的大小
        System.out.println("BitSet 大小: " + bitSet.size() + " bits");

        // 设置一段范围的位
        BitSet rangeBitSet = new BitSet();
        rangeBitSet.set(10, 20); // 设置索引 10 到 19 的位
        System.out.println("范围设置(10-19): " + rangeBitSet);

        // 应用场景: 海量数据去重、布隆过滤器、状态标志位
        System.out.println("应用场景: 海量数据去重、布隆过滤器、状态标志位");
    }

    /**
     * 测试 BitSet 在数据去重中的应用
     */
    @Test
    public void testBitSetDuplicateRemoval() {
        System.out.println("========== BitSet 数据去重应用 ==========");

        // 模拟一组数据, 包含重复值
        int[] data = {1, 3, 5, 3, 7, 9, 1, 5, 7, 11, 13, 11};
        System.out.println("原始数据: " + Arrays.toString(data));

        // 使用 BitSet 去重
        BitSet bitSet = new BitSet();
        List<Integer> unique = new ArrayList<>();
        for (int num : data) {
            if (!bitSet.get(num)) {
                bitSet.set(num);
                unique.add(num);
            }
        }
        System.out.println("去重结果: " + unique);

        // 判断某个数字是否出现过
        System.out.println("数字 7 是否出现过: " + bitSet.get(7));
        System.out.println("数字 8 是否出现过: " + bitSet.get(8));
    }

    // ==================== Calendar 测试 ====================

    /**
     * 测试 Calendar 和 GregorianCalendar: 日历类
     *
     * Calendar 是一个抽象类, 提供了日期和时间字段的转换和操作方法。
     * GregorianCalendar 是 Calendar 的子类, 实现了公历(格里高利历)日历系统。
     */
    @Test
    public void testCalendar() {
        System.out.println("========== Calendar 日历类测试 ==========");

        // 获取 Calendar 实例(默认使用当前时区和区域设置)
        Calendar calendar = Calendar.getInstance();
        System.out.println("当前 Calendar 类型: " + calendar.getClass().getName());

        // 获取各个日期时间字段
        System.out.println("年: " + calendar.get(Calendar.YEAR));
        System.out.println("月: " + (calendar.get(Calendar.MONTH) + 1)); // 月份从 0 开始
        System.out.println("日: " + calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println("时: " + calendar.get(Calendar.HOUR_OF_DAY)); // 24 小时制
        System.out.println("分: " + calendar.get(Calendar.MINUTE));
        System.out.println("秒: " + calendar.get(Calendar.SECOND));

        // 设置日期
        Calendar specificDate = Calendar.getInstance();
        specificDate.set(2025, Calendar.DECEMBER, 25); // 2025 年 12 月 25 日
        System.out.println("设置后的日期: " + specificDate.get(Calendar.YEAR) + "年"
                + (specificDate.get(Calendar.MONTH) + 1) + "月"
                + specificDate.get(Calendar.DAY_OF_MONTH) + "日");

        // 日期运算: 加 7 天
        specificDate.add(Calendar.DAY_OF_MONTH, 7);
        System.out.println("加 7 天后: " + specificDate.get(Calendar.YEAR) + "年"
                + (specificDate.get(Calendar.MONTH) + 1) + "月"
                + specificDate.get(Calendar.DAY_OF_MONTH) + "日");

        // 日期运算: 减 2 个月
        specificDate.add(Calendar.MONTH, -2);
        System.out.println("减 2 个月后: " + specificDate.get(Calendar.YEAR) + "年"
                + (specificDate.get(Calendar.MONTH) + 1) + "月"
                + specificDate.get(Calendar.DAY_OF_MONTH) + "日");

        // 获取一周中的第几天
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String[] weekDays = {"", "周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        System.out.println("今天是: " + weekDays[dayOfWeek]);

        // 获取一年中的第几天
        System.out.println("今天是今年的第 " + calendar.get(Calendar.DAY_OF_YEAR) + " 天");

        // 判断是否为闰年
        GregorianCalendar gc = new GregorianCalendar();
        boolean isLeapYear = gc.isLeapYear(2025);
        System.out.println("2025 年是否为闰年: " + isLeapYear);
        System.out.println("2024 年是否为闰年: " + gc.isLeapYear(2024));
    }

    /**
     * 测试 GregorianCalendar 公历实现
     */
    @Test
    public void testGregorianCalendar() {
        System.out.println("========== GregorianCalendar 公历测试 ==========");

        // 创建 GregorianCalendar 实例
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        // 设置特定日期
        gregorianCalendar.set(2025, Calendar.JANUARY, 1, 0, 0, 0);
        System.out.println("设置日期为 2025-01-01");

        // 获取 Date 对象
        Date date = gregorianCalendar.getTime();
        System.out.println("Date 对象: " + date);

        // 格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEEE");
        String formatted = sdf.format(date);
        System.out.println("格式化日期: " + formatted);

        // 获取月份的最大天数和最小天数
        int maxDays = gregorianCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int minDays = gregorianCalendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        System.out.println("当前月份最大天数: " + maxDays + ", 最小天数: " + minDays);

        // 设置时间为 2024 年 2 月(闰年)
        gregorianCalendar.set(2024, Calendar.FEBRUARY, 1);
        int febDays2024 = gregorianCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("2024 年 2 月天数: " + febDays2024 + " (闰年)");

        // 设置时间为 2025 年 2 月(平年)
        gregorianCalendar.set(2025, Calendar.FEBRUARY, 1);
        int febDays2025 = gregorianCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("2025 年 2 月天数: " + febDays2025 + " (平年)");
    }

    // ==================== TimeZone 测试 ====================

    /**
     * 测试 TimeZone: 时区处理
     */
    @Test
    public void testTimeZone() {
        System.out.println("========== TimeZone 时区测试 ==========");

        // 获取默认时区
        TimeZone defaultTimeZone = TimeZone.getDefault();
        System.out.println("默认时区 ID: " + defaultTimeZone.getID());
        System.out.println("默认时区名称: " + defaultTimeZone.getDisplayName());
        System.out.println("默认时区全称: " + defaultTimeZone.getDisplayName(true, TimeZone.LONG));

        // 获取指定时区
        TimeZone chinaTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        System.out.println("中国时区 ID: " + chinaTimeZone.getID());
        System.out.println("中国时区名称: " + chinaTimeZone.getDisplayName());

        // 获取 UTC 时区
        TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");
        System.out.println("UTC 时区 ID: " + utcTimeZone.getID());

        // 获取美国东部时区
        TimeZone usEasternTimeZone = TimeZone.getTimeZone("America/New_York");
        System.out.println("美东时区 ID: " + usEasternTimeZone.getID());
        System.out.println("美东时区名称: " + usEasternTimeZone.getDisplayName());

        // 获取时区偏移量(毫秒)
        int rawOffset = chinaTimeZone.getRawOffset();
        int hours = rawOffset / (60 * 60 * 1000);
        System.out.println("中国时区偏移量(UTC+" + hours + ")");

        // 获取所有可用时区 ID
        String[] availableIDs = TimeZone.getAvailableIDs();
        System.out.println("可用时区 ID 数量: " + availableIDs.length);

        // 获取特定偏移量的时区 ID
        String[] gmt8Ids = TimeZone.getAvailableIDs(rawOffset);
        System.out.println("UTC+8 时区数量: " + gmt8Ids.length);
        System.out.println("UTC+8 时区示例: " + gmt8Ids[0]);

        // 设置默认时区
        TimeZone originalTimeZone = TimeZone.getDefault();
        TimeZone.setDefault(TimeZone.getTimeZone("America/New_York"));
        System.out.println("切换后默认时区: " + TimeZone.getDefault().getID());
        // 恢复默认时区
        TimeZone.setDefault(originalTimeZone);
        System.out.println("恢复后默认时区: " + TimeZone.getDefault().getID());
    }

    /**
     * 测试 Calendar 结合 TimeZone 使用时区转换
     */
    @Test
    public void testCalendarWithTimeZone() {
        System.out.println("========== Calendar 结合 TimeZone 时区转换测试 ==========");

        // 创建中国时区的 Calendar
        TimeZone chinaTimeZone = TimeZone.getTimeZone("Asia/Shanghai");
        Calendar chinaCalendar = Calendar.getInstance(chinaTimeZone);
        System.out.println("中国时区时间: " + chinaCalendar.get(Calendar.HOUR_OF_DAY) + ":"
                + chinaCalendar.get(Calendar.MINUTE) + ":"
                + chinaCalendar.get(Calendar.SECOND));

        // 创建美国东部时区的 Calendar
        TimeZone usTimeZone = TimeZone.getTimeZone("America/New_York");
        Calendar usCalendar = Calendar.getInstance(usTimeZone);
        System.out.println("美东时区时间: " + usCalendar.get(Calendar.HOUR_OF_DAY) + ":"
                + usCalendar.get(Calendar.MINUTE) + ":"
                + usCalendar.get(Calendar.SECOND));

        // 获取两个时区的时间差
        int chinaOffset = chinaTimeZone.getRawOffset();
        int usOffset = usTimeZone.getRawOffset();
        int diffHours = (chinaOffset - usOffset) / (60 * 60 * 1000);
        System.out.println("中国与美东时差: " + diffHours + " 小时");

        // 结合 SimpleDateFormat 格式化带时区的日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(chinaTimeZone);
        Date now = new Date();
        System.out.println("中国时区格式化: " + sdf.format(now));

        sdf.setTimeZone(usTimeZone);
        System.out.println("美东时区格式化: " + sdf.format(now));
    }

    /**
     * 测试所有引用类型的综合对比
     */
    @Test
    public void testReferenceTypeComparison() {
        System.out.println("========== 引用类型综合对比 ==========");
        System.out.println("Java 中四种引用类型的对比:");
        System.out.println();
        System.out.println("1. 强引用(Strong Reference):");
        System.out.println("   - 最常见的引用类型, 如 Object obj = new Object()");
        System.out.println("   - 只要强引用存在, GC 永远不会回收被引用的对象");
        System.out.println("   - 即使抛出 OutOfMemoryError, 也不会回收强引用对象");
        System.out.println();
        System.out.println("2. 软引用(Soft Reference):");
        System.out.println("   - 通过 SoftReference 类实现");
        System.out.println("   - 内存充足时不会被回收, 内存不足时会被回收");
        System.out.println("   - 应用场景: 图片缓存、对象缓存");
        System.out.println();
        System.out.println("3. 弱引用(Weak Reference):");
        System.out.println("   - 通过 WeakReference 类实现");
        System.out.println("   - 只要发生 GC, 无论内存是否充足, 都会被回收");
        System.out.println("   - 应用场景: ThreadLocal、WeakHashMap");
        System.out.println();
        System.out.println("4. 虚引用(Phantom Reference):");
        System.out.println("   - 通过 PhantomReference 类实现(JDK 1.2 引入)");
        System.out.println("   - 无法通过 get() 方法获取对象实例(始终返回 null)");
        System.out.println("   - 必须与 ReferenceQueue 配合使用");
        System.out.println("   - 应用场景: 对象被回收后的资源清理通知");
        System.out.println();

        // 演示虚引用
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        String strongRef = new String("phantom");
        PhantomReference<String> phantomRef = new PhantomReference<>(strongRef, queue);
        strongRef = null;
        System.out.println("虚引用 get() 始终返回: " + phantomRef.get());
        System.gc();
        System.out.println("虚引用回收后, 引用会被加入 ReferenceQueue");
    }