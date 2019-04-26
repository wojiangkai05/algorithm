package com.itnao.LinearStructure.Stack.LinkStack;

import com.itnao.LinearStructure.Stack.Stack;


/**
 *   用单向链表来实现堆栈的数据结构
 *   1.只能在链表的头部进行插入或者删除的操作，不能再链表的尾部，因为链表的数据结构是当前节点指向下一个节点，
 *   但是下一个节点无法指向上一个节点，所以如果用尾部做堆栈的底会造成数据的丢失。
 */
public class LinkStack<Item> implements Stack<Item> {

    // 链表的头节点
    private Node headNode;
    // 栈中元素数量
    private int N;

    public LinkStack() {
        this.headNode = new Node();
    }


    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean push(Item item) {
        // 创建新的节点
        Node pushNode = new Node();
        // 将数据插入到新创建的节点当中
        pushNode.setItem(item);
        // 将链表头部的next指针赋值给新的节点
        pushNode.setNode(headNode.getNode());
        // 将链表头的next指针替换成新节点
        headNode.setNode(pushNode);
        N++;
        return true;
    }


    @Override
    public Item pop() {
        return null;
    }

    public Node popNode(){
        if (isFull()){
            System.out.println("当前堆栈中没有数据");
            return null;
        }
        //取出头节点的下一个节点，这个节点是最后加入到堆栈当中的节点
        Node oldNode = headNode.getNode();
        // 将oldNode节点的下一个节点存放到头节点当中
        headNode.setNode(oldNode.getNode());
        N--;
        return oldNode;
    }

    @Override
    public boolean isFull() {
        return headNode.getNode() == null;
    }
}
