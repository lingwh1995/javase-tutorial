package org.bluebridge.tree.bstree.bstree_a;

/**
 * 二叉排序树测试
 *
 * @author lingwh
 * @date 2026/4/21 19:02
 */
public class BinarySortTreeTest {

    public static void main(String[] args) {
        int[] arr = { 7, 3, 10, 12, 5, 1, 9 };
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        // 中序遍历
        binarySortTree.infixOrder();

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        // 删除叶子节点
        binarySortTree.delNode(12);
        // 删除只有一棵子树的叶子节点
        binarySortTree.delNode(1);
        // 删除有两棵子树的叶子节点
        binarySortTree.delNode(7);
        // 测试删除最后一个节点
        binarySortTree.delNode(10);
        binarySortTree.infixOrder();
    }
}
