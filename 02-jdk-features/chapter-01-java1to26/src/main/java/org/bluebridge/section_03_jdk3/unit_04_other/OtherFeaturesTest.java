package org.bluebridge.section_03_jdk3.unit_04_other;

import org.junit.Test;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Currency;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

/**
 * JDK 1.3 其他新特性测试
 *
 * JDK 1.3 除了动态代理、JNDI、JavaSound 外, 还引入了以下重要特性:
 *
 * 1. java.rmi.Stub (RMI 相关): RMI 体系结构改进, 引入动态 Stub 机制
 * 2. javax.sql.rowset (RowSet): RowSet 接口, 增强 JDBC 数据访问能力
 * 3. java.util.Currency: 货币类, 支持 ISO 4217 货币代码
 * 4. java.util.Timer 和 TimerTask: 定时任务框架, 支持延迟和周期性任务
 * 5. HotSpot JVM: 成为默认 Java 虚拟机, 显著提升性能
 *
 * @author lingwh
 * @date 2026/08/05 19:08
 */
public class OtherFeaturesTest {

    // ==================== RMI Stub 测试 ====================

    /**
     * 测试 RMI (Remote Method Invocation) 远程接口定义
     *
     * JDK 1.3 对 RMI 进行了改进, 引入了动态 Stub 机制。
     * 在 JDK 1.3 之前, RMI Stub 需要手动使用 rmic 编译器生成。
     * JDK 1.3 之后, 可以通过 UnicastRemoteObject 的静态方法
     * exportObject() 动态生成 Stub, 无需手动运行 rmic。
     *
     * RMI 核心类:
     * - java.rmi.Remote: 远程接口标记接口
     * - java.rmi.server.UnicastRemoteObject: 导出远程对象
     * - java.rmi.server.RemoteStub: 远程 Stub 基类
     */
    @Test
    public void testRmiRemoteInterface() {
        System.out.println("========== RMI (Remote Method Invocation) 测试 ==========");

        System.out.println("RMI 核心概念:");
        System.out.println("  1. Remote 接口: 远程对象的标记接口, 所有远程接口必须继承它");
        System.out.println("  2. RemoteException: 远程方法调用抛出的异常");
        System.out.println("  3. UnicastRemoteObject: 用于导出远程对象并生成 Stub");
        System.out.println("  4. RemoteStub: 远程对象的 Stub 基类(客户端代理)");
        System.out.println("  5. Registry: 注册表, 用于注册和查找远程对象");

        System.out.println();
        System.out.println("JDK 1.3 引入的动态 Stub:");
        System.out.println("  - 之前: 使用 rmic 编译器手动生成 Stub 类");
        System.out.println("  - JDK 1.3+: 使用 UnicastRemoteObject.exportObject() 动态生成");
        System.out.println("  - 优势: 简化了 RMI 开发流程, 无需额外编译步骤");

        System.out.println();
        System.out.println("远程接口定义示例:");
        System.out.println("  // 远程接口必须继承 Remote");
        System.out.println("  interface HelloService extends Remote {");
        System.out.println("      String sayHello(String name) throws RemoteException;");
        System.out.println("  }");
        System.out.println();
        System.out.println("  // 远程实现类继承 UnicastRemoteObject");
        System.out.println("  class HelloServiceImpl extends UnicastRemoteObject");
        System.out.println("          implements HelloService {");
        System.out.println("      protected HelloServiceImpl() throws RemoteException {");
        System.out.println("          super();");
        System.out.println("      }");
        System.out.println("      @Override");
        System.out.println("      public String sayHello(String name) throws RemoteException {");
        System.out.println("          return \"Hello, \" + name;");
        System.out.println("      }");
        System.out.println("  }");
        System.out.println();
        System.out.println("  // 服务器端: 导出远程对象并注册");
        System.out.println("  // HelloService service = new HelloServiceImpl();");
        System.out.println("  // Remote stub = UnicastRemoteObject.exportObject(service, 0);");
        System.out.println("  // Registry registry = LocateRegistry.createRegistry(1099);");
        System.out.println("  // registry.rebind(\"HelloService\", stub);");
        System.out.println();
        System.out.println("  // 客户端: 通过 Registry 查找远程对象");
        System.out.println("  // Registry registry = LocateRegistry.getRegistry(\"localhost\", 1099);");
        System.out.println("  // HelloService service = (HelloService) registry.lookup(\"HelloService\");");
        System.out.println("  // String result = service.sayHello(\"World\");");
    }

