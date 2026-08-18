package org.bluebridge.section_06_jdk6.unit_05_other;

import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * JDK 6 其他新特性测试
 *
 * 涵盖 JDK 6 引入的其他重要特性：
 * 1. java.util.ArrayDeque - 双端队列的高效数组实现
 * 2. java.util.NavigableSet / NavigableMap - 导航集合接口（TreeSet/TreeMap 实现）
 * 3. java.util.Deque 接口 - 双端队列接口
 * 4. java.util.concurrent.LinkedBlockingDeque - 并发双端阻塞队列
 * 5. JDBC 4.0 新特性 - 自动加载驱动、异常改进等
 * 6. JAXB - Java Architecture for XML Binding 基本概念
 *
 * @author lingwh
 * @date 2026/08/05 19:07
 */
public class OtherFeaturesTest {

    /**
     * 测试 java.util.ArrayDeque 双端队列
     * ArrayDeque 是 Deque 接口的大小可调整数组实现，无容量限制
     */
    @Test
    public void testArrayDeque() {
        // 创建 ArrayDeque 实例
        ArrayDeque<String> deque = new ArrayDeque<>();
        // 在队首添加元素
        deque.addFirst("A");
        deque.addFirst("B");
        // 在队尾添加元素
        deque.addLast("C");
        deque.addLast("D");
        System.out.println("ArrayDeque 初始内容: " + deque);
        // 获取队首和队尾元素（不删除）
        String first = deque.getFirst();
        String last = deque.getLast();
        System.out.println("队首元素: " + first + ", 队尾元素: " + last);
        // 移除队首元素
        String removedFirst = deque.removeFirst();
        System.out.println("移除队首: " + removedFirst + ", 剩余: " + deque);
        // 移除队尾元素
        String removedLast = deque.removeLast();
        System.out.println("移除队尾: " + removedLast + ", 剩余: " + deque);
        // 使用 offerFirst/offerLast 添加元素（容量受限时返回 false 而非抛异常）
        deque.offerFirst("E");
        deque.offerLast("F");
        System.out.println("offer 添加元素后: " + deque);
        // 使用 peekFirst/peekLast 查看元素（队列为空时返回 null 而非抛异常）
        System.out.println("peek 队首: " + deque.peekFirst() + ", peek 队尾: " + deque.peekLast());
        // 使用 pollFirst/pollLast 移除元素（队列为空时返回 null 而非抛异常）
        System.out.println("poll 队首: " + deque.pollFirst() + ", poll 队尾: " + deque.pollLast());
        // 作为栈使用（LIFO）
        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push("第一条");
        stack.push("第二条");
        stack.push("第三条");
        System.out.println("栈（LIFO）弹出: " + stack.pop() + ", " + stack.pop() + ", " + stack.pop());
        // 迭代器遍历
        deque.clear();
        deque.add("X");
        deque.add("Y");
        deque.add("Z");
        System.out.print("正向遍历: ");
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
        // 反向迭代器
        System.out.print("反向遍历: ");
        Iterator<String> descendingIterator = deque.descendingIterator();
        while (descendingIterator.hasNext()) {
            System.out.print(descendingIterator.next() + " ");
        }
        System.out.println();
    }

    /**
     * 测试 java.util.NavigableSet 接口（TreeSet 实现）
     * NavigableSet 提供了丰富的导航方法
     */
    @Test
    public void testNavigableSet() {
        // 创建 NavigableSet 实例（TreeSet 实现了 NavigableSet）
        NavigableSet<Integer> navigableSet = new TreeSet<>();
        navigableSet.add(10);
        navigableSet.add(20);
        navigableSet.add(30);
        navigableSet.add(40);
        navigableSet.add(50);
        System.out.println("NavigableSet 内容: " + navigableSet);
        // lower: 返回小于指定元素的最大元素（严格小于）
        Integer lower = navigableSet.lower(30);
        System.out.println("lower(30): " + lower);
        // floor: 返回小于等于指定元素的最大元素
        Integer floor = navigableSet.floor(30);
        System.out.println("floor(30): " + floor);
        // ceiling: 返回大于等于指定元素的最小元素
        Integer ceiling = navigableSet.ceiling(35);
        System.out.println("ceiling(35): " + ceiling);
        // higher: 返回大于指定元素的最小元素（严格大于）
        Integer higher = navigableSet.higher(30);
        System.out.println("higher(30): " + higher);
        // descendingSet: 返回逆序视图
        NavigableSet<Integer> descendingSet = navigableSet.descendingSet();
        System.out.println("降序视图: " + descendingSet);
        // subSet: 返回子集视图（含头不含尾）
        NavigableSet<Integer> subSet = navigableSet.subSet(20, true, 40, true);
        System.out.println("子集 [20, 40]: " + subSet);
        // headSet: 返回小于指定元素的视图
        NavigableSet<Integer> headSet = navigableSet.headSet(30, true);
        System.out.println("headSet(30, true): " + headSet);
        // tailSet: 返回大于等于指定元素的视图
        NavigableSet<Integer> tailSet = navigableSet.tailSet(30, true);
        System.out.println("tailSet(30, true): " + tailSet);
        // pollFirst/pollLast: 移除并返回首/尾元素
        System.out.println("pollFirst: " + navigableSet.pollFirst() + ", 剩余: " + navigableSet);
        System.out.println("pollLast: " + navigableSet.pollLast() + ", 剩余: " + navigableSet);
    }

