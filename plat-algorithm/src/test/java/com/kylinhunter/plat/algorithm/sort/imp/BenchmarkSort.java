package com.kylinhunter.plat.algorithm.sort.imp;

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

import com.kylinhunter.plat.algorithm.sort.SortType;
import com.kylinhunter.plat.commons.io.file.UserDirUtils;
import com.kylinhunter.plat.commons.service.EServices;

@BenchmarkMode(Mode.All)
// 配置预热次数，默认是每次运行1秒，运行10次，这里设置为3次
@Warmup(iterations = 1, time = 100, timeUnit = TimeUnit.MILLISECONDS)
// 本例是一次运行4秒，总共运行3次，在性能对比时候，采用默认1秒即可
//@Measurement(iterations = 3, time = 4)
@Measurement(iterations = 1, time = 1)
// 配置同时起多少个线程执行
@Threads(1)
//代表启动多个单独的进程分别测试每个方法，这里指定为每个方法启动一个进程
@Fork(1)
// 定义类实例的生命周期，Scope.Benchmark：所有测试线程共享一个实例，用于测试有状态实例在多线程共享下的性能
@State(value = Scope.Benchmark)
// 统计结果的时间单元
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BenchmarkSort extends AbstractCommonSortTest {

    @Benchmark
    public void sortBubble() {
        this.sortBeanchmark(EServices.get(SortType.Bubble));
    }

    @Benchmark
    public void sortInsertion() {
        this.sortBeanchmark(EServices.get(SortType.Insertion));
    }

    @Benchmark
    public void sortMerge() {
        this.sortBeanchmark(EServices.get(SortType.Merge));
    }

    @Benchmark
    public void sortChoice() {
        this.sortBeanchmark(EServices.get(SortType.Choice));
    }

    @Benchmark
    public void sortRadix() {
        this.sortBeanchmark(EServices.get(SortType.Radix));
    }

    @Benchmark
    public void sortCounter() {
        this.sortBeanchmark(EServices.get(SortType.Counter));
    }

    @Benchmark
    public void sortHeap() {
        this.sortBeanchmark(EServices.get(SortType.Heap));
    }

    @Benchmark
    public void sortQuickSort() {
        this.sortBeanchmark(EServices.get(SortType.QuickSort));
    }

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BenchmarkSort.class.getSimpleName())
                //                .output(UserDirUtils.getTmpFile("sort_jmh_output.json").getAbsolutePath())
                .result(UserDirUtils.getTmpFile("sort_jmh_result.json").getAbsolutePath())
                .resultFormat(ResultFormatType.JSON).build();
        new Runner(opt).run();

    }
}