    // ==================== javax.sql.rowset.RowSet 测试 ====================

    /**
     * 测试 RowSet 接口
     *
     * RowSet 是 JDK 1.3 引入的 javax.sql.rowset 包中的核心接口。
     * 它扩展了 ResultSet, 提供了更灵活的数据访问方式。
     *
     * RowSet 的特点:
     * - 可滚动: 支持向前、向后遍历
     * - 可更新: 支持对数据进行修改
     * - 可序列化: 可以在网络间传输
     * - 离线操作: 不要求始终保持数据库连接
     *
     * RowSet 的子接口:
     * - JdbcRowSet: 保持数据库连接的 RowSet
     * - CachedRowSet: 离线 RowSet, 断开连接后仍可操作
     * - WebRowSet: 支持 XML 读写的 CachedRowSet
     * - FilteredRowSet: 支持过滤的 RowSet
     * - JoinRowSet: 支持 SQL JOIN 的 RowSet
     */
    @Test
    public void testRowSet() {
        System.out.println("========== RowSet 接口测试 ==========");

        System.out.println("RowSet 接口层次结构:");
        System.out.println("  javax.sql.rowset 包:");
        System.out.println("    ├── RowSet (接口, 继承 ResultSet)");
        System.out.println("    ├── JdbcRowSet (接口, 保持连接)");
        System.out.println("    ├── CachedRowSet (接口, 离线操作)");
        System.out.println("    │   ├── WebRowSet (支持 XML)");
        System.out.println("    │   ├── FilteredRowSet (支持过滤)");
        System.out.println("    │   └── JoinRowSet (支持 JOIN)");
        System.out.println("    └── RowSetProvider (工厂类, 创建 RowSet)");

        System.out.println();
        System.out.println("RowSet 相比 ResultSet 的优势:");
        System.out.println("  1. 可滚动: 支持 previous()、absolute() 等导航方法");
        System.out.println("  2. 可更新: 支持 insertRow()、updateRow()、deleteRow()");
        System.out.println("  3. 可序列化: 可以在网络间传输 RowSet 数据");
        System.out.println("  4. 离线操作: CachedRowSet 断开连接后仍可操作数据");
        System.out.println("  5. 事件通知: 支持 RowSetListener 监听数据变化");

        System.out.println();
        System.out.println("JdbcRowSet 使用示例:");

        // 使用 RowSetProvider 创建 JdbcRowSet
        try {
            // 创建 JdbcRowSet 实例
            JdbcRowSet jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet();

            // 设置数据库连接信息
            jdbcRowSet.setUrl("jdbc:mysql://localhost:3306/mydb");
            jdbcRowSet.setUsername("root");
            jdbcRowSet.setPassword("password");

            // 设置 SQL 查询
            jdbcRowSet.setCommand("SELECT * FROM users WHERE age > ?");
            jdbcRowSet.setInt(1, 18);

            // 执行查询
            jdbcRowSet.execute();

            // 遍历结果集(可滚动)
            System.out.println("  JdbcRowSet 遍历结果:");
            while (jdbcRowSet.next()) {
                String name = jdbcRowSet.getString("name");
                int age = jdbcRowSet.getInt("age");
                System.out.println("    name=" + name + ", age=" + age);
            }

            // 关闭资源
            jdbcRowSet.close();

        } catch (Exception e) {
            // 由于没有实际数据库连接, 这里会抛出异常
            // 仅展示代码结构
            System.out.println("  (预期: 由于没有实际数据库连接, 会抛出异常)");
            System.out.println("  异常类型: " + e.getClass().getSimpleName());
            System.out.println("  异常信息: " + e.getMessage());
        }

        System.out.println();
        System.out.println("CachedRowSet 离线操作示例:");
        System.out.println("  // 创建 CachedRowSet");
        System.out.println("  CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();");
        System.out.println("  crs.setCommand(\"SELECT * FROM users\");");
        System.out.println("  crs.execute(connection); // 执行查询, 数据加载到内存");
        System.out.println("  connection.close();       // 关闭连接, 数据仍可访问");
        System.out.println("  // 离线操作数据");
        System.out.println("  while (crs.next()) {");
        System.out.println("      String name = crs.getString(\"name\");");
        System.out.println("      int age = crs.getInt(\"age\");");
        System.out.println("  }");
        System.out.println("  // 修改数据后同步回数据库");
        System.out.println("  crs.acceptChanges(connection);");
    }

