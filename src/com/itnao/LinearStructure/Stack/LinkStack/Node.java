package com.itnao.LinearStructure.Stack.LinkStack;

/**
 *    链表的NODE节点
 *
 *
 */
public class Node<Item> {

    // 数据
    private Item item;
    // 指向链表下一个节点的指针
    private Node node;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }
}
