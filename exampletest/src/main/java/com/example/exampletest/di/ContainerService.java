package com.example.exampletest.di;

import java.lang.reflect.InvocationTargetException;

public class ContainerService {

    public static <T> T getObject(Class<T> classType) {
        return createInstance(classType);
    }

    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor(null).newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
