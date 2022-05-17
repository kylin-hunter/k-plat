package com.kylinhunter.plat.commons.io.file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.commons.io.ResourceHelper;

class FileUtilTest {

    @Test
    void process() {
        String path = "classpath:/test/test.txt";
        File distFile = ResourceHelper.getFile(path);
        AtomicInteger lines = new AtomicInteger(0);
        FileUtil.process(distFile, "UTF-8", (line -> lines.incrementAndGet()));
        assertEquals(1, lines.get());
        FileStatLinesProcessor fileStatLinesProcessor = new FileStatLinesProcessor();
        FileUtil.process(distFile, "UTF-8", fileStatLinesProcessor);
        assertEquals(1, fileStatLinesProcessor.getResult().getLineNum());

    }

}