package com.kylinhunter.plat.algorithm.study.leecode.queue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * <p>
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * <p>
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * <p>
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像3a或2[4]的输入。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 * <p>
 * 示例 3：
 * <p>
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 * <p>
 * 示例 4：
 * <p>
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 * @author BiJi'an
 * @description
 * @date 2022-08-01 23:46
 **/
public class Solution0394_decodeString2 {

    public String decodeString(String s) {

        Deque<String> stack = new ArrayDeque<>();
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        StringBuilder digit = new StringBuilder();

        for (char c : chars) {
            if (c == ']') {
                sb.setLength(0);
                digit.setLength(0);
                String pop = stack.pop();
                while (!pop.equals("[")) {
                    sb.insert(0, pop);
                    pop = stack.pop();
                }

                while (stack.peek() != null && Character.isDigit(stack.peek().charAt(0))) {
                    digit.append(stack.pop());
                }

                int len = Integer.valueOf(digit.reverse().toString());
                String tmpS = sb.toString();
                sb.setLength(0);
                for (int i = 0; i < len; i++) {
                    sb.append(tmpS);
                }

                String dist = sb.toString();
                stack.push(dist);

            } else if (c == '[') {
                stack.push(String.valueOf(c));
            } else {
                stack.push(String.valueOf(c));

            }
        }

        sb.setLength(0);

        while (!stack.isEmpty()) {
            sb.append(stack.removeLast());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution0394_decodeString2 decodeString = new Solution0394_decodeString2();

        System.out.println(decodeString.decodeString("10[ab]"));
    }
}