    /**
     * 测试 java.util.NavigableMap 接口（TreeMap 实现）
     */
    @Test
    public void testNavigableMap() {
        // 创建 NavigableMap 实例（TreeMap 实现了 NavigableMap）
        NavigableMap<String, Integer> navigableMap = new TreeMap<>();
        navigableMap.put("A", 1);
        navigableMap.put("C", 3);
        navigableMap.put("E", 5);
        navigableMap.put("B", 2);
        navigableMap.put("D", 4);
        System.out.println("NavigableMap 内容: " + navigableMap);
        // lowerKey: 返回小于指定键的最大键
        String lowerKey = navigableMap.lowerKey("C");
        System.out.println("lowerKey(C): " + lowerKey);
        // floorKey: 返回小于等于指定键的最大键
        String floorKey = navigableMap.floorKey("C");
        System.out.println("floorKey(C): " + floorKey);
        // ceilingKey: 返回大于等于指定键的最小键
        String ceilingKey = navigableMap.ceilingKey("D");
        System.out.println("ceilingKey(D): " + ceilingKey);
        // higherKey: 返回大于指定键的最小键
        String higherKey = navigableMap.higherKey("C");
        System.out.println("higherKey(C): " + higherKey);
        // descendingMap: 返回逆序视图
        NavigableMap<String, Integer> descendingMap = navigableMap.descendingMap();
        System.out.println("降序视图: " + descendingMap);
        // subMap: 返回子映射视图
        NavigableMap<String, Integer> subMap = navigableMap.subMap("B", true, "D", true);
        System.out.println("子映射 [B, D]: " + subMap);
        // firstEntry/lastEntry: 获取首/尾条目
        System.out.println("firstEntry: " + navigableMap.firstEntry());
        System.out.println("lastEntry: " + navigableMap.lastEntry());
        // pollFirstEntry/pollLastEntry: 移除并返回首/尾条目
        System.out.println("pollFirstEntry: " + navigableMap.pollFirstEntry() + ", 剩余: " + navigableMap);
        System.out.println("pollLastEntry: " + navigableMap.pollLastEntry() + ", 剩余: " + navigableMap);
    }

    /**
     * 测试 java.util.Deque 接口和 java.util.concurrent.LinkedBlockingDeque
     */
    @Test
    public void testDequeAndLinkedBlockingDeque() {
        // Deque 接口定义
        System.out.println("Deque 接口（双端队列）核心方法: ");
        System.out.println("  首端操作: addFirst, removeFirst, getFirst, offerFirst, pollFirst, peekFirst, push");
        System.out.println("  尾端操作: addLast, removeLast, getLast, offerLast, pollLast, peekLast, add");
        System.out.println("  通用操作: remove, pop, element, peek, size, iterator, descendingIterator");
        System.out.println("--------------------------------------");
        // 使用 LinkedBlockingDeque（并发安全的双端阻塞队列）
        LinkedBlockingDeque<String> blockingDeque = new LinkedBlockingDeque<>(5);
        System.out.println("LinkedBlockingDeque（容量=5）: ");
        // 添加元素
        blockingDeque.add("任务1");
        blockingDeque.addFirst("任务2（优先）");
        blockingDeque.addLast("任务3（延后）");
        System.out.println("  添加元素后: " + blockingDeque);
        // 获取元素
        System.out.println("  队首: " + blockingDeque.getFirst() + ", 队尾: " + blockingDeque.getLast());
        // 移除元素
        System.out.println("  移除队首: " + blockingDeque.removeFirst());
        System.out.println("  移除队尾: " + blockingDeque.removeLast());
        System.out.println("  移除后: " + blockingDeque);
        // 阻塞方法（不阻塞的演示）
        blockingDeque.offer("任务4");
        blockingDeque.offerFirst("任务5");
        System.out.println("  offer 添加后: " + blockingDeque);
        // 作为阻塞队列使用（take/put 方法会阻塞，此处不实际调用）
        System.out.println("  take() - 获取并移除队首，队列为空时阻塞");
        System.out.println("  put()  - 添加元素到队尾，队列满时阻塞");
        // 剩余容量
        System.out.println("  剩余容量: " + blockingDeque.remainingCapacity());
        // 清空
        blockingDeque.clear();
        System.out.println("  清空后大小: " + blockingDeque.size());
        // 使用 Deque 接口引用
        Deque<String> deque = new LinkedBlockingDeque<>();
        deque.addFirst("A");
        deque.addLast("B");
        System.out.println("  Deque 接口引用操作: " + deque);
    }

