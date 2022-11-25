package io.github.kylinhunter.study.algorithm.sort;

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

import io.github.kylinhunter.study.algorithm.sort.imp.AbstractSortTest;

import io.github.kylinhunter.commons.component.CF;
import io.github.kylinhunter.commons.io.file.UserDirUtils;

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
public class BenchmarkSort extends AbstractSortTest {

    @Benchmark
    public void sortBubble() {
        this.sortBeanchmark(CF.get(SortType.Bubble.clazz));
    }

    @Benchmark
    public void sortInsertion() {
        this.sortBeanchmark(CF.get(SortType.Insertion.clazz));
    }

    @Benchmark
    public void sortMerge() {
        this.sortBeanchmark(CF.get(SortType.Merge.clazz));
    }

    @Benchmark
    public void sortChoice() {
        this.sortBeanchmark(CF.get(SortType.Choice.clazz));
    }

    @Benchmark
    public void sortRadix() {
        this.sortBeanchmark(CF.get(SortType.Radix.clazz));
    }

    @Benchmark
    public void sortCounter() {
        this.sortBeanchmark(CF.get(SortType.Counter.clazz));
    }

    @Benchmark
    public void sortHeap() {
        this.sortBeanchmark(CF.get(SortType.Heap.clazz));
    }

    @Benchmark
    public void sortShell() {
        this.sortBeanchmark(CF.get(SortType.Shell.clazz));
    }

    @Benchmark
    public void sortQuickSort() {
        this.sortBeanchmark(CF.get(SortType.QuickSort.clazz));
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