    // ==================== java.util.Currency 测试 ====================

    /**
     * 测试 Currency 货币类
     *
     * Currency 类表示货币, 基于 ISO 4217 货币代码标准。
     * 它提供了货币的符号、代码、名称和小数位数等信息。
     */
    @Test
    public void testCurrency() {
        System.out.println("========== Currency 货币类测试 ==========");

        // 通过 Locale 获取货币
        Currency cny = Currency.getInstance(Locale.CHINA);
        System.out.println("中国货币:");
        System.out.println("  货币代码: " + cny.getCurrencyCode());     // CNY
        System.out.println("  货币符号: " + cny.getSymbol());           // ¥
        System.out.println("  货币符号(Locale): " + cny.getSymbol(Locale.US)); // 在 US locale 下显示 CNY
        System.out.println("  默认小数位数: " + cny.getDefaultFractionDigits()); // 2
        System.out.println("  显示名称: " + cny.getDisplayName());     // 人民币
        System.out.println("  显示名称(Locale): " + cny.getDisplayName(Locale.US)); // Chinese Yuan

        System.out.println();

        // 通过货币代码获取货币
        Currency usd = Currency.getInstance("USD");
        System.out.println("美国货币:");
        System.out.println("  货币代码: " + usd.getCurrencyCode());     // USD
        System.out.println("  货币符号: " + usd.getSymbol());           // $
        System.out.println("  默认小数位数: " + usd.getDefaultFractionDigits()); // 2
        System.out.println("  显示名称: " + usd.getDisplayName());      // US Dollar

        System.out.println();

        // 获取更多货币信息
        Currency eur = Currency.getInstance("EUR");
        System.out.println("欧元:");
        System.out.println("  货币代码: " + eur.getCurrencyCode());
        System.out.println("  货币符号: " + eur.getSymbol());
        System.out.println("  显示名称: " + eur.getDisplayName());

        Currency jpy = Currency.getInstance("JPY");
        System.out.println();
        System.out.println("日元:");
        System.out.println("  货币代码: " + jpy.getCurrencyCode());
        System.out.println("  货币符号: " + jpy.getSymbol());
        System.out.println("  默认小数位数: " + jpy.getDefaultFractionDigits()); // 0(日元没有小数)
        System.out.println("  显示名称: " + jpy.getDisplayName());

        Currency gbp = Currency.getInstance("GBP");
        System.out.println();
        System.out.println("英镑:");
        System.out.println("  货币代码: " + gbp.getCurrencyCode());
        System.out.println("  货币符号: " + gbp.getSymbol());
        System.out.println("  显示名称: " + gbp.getDisplayName());

        // 获取所有可用货币
        System.out.println();
        System.out.println("所有可用货币数量: " + Currency.getAvailableCurrencies().size());

        // 通过 Locale 获取货币的注意事项
        System.out.println();
        System.out.println("通过 Locale 获取货币示例:");
        Locale[] locales = {Locale.CHINA, Locale.US, Locale.JAPAN, Locale.UK, Locale.GERMANY};
        for (Locale locale : locales) {
            Currency currency = Currency.getInstance(locale);
            System.out.println("  " + locale.getDisplayName() + ": "
                    + currency.getCurrencyCode() + " (" + currency.getSymbol() + ")");
        }
    }

