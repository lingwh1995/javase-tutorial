package linkedlist.linkedlist_b;

/**
 * 英雄节点测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class HeroNodeTest {

    public static void main(String[] args) {
        Node songjiang = new Node(1, "宋江", "及时雨");
        Node chaogai = new Node(2, "晁盖", "托塔天王");
        Node wuyong = new Node(3, "吴用", "智多星");
        Node linchong = new Node(4, "林冲", "豹子头");
        HeroLinkedList heroLinkedList = new HeroLinkedList();
        heroLinkedList.add(linchong);
        heroLinkedList.add(songjiang);
        // heroLinkedList.add(songjiang);
        heroLinkedList.add(chaogai);
        heroLinkedList.add(wuyong);
        heroLinkedList.list();

        // 更新链表节点
        System.out.println("----------------------更新节点----------------------------");
        Node newNode3 = new Node(3, "吴用1", "智多星1");
        heroLinkedList.update(newNode3);
        Node newNode = new Node(7, "吴用1", "智多星1");
        heroLinkedList.update(newNode);
        heroLinkedList.list();

        // 删除节点
        System.out.println("----------------------删除节点----------------------------");
        heroLinkedList.removeByHeroNo(1);
        heroLinkedList.list();

        // 获取链表中有效节点个数(去除了头节点)
        System.out.println("链表中有效节点个数:" + heroLinkedList.size());

        System.out.println("----------------------获取链表中倒数第n个元素----------------------------");
        // 获取链表中倒数第n个元素
        Node reverseNode = heroLinkedList.getReverseNode(1);
        System.out.println(reverseNode);

        // 反转链表
        System.out.println("----------------------反转链表前----------------------------");
        // 反转前
        heroLinkedList.list();
        System.out.println("----------------------反转链表后----------------------------");
        heroLinkedList.reverse();
        // 反转后
        heroLinkedList.list();

        // 反转链表:不带返回值
        System.out.println("---------------------------------------------------");
        heroLinkedList.reverseList();

        // 反转链表:带返回值
        System.out.println("-------------------反转链表:带返回值--------------------------------");
        HeroLinkedList reverseHasResult = heroLinkedList.reverseHasResult();
        reverseHasResult.list();

        // 合并链表
        System.out.println("------------------------合并链表---------------------------");
        // 上面的节点的next已经不为空了，不可以继续使用了
        // Node songj = new Node(1, "宋江", "及时雨");
        // Node chaog = new Node(2, "晁盖", "托塔天王");
        // HeroLinkedList l1 = new HeroLinkedList();
        // l1.add(songj);
        // HeroLinkedList l2 = new HeroLinkedList();
        // l2.add(chaog);
        // HeroLinkedList merge = l1.merge(l1, l2);
        // System.out.println(l1.size());
        // merge.list();

    }
}
