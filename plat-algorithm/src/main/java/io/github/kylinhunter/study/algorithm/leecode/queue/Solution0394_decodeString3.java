package io.github.kylinhunter.study.algorithm.leecode.queue;

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
public class Solution0394_decodeString3 {

    public String decodeString(String s) {
        return dfs(s, 0)[0];

    }

    private String[] dfs(String s, int i) {
        StringBuilder sb = new StringBuilder();

        int multi = 0;
        while (i < s.length()) {
            if (s.charAt(i) >='0' && s.charAt(i) <= '9') {
                multi = multi * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            } else if (s.charAt(i) == '[') {
                String[] tmp = dfs(s, i + 1);
                i = Integer.valueOf(tmp[0]);
                while (multi > 0) {
                    sb.append(tmp[1]);
                    multi--;
                }
            } else if (s.charAt(i) == ']') {
                return new String[] {String.valueOf(i), sb.toString()};
            } else {
                sb.append(String.valueOf(s.charAt(i)));
            }
            i++;

        }
        return new String[]{sb.toString()};

    }

    public static void main(String[] args) {
        Solution0394_decodeString3 decodeString = new Solution0394_decodeString3();

        System.out.println(decodeString.decodeString("10[ab]"));
    }
}
