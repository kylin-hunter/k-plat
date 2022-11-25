package io.github.kylinhunter.study.algorithm.leecode.linklist;

import java.util.HashSet;
import java.util.Set;

/**
 * 题目 ：环形链表
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos
 * 不作为参数进行传递。仅仅是为了标识链表的实际情况
 * <p>
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 * <p>
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * @author BiJi'an
 * @description
 * @date 2022-07-25 16:48
 **/
public class Solution0141_hasCycle1 {

    /**
     * @param head head
     * @return boolean
     * @title hasCycle
     * @description hash求解方式，这个求解方式是有一定概率是有问题的，
     * 因为这个求解方式依赖ListNode的hashCode和equals方法，这是不能依赖的。
     * @author BiJi'an
     * @date 2022-07-31 23:38
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        Set<ListNode> tmp = new HashSet<>();
        while (head != null) {
            if (tmp.contains(head)) {
                return true;
            }

            tmp.add(head);

            head = head.next;

        }
        return false;
    }

}
