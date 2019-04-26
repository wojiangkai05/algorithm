package com.itnao.LinearStructure.Stack;

/**
 *  堆栈的实现接口
 *  1.一个栈容器要求提供入栈操作，出栈操作，获取栈大小和判断栈是否为空操作。
 *  2.抽象数据类型可定义为 压栈操作、出栈操作、
 */
public interface Stack<Item> {

    // 判断栈是不是空的
    public boolean isEmpty();

    // 栈的大小
    public int size();

    // 压栈操作
    public boolean push(Item item);

    // 弹栈操作
    public Item pop();

    // 判断栈是否为空
    public boolean isFull();
}
