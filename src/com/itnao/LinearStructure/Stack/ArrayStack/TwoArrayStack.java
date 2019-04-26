package com.itnao.LinearStructure.Stack.ArrayStack;


/**
 *    用一个数组实现两个堆栈的实现方式
 *    1。将堆栈头部当做堆栈1的栈底，将堆栈的尾部作为堆栈2的栈底，堆栈1随着入栈增多增加下标指针位置
 *    ，堆栈2随着入栈的增多递减下标的指针位置，当两个堆栈的指针位置相等的时候证明堆栈是满的。
 *
 */
public class TwoArrayStack<Item>{

    // 默认栈大小为1
    private Item a[];
    // 堆栈1栈顶指针
    private int top1 = -1;
    // 堆栈2栈顶指正
    private int top2;
    // 堆栈大小
    private int maxSize;

    // 初始化栈的大小
    public TwoArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.a = (Item[]) new Object[maxSize];
        this.top2 = maxSize;
    }

    public boolean isEmpty() {
        return top1 == -1 && top2 == maxSize;
    }

    /**
     *  @param tag 标识插入堆栈1还是堆栈2。 1标识堆栈1,2表示堆栈2
     */
    public boolean push(Item item,int tag) {
        if (isFull()){
            System.out.println("当前堆栈已经满了");
            return false;
        }else {
            if (tag ==1){
                a[++top1] = item;
                return true;
            }else if (tag == 2){
                a[--top2] = item;
                return true;
            }else {
                System.out.println("插入堆栈错误，没有此堆栈");
                return false;
            }
        }
    }

    public Item pop(int tag) {
        if (isEmpty()){
            System.out.println("当前堆栈是空的");
            return null;
        }else {
            if (tag == 1){
                Item item = a[top1];
                a[top1] = null;
                top1--;
                return item;
            }else if (tag == 2){
                Item item = a[top2];
                a[top2] = null;
                top2++;
                return item;
            }else {
                System.out.println("堆栈操作错误，请选择正确的堆栈");
                return null;
            }

        }
    }

    public boolean isFull() {
        return top2 - top1 == 1;
    }
}
