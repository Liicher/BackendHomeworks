package edu.hw10.task2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheProxy implements InvocationHandler {
    private final Object target;
    private final String cacheDirectory;
    private final Map<String, Long> cache;

    private CacheProxy(Object target, String cacheDirectory) {
        this.target = target;
        this.cacheDirectory = cacheDirectory;
        this.cache = new ConcurrentHashMap<>();
    }

    public static <T> T create(T target, Class<T> interfaceClass, String cacheDirectory) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(),
            new Class<?>[] {interfaceClass}, new CacheProxy(target, cacheDirectory)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Cache cacheAnnotation = method.getAnnotation(Cache.class);
        if (cacheAnnotation.persist()) {
            String cacheKey = generateCacheKey(method.getName(), args);
            if (cache.containsKey(cacheKey)) {
                return cache.get(cacheKey);
            }

            Object response = method.invoke(target, args);
            cache.put(cacheKey, (long) response);
            save();
            return response;

        } else {
            return method.invoke(target, args);
        }
    }

    private void save() throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(new File(cacheDirectory)));
        printWriter.print(cache);
        printWriter.print("\n");
        printWriter.close();
    }

    private String generateCacheKey(String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append(methodName);
        sb.append("_");
        for (Object arg : args) {
            sb.append(arg.toString());
            sb.append("_");
        }
        return sb.toString();
    }
}

