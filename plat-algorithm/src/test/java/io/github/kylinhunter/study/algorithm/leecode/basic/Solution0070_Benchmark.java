package io.github.kylinhunter.study.algorithm.leecode.basic;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import io.github.kylinhunter.commons.io.file.UserDirUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-25 17:27
 **/
@BenchmarkMode(Mode.AverageTime)
// 配置预热次数，默认是每次运行1秒，运行10次，这里设置为3次
@Warmup(iterations = 0, time = 100, timeUnit = TimeUnit.MILLISECONDS)
// 本例是一次运行4秒，总共运行3次，在性能对比时候，采用默认1秒即可
//@Measurement(iterations = 3, time = 4)
@Measurement(iterations = 2,time = 1)
// 配置同时起多少个线程执行
@Threads(1)
//代表启动多个单独的进程分别测试每个方法，这里指定为每个方法启动一个进程
@Fork(1)
// 定义类实例的生命周期，Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能
@State(value = Scope.Benchmark)
// 统计结果的时间单元
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class Solution0070_Benchmark {

    private final int n = 30;

    @Benchmark
    public void climbStairs1() {

        new Solution0070_ClimbStairs1().climbStairs(n);
    }

    @Benchmark
    public void climbStairs2() {

        new Solution0070_ClimbStairs2().climbStairs(n);
    }

    @Benchmark
    public void climbStairs3() {

        new Solution0070_ClimbStairs3().climbStairs(n);
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(Solution0070_Benchmark.class.getSimpleName())
                //                .output(UserDirUtils.getTmpFile("sort_jmh_output.json").getAbsolutePath())
                .result(UserDirUtils.getTmpFile(Solution0070_Benchmark.class.getSimpleName() + ".json")
                        .getAbsolutePath())
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();

    }
}