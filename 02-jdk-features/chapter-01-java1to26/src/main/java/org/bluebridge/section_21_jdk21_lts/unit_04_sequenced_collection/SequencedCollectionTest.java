package org.bluebridge.section_21_jdk21_lts.unit_04_sequenced_collection;

import org.junit.Test;

import java.util.*;

/**
 * JDK 21 LTS 顺序集合测试(STANDARD 正式特性)
 *
 * 顺序集合(Sequenced Collections, JEP 431) 是 JDK 21 LTS 的 STANDARD 正式特性，
 * 引入三个新接口: SequencedCollection、SequencedSet、SequencedMap，
 * 为集合框架添加了统一的顺序操作能力。
 *
 * 继承体系:
 *   SequencedCollection (接口) - 所有有序集合的根接口
 *     ├── List (已有接口，现在继承 SequencedCollection)
 *     ├── Deque (已有接口，现在继承 SequencedCollection)
 *     └── SequencedSet (新接口，继承 SequencedCollection)
 *           └── SortedSet (已有接口，现在继承 SequencedSet)
 *           └── LinkedHashSet (实现 SequencedSet)
 *   SequencedMap (新接口) - 有序 Map 的根接口
 *     ├── SortedMap (已有接口，现在继承 SequencedMap)
 *     └── LinkedHashMap (实现 SequencedMap)
 *
 * 新增方法:
 *   SequencedCollection: getFirst(), getLast(), addFirst(), addLast(),
 *                         removeFirst(), removeLast(), reversed()
 *   SequencedSet: 继承 SequencedCollection，reversed() 返回 SequencedSet
 *   SequencedMap: firstEntry(), lastEntry(), putFirst(), putLast(),
 *                  pollFirstEntry(), pollLastEntry(), reversed()
 *
 * @author lingwh
 * @date 2026/08/06 14:01
 */
public class SequencedCollectionTest {

    /**
     * 测试 SequencedCollection 的 getFirst() 和 getLast() 方法(STANDARD)
     * 获取有序集合的第一个和最后一个元素
     * ArrayList 和 LinkedList 都继承 SequencedCollection
     */
    @Test
    public void testGetFirstAndLast() {
        // ArrayList 测试
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println("ArrayList: " + list);
        System.out.println("  getFirst(): " + list.getFirst());
        System.out.println("  getLast(): " + list.getLast());
        System.out.println("--------------------------------------");

        // LinkedList 测试
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("X");
        linkedList.add("Y");
        linkedList.add("Z");
        System.out.println("LinkedList: " + linkedList);
        System.out.println("  getFirst(): " + linkedList.getFirst());
        System.out.println("  getLast(): " + linkedList.getLast());
        System.out.println("--------------------------------------");

        // ArrayDeque 测试
        ArrayDeque<String> deque = new ArrayDeque<>();
        deque.add("One");
        deque.add("Two");
        deque.add("Three");
        System.out.println("ArrayDeque: " + deque);
        System.out.println("  getFirst(): " + deque.getFirst());
        System.out.println("  getLast(): " + deque.getLast());
    }

    /**
     * 测试 SequencedCollection 的 addFirst() 和 addLast() 方法(STANDARD)
     * 在有序集合的开头和结尾添加元素
     */
    @Test
    public void testAddFirstAndLast() {
        // ArrayList 测试
        ArrayList<String> list = new ArrayList<>();
        list.add("B");
        list.add("C");
        list.add("D");
        System.out.println("初始: " + list);
        // ===== 旧版实现方式(JDK 21 之前): List 需要 add(0, x) / add(x) 实现 =====
        // list.add(0, "A");
        // list.add("E");
        // ===== 新版实现方式(JDK 21 起): 统一使用 addFirst()/addLast() =====
        list.addFirst("A");
        System.out.println("addFirst(A): " + list);
        list.addLast("E");
        System.out.println("addLast(E): " + list);
        System.out.println("--------------------------------------");

        // LinkedList 测试
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("B");
        linkedList.add("C");
        System.out.println("LinkedList 初始: " + linkedList);
        linkedList.addFirst("A");
        System.out.println("addFirst(A): " + linkedList);
        linkedList.addLast("D");
        System.out.println("addLast(D): " + linkedList);
        System.out.println("--------------------------------------");

        // 验证 addFirst 和 addLast 的链式调用
        ArrayList<String> chainList = new ArrayList<>();
        chainList.addFirst("First");
        chainList.addLast("Middle");
        chainList.addLast("Last");
        System.out.println("链式调用结果: " + chainList);
    }