    // ==================== Timer 和 TimerTask 测试 ====================

    /**
     * 测试 Timer 和 TimerTask 定时任务
     *
     * Timer 和 TimerTask 是 JDK 1.3 引入的简单定时任务框架。
     * Timer 用于调度任务, TimerTask 是任务的具体实现。
     *
     * 核心类:
     * - java.util.Timer: 定时器, 调度 TimerTask 执行
     * - java.util.TimerTask: 抽象任务类, 继承并实现 run() 方法
     *
     * 调度方式:
     * - schedule(TimerTask task, long delay): 延迟指定毫秒后执行一次
     * - schedule(TimerTask task, Date time): 在指定时间执行一次
     * - schedule(TimerTask task, long delay, long period): 延迟后按固定间隔执行
     * - scheduleAtFixedRate(TimerTask task, long delay, long period): 按固定速率执行
     */
    @Test
    public void testTimerAndTimerTask() {
        System.out.println("========== Timer 和 TimerTask 定时任务测试 ==========");

        System.out.println("Timer 调度方式:");
        System.out.println("  1. schedule(Task, delay): 延迟后执行一次");
        System.out.println("  2. schedule(Task, time): 在指定时间执行一次");
        System.out.println("  3. schedule(Task, delay, period): 延迟后按固定间隔执行");
        System.out.println("  4. scheduleAtFixedRate(Task, delay, period): 按固定速率执行");

        System.out.println();
        System.out.println("TimerTask 生命周期:");
        System.out.println("  1. 创建: 继承 TimerTask 并实现 run() 方法");
        System.out.println("  2. 调度: 通过 Timer 的 schedule() 方法调度");
        System.out.println("  3. 执行: Timer 线程调用 run() 方法");
        System.out.println("  4. 取消: 调用 cancel() 方法取消任务");

        System.out.println();
        System.out.println("Timer 的注意事项:");
        System.out.println("  1. Timer 是单线程的: 一个 Timer 实例只有一个后台线程");
        System.out.println("  2. 任务串行执行: 如果前一个任务耗时较长, 会延迟后续任务");
        System.out.println("  3. 异常影响: 如果某个任务抛出未捕获的异常, Timer 线程会终止");
        System.out.println("  4. 替代方案: JDK 1.5+ 推荐使用 ScheduledExecutorService");

        // 创建 Timer 并调度任务
        Timer timer = new Timer("JDK1.3-Timer-Thread", true); // daemon=true, 不会阻止 JVM 退出

        // 方式 1: 延迟执行(延迟 500ms 后执行)
        System.out.println();
        System.out.println("调度延迟任务(500ms 后执行):");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("  [延迟任务] 执行时间: " + System.currentTimeMillis());
                System.out.println("  [延迟任务] 当前线程: " + Thread.currentThread().getName());
            }
        }, 500);

        // 方式 2: 周期性执行(延迟 1000ms 后, 每隔 500ms 执行一次, 执行 3 次后取消)
        System.out.println("调度周期性任务(1000ms 后启动, 每隔 500ms 执行一次):");
        TimerTask periodTask = new TimerTask() {
            private int count = 0;

            @Override
            public void run() {
                count++;
                System.out.println("  [周期任务] 第 " + count + " 次执行, 时间: " + System.currentTimeMillis());
                if (count >= 3) {
                    System.out.println("  [周期任务] 执行完成, 取消任务");
                    cancel();
                }
            }
        };
        timer.schedule(periodTask, 1000, 500);

        // 等待任务执行完成
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 取消 Timer 中的所有任务
        timer.cancel();
        System.out.println();
        System.out.println("Timer 已取消, 所有任务已停止");
    }

    /**
     * 测试 Timer 的 scheduleAtFixedRate 与 schedule 的区别
     *
     * scheduleAtFixedRate: 固定速率, 以任务开始时间为基准, 补偿延迟
     * schedule: 固定间隔, 以任务结束时间为基准, 不补偿延迟
     */
    @Test
    public void testTimerScheduleFixedRate() {
        System.out.println("========== scheduleAtFixedRate vs schedule 对比 ==========");

        System.out.println("schedule 和 scheduleAtFixedRate 的区别:");
        System.out.println("  schedule(Task, delay, period):");
        System.out.println("    - 每次执行完毕后等待 period 毫秒再执行下一次");
        System.out.println("    - 如果任务执行时间超过 period, 实际间隔会变长");
        System.out.println("    - 不会补偿延迟的调度");
        System.out.println();
        System.out.println("  scheduleAtFixedRate(Task, delay, period):");
        System.out.println("    - 以任务开始时间为基准, 每隔 period 执行一次");
        System.out.println("    - 如果某个任务延迟了, 后续任务会加速执行以补偿");
        System.out.println("    - 会补偿延迟的调度");

        System.out.println();
        System.out.println("Timer 使用建议:");
        System.out.println("  1. 对于简单的定时任务, Timer/TimerTask 足够使用");
        System.out.println("  2. 对于复杂的调度需求, 建议使用 ScheduledExecutorService");
        System.out.println("  3. 任务中应捕获所有异常, 避免 Timer 线程终止");
        System.out.println("  4. 任务执行完毕后应调用 cancel() 取消任务, 避免内存泄漏");
        System.out.println("  5. 使用完 Timer 后应调用 cancel() 释放资源");
    }

    // ==================== HotSpot JVM 说明 ====================

    /**
     * 测试 HotSpot JVM 成为默认 JVM 的说明
     *
     * JDK 1.3 的一个重要里程碑是 HotSpot JVM 成为 Java 的默认虚拟机。
     * 在此之前, 默认的 JVM 是 Classic JVM。
     *
     * HotSpot JVM 的核心特性:
     * 1. 自适应优化器: 通过运行时分析热点代码, 进行即时编译(JIT)优化
     * 2. 分代垃圾回收: 根据对象生命周期将堆分为年轻代和老年代
     * 3. 线程同步优化: 提供更高效的锁机制
     * 4. 内存管理: 更高效的内存分配和回收策略
     */
    @Test
    public void testHotSpotJVM() {
        System.out.println("========== HotSpot JVM 成为默认 JVM ==========");

        System.out.println("HotSpot JVM 发展历程:");
        System.out.println("  1999.04: HotSpot JVM 发布(最初由 Sun 收购的 Longview Technologies 开发)");
        System.out.println("  2000.05: JDK 1.3 发布, HotSpot JVM 成为默认 JVM");
        System.out.println("  2006.11: JDK 6 发布, HotSpot JVM 开源(OpenJDK)");
        System.out.println("  2018.03: JDK 10 发布, HotSpot JVM 与 JRockit 合并");

        System.out.println();
        System.out.println("HotSpot JVM 核心特性:");

        System.out.println("  1. 热点探测(Hot Spot Detection):");
        System.out.println("     - 运行时统计方法调用次数和循环次数");
        System.out.println("     - 达到阈值的方法被标记为热点方法");
        System.out.println("     - 热点方法被编译为本地代码, 提升执行效率");

        System.out.println("  2. 分代垃圾回收(Generational GC):");
        System.out.println("     - 年轻代(Young Generation): 存储新创建的对象");
        System.out.println("     - 老年代(Old Generation): 存储长期存活的对象");
        System.out.println("     - 元空间(Metaspace): 存储类元数据(JDK 8+)");
        System.out.println("     - 基于弱分代假设: 大部分对象生命周期短");

        System.out.println("  3. 即时编译(JIT Compilation):");
        System.out.println("     - Client Compiler (C1): 启动快, 编译快, 适合桌面应用");
        System.out.println("     - Server Compiler (C2): 启动慢, 优化强, 适合服务端应用");
        System.out.println("     - Tiered Compilation (JDK 7+): 结合 C1 和 C2 的优势");

        System.out.println("  4. 线程同步优化:");
        System.out.println("     - 偏向锁(Biased Locking): 减少无竞争锁的开销");
        System.out.println("     - 轻量级锁(Lightweight Locking): CAS 自旋");
        System.out.println("     - 锁膨胀(Lock Inflation): 从偏向锁到重量级锁");
        System.out.println("     - 锁消除(Lock Elision): 去除不必要的锁");
        System.out.println("     - 锁粗化(Lock Coarsening): 合并相邻锁操作");

        System.out.println();
        System.out.println("JVM 参数示例:");
        System.out.println("  // 查看 JVM 版本信息");
        System.out.println("  java -version");
        System.out.println("  // 查看 HotSpot JVM 的默认参数");
        System.out.println("  java -XX:+PrintFlagsFinal -version");
        System.out.println("  // 设置垃圾回收器(如 G1)");
        System.out.println("  java -XX:+UseG1GC -jar myapp.jar");
        System.out.println("  // 设置堆内存大小");
        System.out.println("  java -Xms512m -Xmx1024m -jar myapp.jar");
    }

    /**
     * 测试 JDK 1.3 其他零散新特性
     *
     * 总结 JDK 1.3 引入的其他重要特性。
     */
    @Test
    public void testOtherJDK13Features() {
        System.out.println("========== JDK 1.3 其他零散新特性总结 ==========");

        System.out.println("1. java.rmi.Stub");
        System.out.println("   包: java.rmi.server");
        System.out.println("   说明: 远程对象的 Stub 基类, 客户端代理的核心");
        System.out.println("   改进: JDK 1.3 支持动态 Stub 生成, 无需手动运行 rmic");
        System.out.println("   相关类: RemoteStub, UnicastRemoteObject, RemoteObject");

        System.out.println();
        System.out.println("2. javax.sql.rowset.RowSet");
        System.out.println("   包: javax.sql.rowset");
        System.out.println("   说明: 扩展 ResultSet 的可滚动、可更新、可序列化的数据集");
        System.out.println("   子接口: JdbcRowSet, CachedRowSet, WebRowSet, FilteredRowSet, JoinRowSet");
        System.out.println("   工厂类: RowSetProvider (JDK 1.7+ 引入)");

        System.out.println();
        System.out.println("3. java.util.Currency");
        System.out.println("   包: java.util");
        System.out.println("   说明: 基于 ISO 4217 标准的货币类, 支持货币代码、符号、小数位数");
        System.out.println("   获取方式: Currency.getInstance(Locale), Currency.getInstance(String code)");
        System.out.println("   核心方法: getCurrencyCode(), getSymbol(), getDisplayName(), getDefaultFractionDigits()");

        System.out.println();
        System.out.println("4. java.util.Timer & TimerTask");
        System.out.println("   包: java.util");
        System.out.println("   说明: 简单的定时任务框架, 支持延迟和周期性任务调度");
        System.out.println("   调度方式: schedule(), scheduleAtFixedRate()");
        System.out.println("   注意事项: 单线程, 任务异常会导致 Timer 终止");
        System.out.println("   替代方案: ScheduledExecutorService (JDK 1.5+)");

        System.out.println();
        System.out.println("5. HotSpot JVM");
        System.out.println("   说明: HotSpot 成为 Java 默认虚拟机");
        System.out.println("   核心特性: 热点探测、分代 GC、JIT 编译、线程同步优化");
        System.out.println("   历史意义: 奠定了 Java 高性能的基础");
    }
}