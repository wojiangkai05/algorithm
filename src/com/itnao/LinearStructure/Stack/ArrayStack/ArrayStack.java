package com.itnao.LinearStructure.Stack.ArrayStack;


import com.itnao.LinearStructure.Stack.Stack;

/**
 *    用顺序结构实现堆栈的简单原理
 *    1.堆栈的处理逻辑是只有一端可以进行压栈和弹栈操作，并且先入后出的原理[LIFO].
 *    2.数组的实现又叫做顺序结构的堆栈，我们通过数组来实现堆栈的基本应用。
 *    3.数组最大的问题就是无法处理大小，需要给定的时候就指定堆栈的大小，并且我们将数组下标最小的一端当做栈底。
 *
 */
public class ArrayStack<Item> implements Stack<Item>{

    // 默认栈大小为1
    private Item a[];
    // 栈顶指针
    private int top = -1;
    // 堆栈大小
    private int maxSize;

    // 初始化栈的大小
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.a = (Item[]) new Object[maxSize];
    }

    @Override
    public boolean isEmpty() {
           return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public boolean push(Item item) {
        if (isFull()){
            System.out.println("当前堆栈已经满了");
            return false;
        }else {
            a[++top] = item;
            return true;
        }
    }

    @Override
    public Item pop() {
        if (isEmpty()){
            System.out.println("当前堆栈是空的");
            return null;
        }else {
            Item item = a[top];
            a[top] = null;
            top--;
            return item;
        }
    }

    @Override
    public boolean isFull() {
        return top + 1 == maxSize;
    }
}