    /**
     * 测试 JDBC 4.0 新特性
     * 注意：本测试不实际连接数据库，仅展示 JDBC 4.0 的 API 和改进点
     */
    @Test
    public void testJdbc40Features() {
        System.out.println("JDBC 4.0（JDK 6 引入）新特性: ");
        System.out.println("--------------------------------------");
        // 1. 自动加载驱动类
        System.out.println("1. 自动加载驱动类: ");
        System.out.println("   JDBC 4.0 之前: Class.forName(\"com.mysql.jdbc.Driver\")");
        System.out.println("   JDBC 4.0 开始: 通过 SPI 自动加载驱动");
        System.out.println("   驱动 Jar 包中 META-INF/services/java.sql.Driver 文件指定驱动类");
        System.out.println("--------------------------------------");
        // 2. 异常改进
        System.out.println("2. SQLException 改进: ");
        System.out.println("   - 新增 SQLNonTransientException / SQLTransientException 子类");
        System.out.println("   - SQLException 支持 Iterable 接口（可遍历异常链）");
        System.out.println("   - 通过 getCause() 获取根本原因");
        System.out.println("--------------------------------------");
        // 3. Connection 新方法
        System.out.println("3. Connection 新方法: ");
        System.out.println("   - isValid(int timeout): 检查连接是否有效");
        System.out.println("   - createClob()/createBlob()/createNClob(): 创建 LOB 对象");
        System.out.println("   - setClientInfo()/getClientInfo(): 设置/获取客户端信息");
        System.out.println("--------------------------------------");
        // 4. DataSet 和 RowSet 改进
        System.out.println("4. RowSet 改进: ");
        System.out.println("   - 新增 RowSetFactory 和 RowSetProvider");
        System.out.println("   - 通过 RowSetProvider.newFactory().createJdbcRowSet() 创建 RowSet");
        System.out.println("--------------------------------------");
        // 5. 展示 DriverManager 的改进
        System.out.println("5. DriverManager 改进: ");
        System.out.println("   - getLogWriter()/setLogWriter(): 日志记录");
        System.out.println("   - getDrivers(): 获取所有已注册的驱动");
        System.out.println("--------------------------------------");
        // 演示 DriverManager 的基本用法（不实际连接）
        System.out.println("DriverManager 支持的 JDBC URL 格式: ");
        System.out.println("   jdbc:mysql://localhost:3306/db");
        System.out.println("   jdbc:postgresql://localhost:5432/db");
        System.out.println("   jdbc:oracle:thin:@localhost:1521:db");
        // 演示异常处理改进
        System.out.println("--------------------------------------");
        System.out.println("SQLException 迭代示例: ");
        try {
            // 模拟 SQL 异常（不实际连接数据库）
            throw new SQLException("主异常: 连接失败", "08001", -1);
        } catch (SQLException e) {
            System.out.println("   捕获 SQLException: " + e.getMessage());
            System.out.println("   SQLState: " + e.getSQLState());
            System.out.println("   ErrorCode: " + e.getErrorCode());
            // SQLException 实现了 Iterable 接口
            e.setNextException(new SQLException("次要异常: 超时", "08002", -2));
            System.out.println("   遍历异常链: ");
            for (Throwable throwable : e) {
                System.out.println("     - " + throwable.getMessage());
            }
        }
    }

