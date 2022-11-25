package io.github.kylinhunter.study.algorithm.leecode.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 用栈实现队列
 * 请你仅使用两个栈实现先入先出队列。
 * 队列应当支持一般队列支持的所有操作（push、pop、peek、empty）：
 * <p>
 * 方法概述:
 * 1、入栈到 inStack
 * 2、出栈的时候，inStack 弹出，压入outStack，此时，后缀的顺序就是先进先出
 *
 * @author BiJi'an
 * @description
 * @date 2022-08-01 23:46
 **/
public class Solution0232_MyQueue {
    Deque<Integer> inStack;
    Deque<Integer> outStack;

    public Solution0232_MyQueue() {
        inStack = new ArrayDeque<Integer>();
        outStack = new ArrayDeque<Integer>();

    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.pop();

    }

    public int peek() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}
