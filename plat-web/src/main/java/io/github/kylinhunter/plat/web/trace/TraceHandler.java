package io.github.kylinhunter.plat.web.trace;

/**
 * @author BiJi'an
 * @description 日志追踪
 * @date 2022/01/01
 **/
public interface TraceHandler {
    /**
     * @return io.github.kylinhunter.plat.commons.trace.Trace
     * @throws
     * @title 隐式 从 request中 创建 trace
     * @description
     * @author BiJi'an
     * @date 2022/01/01 2:46 下午
     */
    Trace create();

    /**
     * @return io.github.kylinhunter.plat.commons.trace.Trace
     * @throws
     * @title 获取trace
     * @description
     * @author BiJi'an
     * @date 2021/7/30 11:25 上午
     */
    Trace get();

    /**
     * @return 清空trace
     * @throws
     * @title remove
     * @description
     * @author BiJi'an
     * @date 2022/01/01 2:54 下午
     */
    void remove();
}
