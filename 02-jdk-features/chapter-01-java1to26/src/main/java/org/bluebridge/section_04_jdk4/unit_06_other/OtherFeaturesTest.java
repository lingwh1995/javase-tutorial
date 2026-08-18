package org.bluebridge.section_04_jdk4.unit_06_other;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * JDK 1.4 其他新特性测试
 * 包含：URI、LinkedHashMap、LinkedHashSet、PriorityQueue、IdentityHashMap、StackOverflowError
 *
 * @author lingwh
 * @date 2026/08/05 19:03
 */
public class OtherFeaturesTest {

    /**
     * 测试 java.net.URI（统一资源标识符）
     * URI 用于标识资源，比 URL 更通用
     */
    @Test
    public void testURI() throws Exception {
        // 创建 URI
        URI uri = new URI("https://user:password@example.com:8080/path/to/resource?query=value&lang=java#section");

        System.out.println("完整 URI：" + uri);
        System.out.println("Scheme（协议）：" + uri.getScheme());
        System.out.println("Host（主机）：" + uri.getHost());
        System.out.println("Port（端口）：" + uri.getPort());
        System.out.println("Path（路径）：" + uri.getPath());
        System.out.println("Query（查询参数）：" + uri.getQuery());
        System.out.println("Fragment（片段）：" + uri.getFragment());
        System.out.println("UserInfo（用户信息）：" + uri.getUserInfo());

        // 相对 URI
        URI base = new URI("https://example.com/api/");
        URI relative = new URI("users/list");
        URI resolved = base.resolve(relative);
        System.out.println("解析后的 URI：" + resolved);

        // 相对化
        URI full = new URI("https://example.com/api/users/list");
        URI relativized = base.relativize(full);
        System.out.println("相对化后的 URI：" + relativized);

        // 规范化
        URI messy = new URI("https://example.com/foo/./bar/../baz");
        System.out.println("规范化前：" + messy);
        System.out.println("规范化后：" + messy.normalize());
    }

    /**
     * 测试 java.util.LinkedHashMap
     * 保持插入顺序或访问顺序的 HashMap
     */
    @Test
    public void testLinkedHashMap() {
        // 默认构造：保持插入顺序
        LinkedHashMap<String, Integer> insertionOrder = new LinkedHashMap<>();
        insertionOrder.put("Java", 1);
        insertionOrder.put("Python", 2);
        insertionOrder.put("C++", 3);
        insertionOrder.put("JavaScript", 4);

        System.out.println("插入顺序（LinkedHashMap）：");
        for (Map.Entry<String, Integer> entry : insertionOrder.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        // 访问顺序：accessOrder = true
        LinkedHashMap<String, Integer> accessOrder = new LinkedHashMap<>(16, 0.75f, true);
        accessOrder.put("A", 1);
        accessOrder.put("B", 2);
        accessOrder.put("C", 3);
        accessOrder.put("D", 4);

        // 访问某些元素
        accessOrder.get("B");
        accessOrder.get("D");
        accessOrder.get("A");

        System.out.println("访问顺序（最近访问的排在最后）：");
        for (Map.Entry<String, Integer> entry : accessOrder.entrySet()) {
            System.out.println("  " + entry.getKey() + " -> " + entry.getValue());
        }

        // 测试 removeEldestEntry 实现 LRU 缓存
        LinkedHashMap<String, String> lruCache = new LinkedHashMap<String, String>(16, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > 3; // 最多保留 3 个元素
            }
        };

        lruCache.put("a", "1");
        lruCache.put("b", "2");
        lruCache.put("c", "3");
        System.out.println("LRU 缓存（添加 a, b, c）：" + lruCache.keySet());

        lruCache.put("d", "4");
        System.out.println("LRU 缓存（添加 d，a 应被移除）：" + lruCache.keySet());

        // 访问 b
        lruCache.get("b");
        lruCache.put("e", "5");
        System.out.println("LRU 缓存（访问 b 后添加 e，c 应被移除）：" + lruCache.keySet());
    }

    /**
     * 测试 java.util.LinkedHashSet
     * 保持插入顺序的 HashSet
     */
    @Test
    public void testLinkedHashSet() {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("香蕉");
        linkedHashSet.add("苹果");
        linkedHashSet.add("橘子");
        linkedHashSet.add("葡萄");
        linkedHashSet.add("苹果"); // 重复元素，不会添加

        System.out.println("LinkedHashSet（保持插入顺序）：");
        for (String fruit : linkedHashSet) {
            System.out.println("  " + fruit);
        }

        // 与 HashSet 对比（不保证顺序）
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("香蕉");
        hashSet.add("苹果");
        hashSet.add("橘子");
        hashSet.add("葡萄");

        System.out.println("HashSet（不保证顺序）：");
        for (String fruit : hashSet) {
            System.out.println("  " + fruit);
        }
    }

