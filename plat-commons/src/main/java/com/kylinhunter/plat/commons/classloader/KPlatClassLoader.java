package com.kylinhunter.plat.commons.classloader;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 20:10
 **/
@Slf4j
public class KPlatClassLoader extends URLClassLoader {
    public KPlatClassLoader(URL[] urls) {
        super(urls);
    }

    public void addPath(Path path) throws Exception {

        Method add = URLClassLoader.class.getDeclaredMethod("addURL", new Class[] {URL.class});
        add.setAccessible(true);
        add.invoke(this, path.toUri().toURL());


        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        add.invoke(classLoader, path.toUri().toURL());

        log.info("add path ==>" + path);

    }

}