    /**
     * 测试 JAXB（Java Architecture for XML Binding）基本概念
     * 注意：JDK 11+ 已移除 JAXB，需要单独引入依赖
     * 本测试演示 JAXB 的基本概念和 API 使用方式
     */
    @Test
    public void testJaxbBasicConcepts() {
        System.out.println("JAXB（Java Architecture for XML Binding）: ");
        System.out.println("  JDK 6 将 JAXB 纳入标准库（JSR 222）");
        System.out.println("  实现 Java 对象与 XML 数据之间的双向映射");
        System.out.println("--------------------------------------");
        System.out.println("核心注解: ");
        System.out.println("  @XmlRootElement   - 将 Java 类映射为 XML 根元素");
        System.out.println("  @XmlElement       - 将 Java 属性映射为 XML 元素");
        System.out.println("  @XmlAttribute     - 将 Java 属性映射为 XML 属性");
        System.out.println("  @XmlType          - 定义 XML 类型映射规则");
        System.out.println("  @XmlAccessorType  - 控制属性访问方式");
        System.out.println("--------------------------------------");
        System.out.println("核心 API: ");
        System.out.println("  JAXBContext.newInstance(Class) - 创建 JAXB 上下文");
        System.out.println("  Marshaller   - 将 Java 对象编组为 XML");
        System.out.println("  Unmarshaller - 将 XML 解组为 Java 对象");
        System.out.println("--------------------------------------");
        // 使用 JAXB 注解的示例类
        System.out.println("JAXB 注解使用示例: ");
        System.out.println("  @XmlRootElement(name = \"book\")");
        System.out.println("  public class Book {");
        System.out.println("      @XmlElement");
        System.out.println("      private String title;");
        System.out.println("      @XmlElement");
        System.out.println("      private String author;");
        System.out.println("      @XmlElement");
        System.out.println("      private double price;");
        System.out.println("  }");
        System.out.println("--------------------------------------");
        // 演示编组和解组的基本流程
        System.out.println("编组（Marshalling）流程: ");
        System.out.println("  1. JAXBContext context = JAXBContext.newInstance(Book.class)");
        System.out.println("  2. Marshaller marshaller = context.createMarshaller()");
        System.out.println("  3. marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)");
        System.out.println("  4. marshaller.marshal(book, System.out)");
        System.out.println("--------------------------------------");
        System.out.println("解组（Unmarshalling）流程: ");
        System.out.println("  1. JAXBContext context = JAXBContext.newInstance(Book.class)");
        System.out.println("  2. Unmarshaller unmarshaller = context.createUnmarshaller()");
        System.out.println("  3. Book book = (Book) unmarshaller.unmarshal(new File(\"book.xml\"))");
        System.out.println("--------------------------------------");
        System.out.println("注意: 从 JDK 11 开始，JAXB 从 JDK 中移除，");
        System.out.println("  需要使用 Maven 单独引入依赖: ");
        System.out.println("  <dependency>");
        System.out.println("      <groupId>javax.xml.bind</groupId>");
        System.out.println("      <artifactId>jaxb-api</artifactId>");
        System.out.println("      <version>2.3.1</version>");
        System.out.println("  </dependency>");
    }

    /**
     * 测试 ArrayDeque 作为队列和栈的性能演示
     */
    @Test
    public void testArrayDequeAsQueueAndStack() {
        // ArrayDeque 作为队列（FIFO）
        ArrayDeque<String> queue = new ArrayDeque<>();
        System.out.println("=== ArrayDeque 作为队列（FIFO） ===");
        queue.offer("第1个");
        queue.offer("第2个");
        queue.offer("第3个");
        System.out.println("入队顺序: 第1个 -> 第2个 -> 第3个");
        System.out.print("出队顺序: ");
        while (!queue.isEmpty()) {
            System.out.print(queue.poll() + " ");
        }
        System.out.println("\n");
        // ArrayDeque 作为栈（LIFO）
        ArrayDeque<String> stack = new ArrayDeque<>();
        System.out.println("=== ArrayDeque 作为栈（LIFO） ===");
        stack.push("底部");
        stack.push("中部");
        stack.push("顶部");
        System.out.println("入栈顺序: 底部 -> 中部 -> 顶部");
        System.out.print("出栈顺序: ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println("\n");
        // ArrayDeque 与 LinkedList 对比
        System.out.println("=== ArrayDeque 与 LinkedList 对比 ===");
        System.out.println("  ArrayDeque: 数组实现，随机访问快，内存开销小");
        System.out.println("  LinkedList: 链表实现，中间插入/删除快，内存开销大");
        System.out.println("  结论: 作为双端队列/栈使用时，ArrayDeque 通常优于 LinkedList");
    }
}