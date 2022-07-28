package com.kylinhunter.plat.algorithm.study.leecode.linklist.common;

import com.kylinhunter.plat.algorithm.study.leecode.linklist.ListNode;
import com.kylinhunter.plat.commons.exception.inner.ParamException;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-26 16:41
 **/
public class ListOperator {

    /**
     * @param arr arr
     * @return com.kylinhunter.plat.algorithm.study.leecode.linklist.ListNode
     * @title 跟进数组初始化链表
     * @description
     * @author BiJi'an
     * @date 2022-07-26 17:26
     */
    public static ListNode init(int[] arr) {
        if (arr != null && arr.length > 0) {
            ListNode rootNode = new ListNode(arr[0]);
            ListNode preNode = rootNode;
            for (int i = 1; i < arr.length; i++) {
                preNode.next = new ListNode(arr[i]);
                preNode = preNode.next;
            }
            return rootNode;

        } else {
            throw new ParamException("arr is null or empty");
        }

    }

    /**
     * @param l l
     * @return void
     * @title 打印链表
     * @description
     * @author BiJi'an
     * @date 2022-07-26 17:27
     */
    public static void print(ListNode l) {
        while (l != null) {
            System.out.print(l.val + " ");
            l = l.next;
        }
        System.out.println();
    }

    public static boolean equals(ListNode l, ListNode r) {
        while (l != null && r != null) {
            if (l.val != r.val) {
                return false;
            } else {
                l = l.next;
                r = r.next;
            }

        }

        if (l == null && r == null) {
            return true;
        }

        return false;
    }

}