    /**
     * 测试 SequencedCollection 的 removeFirst() 和 removeLast() 方法(STANDARD)
     * 移除有序集合的第一个和最后一个元素，返回被移除的元素
     */
    @Test
    public void testRemoveFirstAndLast() {
        // ArrayList 测试
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");
        System.out.println("初始: " + list);
        // ===== 旧版实现方式(JDK 21 之前): List 需要 remove(0) / remove(size-1) =====
        // String first = list.remove(0);
        // String last = list.remove(list.size() - 1);
        // ===== 新版实现方式(JDK 21 起): 统一使用 removeFirst()/removeLast() =====
        String first = list.removeFirst();
        System.out.println("removeFirst() 移除: " + first + ", 剩余: " + list);
        String last = list.removeLast();
        System.out.println("removeLast() 移除: " + last + ", 剩余: " + list);
        System.out.println("--------------------------------------");

        // 连续移除
        list.removeFirst();
        list.removeLast();
        System.out.println("继续移除首尾后: " + list);
    }

    /**
     * 测试 SequencedCollection 的 reversed() 方法(STANDARD)
     * 返回逆序视图，原集合不受影响(逆序视图上的操作会反映到原集合)
     */
    @Test
    public void testReversed() {
        // 基本逆序
        ArrayList<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        // ===== 旧版实现方式(JDK 21 之前): Collections.reverse() 原地逆序或手动逆序遍历 =====
        // Collections.reverse(list);  // 原地逆序, 会直接修改原集合
        // 若想保留原集合, 需要 new ArrayList<>(list) 副本再 reverse, 或用 ListIterator 逆序遍历
        // ===== 新版实现方式(JDK 21 起): reversed() 返回逆序视图, 原集合不受影响 =====
        System.out.println("原集合: " + list);
        SequencedCollection<String> reversed = list.reversed();
        System.out.println("逆序视图: " + reversed);
        System.out.println("原集合不变: " + list);
        System.out.println("--------------------------------------");

        // 逆序视图上的操作会反映到原集合上
        reversed.addFirst("E");
        System.out.println("逆序视图 addFirst(E) 后原集合: " + list);
        System.out.println("逆序视图 addFirst(E) 后逆序视图: " + reversed);
        System.out.println("--------------------------------------");

        // 多次逆序回到原顺序
        SequencedCollection<String> doubleReversed = reversed.reversed();
        System.out.println("两次逆序后: " + doubleReversed);
    }

    /**
     * 测试 SequencedSet 接口(STANDARD)
     * SequencedSet 继承 SequencedCollection，保证不重复的同时保持顺序
     * LinkedHashSet 实现了 SequencedSet 接口
     */
    @Test
    public void testSequencedSet() {
        // 基本操作
        LinkedHashSet<String> set = new LinkedHashSet<>();
        set.add("B");
        set.add("C");
        set.add("A");
        set.add("D");
        // ===== 旧版实现方式(JDK 21 之前): Set 无索引, 只能通过迭代器取首尾元素 =====
        // String first = set.iterator().next();
        // String last = null;
        // for (String s : set) { last = s; }
        // ===== 新版实现方式(JDK 21 起): SequencedSet 继承 SequencedCollection, 直接 getFirst()/getLast() =====
        System.out.println("LinkedHashSet: " + set);
        System.out.println("  getFirst(): " + set.getFirst());
        System.out.println("  getLast(): " + set.getLast());
        System.out.println("--------------------------------------");

        // addFirst 和 addLast
        set.addFirst("First");
        set.addLast("Last");
        System.out.println("addFirst/addLast 后: " + set);
        System.out.println("  getFirst(): " + set.getFirst());
        System.out.println("  getLast(): " + set.getLast());
        System.out.println("--------------------------------------");

        // addFirst 重复元素会调整位置
        set.addFirst("C");
        System.out.println("addFirst(C) 将 C 移到开头: " + set);
        System.out.println("  getFirst(): " + set.getFirst());
        System.out.println("--------------------------------------");

        // reversed 返回 SequencedSet
        SequencedSet<String> reversedSet = set.reversed();
        System.out.println("逆序 SequencedSet: " + reversedSet);
        System.out.println("  reversedSet 类型: " + reversedSet.getClass().getSimpleName());
        System.out.println("  reversedSet.getFirst(): " + reversedSet.getFirst());
        System.out.println("  reversedSet.getLast(): " + reversedSet.getLast());
    }

    /**
     * 测试 SequencedMap 的 firstEntry() 和 lastEntry() 方法(STANDARD)
     * 获取有序 Map 的第一个和最后一个键值对
     */
    @Test
    public void testSequencedMapFirstAndLastEntry() {
        // LinkedHashMap 测试
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("B", 2);
        map.put("C", 3);
        map.put("A", 1);
        map.put("D", 4);
        // ===== 旧版实现方式(JDK 21 之前): 只能通过迭代器手动取首尾 Entry =====
        // Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        // Map.Entry<String, Integer> first = it.next();
        // Map.Entry<String, Integer> last = null;
        // while (it.hasNext()) { last = it.next(); }
        // ===== 新版实现方式(JDK 21 起): firstEntry()/lastEntry() 直接获取 =====
        System.out.println("LinkedHashMap: " + map);
        System.out.println("  firstEntry(): " + map.firstEntry());
        System.out.println("  lastEntry(): " + map.lastEntry());
        System.out.println("--------------------------------------");

        // 空 Map 的 firstEntry/lastEntry 返回 null
        LinkedHashMap<String, Integer> emptyMap = new LinkedHashMap<>();
        System.out.println("空 Map firstEntry(): " + emptyMap.firstEntry());
        System.out.println("空 Map lastEntry(): " + emptyMap.lastEntry());
    }

