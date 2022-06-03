package com.kylinhunter.plat.commons.aviator;

import java.util.List;
import java.util.Map;

import org.assertj.core.util.Lists;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorObject;
import com.googlecode.aviator.runtime.type.AviatorString;

import jodd.util.CharUtil;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-01 02:10
 **/
public class TestAviator {

    public static void main(String[] args) {
        //注册函数
        AviatorEvaluator.addFunction(new MustFunction());
        AviatorEvaluator.addFunction(new ShouldFunction());
        AviatorEvaluator.addFunction(new NotFunction());
        AviatorEvaluator.addFunction(new ExactFunction());
        AviatorEvaluator.addFunction(new AllFunction());

        System.out.println(AviatorEvaluator.execute("must(\"测试1\", \"测试2\")"));
        System.out.println(AviatorEvaluator.execute("should(\"测试3\", \"测试4\")"));

        String text = "中国 生产  +(无糖  无菌) (美国 德国) \"草莓很好吃\"  -(进口)";
        init(text);
        Object execute = AviatorEvaluator.execute(
                "all( must(\"测试1\", \"测试2\") "
                        + ",  term(\"测试3\", \"测试4\") "
                        + ",  should(\"测试5\", \"测试6\") "
                        + ",  not(\"测试7\", \"测试8\") "
                        + ")");

        System.out.println(execute);

    }

    public static String init(String text) {
        List<String> result = Lists.newArrayList();
        char[] chars = text.toCharArray();

        int index = 0;
        StringBuilder tmp = new StringBuilder();
        int parenthesesMode = 0;

        while (index < chars.length) {

            char c = chars[index];

            if (c == '(') {
                parenthesesMode = 1;
                tmp.append(c);
            } else if (c == ')') {
                parenthesesMode = 3;
                tmp.append(c);
            } else {

                if (!CharUtil.isWhitespace(c)) {
                    tmp.append(c);

                } else {
                    if (parenthesesMode == 1) {
                        tmp.append(c);

                    } else {

                        if (tmp.length() != 0) {
                            result.add(tmp.toString().trim());
                        }
                        tmp.setLength(0);
                    }

                }
            }

            index++;
        }
        if (tmp.length() != 0) {
            result.add(tmp.toString().trim());
        }

        for (int i = 0; i < result.size(); i++) {
            String s = result.get(i);
            if (s.startsWith("(")) {
                result.set(i, "should" + s);
            } else if (s.startsWith("-")) {
                result.set(i, "not" + s.substring(1));

            } else if (s.startsWith("+")) {
                result.set(i, "must" + s.substring(1));

            } else if (s.startsWith("\"")) {
                result.set(i, "term(" + s.substring(1, s.length() - 1) + ")");

            }

        }

        System.out.println(text);

        result.forEach(System.out::println);

        return text;
    }
}

class AllFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env,
                              AviatorObject arg1, AviatorObject arg2,
                              AviatorObject arg3, AviatorObject arg4) {
        String left = FunctionUtils.getStringValue(arg1, env);
        String right = FunctionUtils.getStringValue(arg2, env);
        List<String> a = Lists.newArrayList();
        a.add(left);
        a.add(right);
        a.add(FunctionUtils.getStringValue(arg3, env));
        a.add(FunctionUtils.getStringValue(arg4, env));

        return FunctionUtils.wrapReturn(a);

    }

    public String getName() {
        return "all";
    }

}

class MustFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env,
                              AviatorObject arg1, AviatorObject arg2) {
        String left = FunctionUtils.getStringValue(arg1, env);
        String right = FunctionUtils.getStringValue(arg2, env);
        return new AviatorString(getName() + left + right);
    }

    public String getName() {
        return "must";
    }

}

class ExactFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env,
                              AviatorObject arg1, AviatorObject arg2) {
        String left = FunctionUtils.getStringValue(arg1, env);
        String right = FunctionUtils.getStringValue(arg2, env);
        return new AviatorString(getName() + left + right);
    }

    public String getName() {
        return "term";
    }

}

class NotFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env,
                              AviatorObject arg1, AviatorObject arg2) {
        String left = FunctionUtils.getStringValue(arg1, env);
        String right = FunctionUtils.getStringValue(arg2, env);
        return new AviatorString(getName() + left + right);
    }

    public String getName() {
        return "not";
    }

}

class ShouldFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env,
                              AviatorObject arg1, AviatorObject arg2) {
        String left = FunctionUtils.getStringValue(arg1, env);
        String right = FunctionUtils.getStringValue(arg2, env);
        return new AviatorString(getName() + left + right);
    }

    public String getName() {
        return "should";
    }

}