    /**
     * 测试 java.util.PriorityQueue
     * 基于优先级堆的无限队列，元素按照自然顺序或 Comparator 排序
     */
    @Test
    public void testPriorityQueue() {
        // 自然顺序（最小堆）
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(30);
        minHeap.offer(10);
        minHeap.offer(50);
        minHeap.offer(20);
        minHeap.offer(40);

        System.out.println("PriorityQueue（最小堆 - 自然顺序）：");
        while (!minHeap.isEmpty()) {
            System.out.print(minHeap.poll() + " ");
        }
        System.out.println();

        // 自定义 Comparator（最大堆）
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        maxHeap.offer(30);
        maxHeap.offer(10);
        maxHeap.offer(50);
        maxHeap.offer(20);
        maxHeap.offer(40);

        System.out.println("PriorityQueue（最大堆 - 自定义 Comparator）：");
        while (!maxHeap.isEmpty()) {
            System.out.print(maxHeap.poll() + " ");
        }
        System.out.println();

        // 使用 PriorityQueue 处理任务优先级
        PriorityQueue<Task> taskQueue = new PriorityQueue<>((t1, t2) -> t2.priority - t1.priority);
        taskQueue.offer(new Task("普通任务", 1));
        taskQueue.offer(new Task("紧急任务", 10));
        taskQueue.offer(new Task("次要任务", 5));
        taskQueue.offer(new Task("一般任务", 3));

        System.out.println("按优先级处理任务：");
        while (!taskQueue.isEmpty()) {
            Task task = taskQueue.poll();
            System.out.println("  处理：" + task.name + "（优先级：" + task.priority + "）");
        }
    }

    /**
     * 任务类，用于 PriorityQueue 测试
     */
    private static class Task {
        String name;
        int priority;

        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
        }
    }

    /**
     * 测试 java.util.IdentityHashMap
     * 使用 == 而不是 equals() 比较键的 Map
     */
    @Test
    public void testIdentityHashMap() {
        // 普通 HashMap：使用 equals() 比较键
        Map<String, String> hashMap = new HashMap<>();
        String key1 = new String("key");
        String key2 = new String("key");
        hashMap.put(key1, "value1");
        hashMap.put(key2, "value2");
        System.out.println("HashMap 使用 equals() 比较键，大小：" + hashMap.size() + "（两个 key 被视为相同）");

        // IdentityHashMap：使用 == 比较键（引用相等）
        Map<String, String> identityMap = new IdentityHashMap<>();
        String identityKey1 = new String("key");
        String identityKey2 = new String("key");
        identityMap.put(identityKey1, "value1");
        identityMap.put(identityKey2, "value2");
        System.out.println("IdentityHashMap 使用 == 比较键，大小：" + identityMap.size() + "（两个 key 被视为不同）");

        // 演示 IdentityHashMap 的典型用途：跟踪对象实例
        IdentityHashMap<Object, String> instanceTracker = new IdentityHashMap<>();
        Object obj1 = new Object();
        Object obj2 = new Object();
        Object obj3 = obj1; // 同一个引用

        instanceTracker.put(obj1, "第一个对象");
        instanceTracker.put(obj2, "第二个对象");
        instanceTracker.put(obj3, "第三个对象（实际上是 obj1）");

        System.out.println("IdentityHashMap 对象实例跟踪，大小：" + instanceTracker.size() + "（obj1 和 obj3 是同一引用）");
        System.out.println("  obj1 的值：" + instanceTracker.get(obj1));
        System.out.println("  obj2 的值：" + instanceTracker.get(obj2));
    }

    /**
     * 测试捕获 StackOverflowError
     * 模拟无限递归导致栈溢出，并捕获 StackOverflowError
     */
    @Test
    public void testStackOverflowError() {
        // 使用计数器控制递归深度，避免 JVM 崩溃
        final int[] depth = {0};
        final int[] maxDepth = {0};

        try {
            recursiveMethod(depth, maxDepth);
        } catch (StackOverflowError e) {
            System.out.println("捕获到 StackOverflowError！");
            System.out.println("最大递归深度：" + maxDepth[0]);
            System.out.println("栈溢出错误信息：" + e.getMessage());
        }

        System.out.println("程序继续执行，StackOverflowError 已被捕获处理");
    }

    /**
     * 递归方法，用于触发 StackOverflowError
     */
    private void recursiveMethod(int[] depth, int[] maxDepth) {
        depth[0]++;
        maxDepth[0] = Math.max(maxDepth[0], depth[0]);
        recursiveMethod(depth, maxDepth);
    }

    /**
     * 测试 URI 的创建和异常处理
     */
    @Test
    public void testURIException() {
        // 测试无效 URI
        try {
            new URI("invalid uri with spaces");
        } catch (URISyntaxException e) {
            System.out.println("捕获 URISyntaxException：");
            System.out.println("  错误信息：" + e.getMessage());
            System.out.println("  错误索引：" + e.getIndex());
        }

        // 测试相对 URI 和绝对 URI
        URI absolute = URI.create("https://example.com/path");
        URI relative = URI.create("/relative/path");
        System.out.println("绝对 URI：" + absolute.isAbsolute());
        System.out.println("相对 URI：" + relative.isAbsolute());
    }
}