    /**
     * 测试 SequencedMap 的 putFirst() 和 putLast() 方法(STANDARD)
     * 在有序 Map 的开头和结尾插入键值对
     */
    @Test
    public void testSequencedMapPutFirstAndLast() {
        // 基本插入
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("B", 2);
        map.put("C", 3);
        System.out.println("初始: " + map);
        map.putFirst("A", 1);
        System.out.println("putFirst(A, 1): " + map);
        map.putLast("D", 4);
        System.out.println("putLast(D, 4): " + map);
        System.out.println("--------------------------------------");

        // putFirst 已存在的键会改变位置
        map.putFirst("C", 33);
        System.out.println("putFirst(C, 33) 移动 C 到开头: " + map);
        System.out.println("  firstEntry(): " + map.firstEntry());
        System.out.println("--------------------------------------");

        // putLast 已存在的键会改变位置
        map.putLast("A", 11);
        System.out.println("putLast(A, 11) 移动 A 到末尾: " + map);
        System.out.println("  lastEntry(): " + map.lastEntry());
    }

    /**
     * 测试 SequencedMap 的 pollFirstEntry() 和 pollLastEntry() 方法(STANDARD)
     * 移除并返回有序 Map 的第一个和最后一个键值对
     */
    @Test
    public void testSequencedMapPoll() {
        // 基本移除
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("D", 4);
        System.out.println("初始: " + map);
        Map.Entry<String, Integer> first = map.pollFirstEntry();
        System.out.println("pollFirstEntry(): " + first + ", 剩余: " + map);
        Map.Entry<String, Integer> last = map.pollLastEntry();
        System.out.println("pollLastEntry(): " + last + ", 剩余: " + map);
        System.out.println("--------------------------------------");

        // 空 Map 的 poll 返回 null
        LinkedHashMap<String, Integer> emptyMap = new LinkedHashMap<>();
        System.out.println("空 Map pollFirstEntry(): " + emptyMap.pollFirstEntry());
        System.out.println("空 Map pollLastEntry(): " + emptyMap.pollLastEntry());
    }

    /**
     * 测试 SequencedMap 的 reversed() 方法(STANDARD)
     * 返回逆序视图的 SequencedMap
     */
    @Test
    public void testSequencedMapReversed() {
        // 基本逆序
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        System.out.println("原 Map: " + map);
        SequencedMap<String, Integer> reversed = map.reversed();
        System.out.println("逆序视图: " + reversed);
        System.out.println("  reversed 类型: " + reversed.getClass().getSimpleName());
        System.out.println("  reversed.firstEntry(): " + reversed.firstEntry());
        System.out.println("  reversed.lastEntry(): " + reversed.lastEntry());
        System.out.println("--------------------------------------");

        // 逆序视图上的操作会反映到原 Map
        reversed.putFirst("D", 4);
        System.out.println("逆序视图 putFirst(D, 4) 后原 Map: " + map);
        System.out.println("  map.firstEntry(): " + map.firstEntry());
    }

    /**
     * 测试 SequencedCollection 的完整操作链(STANDARD)
     * 链式调用 addFirst/addLast/getFirst/getLast/reversed 等操作
     */
    @Test
    public void testSequencedCollectionFullChain() {
        // 完整操作链
        ArrayList<String> list = new ArrayList<>();
        list.add("B");
        list.add("C");
        System.out.println("初始列表: " + list);
        System.out.println("  getFirst(): " + list.getFirst());
        System.out.println("  getLast(): " + list.getLast());

        // 链式操作
        list.addFirst("A");
        list.addLast("D");
        System.out.println("添加 A 和 D 后: " + list);
        System.out.println("  removeFirst(): " + list.removeFirst());
        System.out.println("  removeLast(): " + list.removeLast());
        System.out.println("移除首尾后: " + list);

        // 逆序视图
        SequencedCollection<String> reversed = list.reversed();
        System.out.println("逆序: " + reversed);
        System.out.println("  reversed.getFirst(): " + reversed.getFirst());
        System.out.println("  reversed.getLast(): " + reversed.getLast());
        System.out.println("--------------------------------------");

        // 使用 TreeSet (SortedSet 继承 SequencedSet)
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(3);
        treeSet.add(1);
        treeSet.add(4);
        treeSet.add(1);  // 重复，不会添加
        treeSet.add(2);
        System.out.println("TreeSet: " + treeSet);
        System.out.println("  getFirst(): " + treeSet.getFirst());
        System.out.println("  getLast(): " + treeSet.getLast());
        System.out.println("  reversed: " + treeSet.reversed());
    }
}