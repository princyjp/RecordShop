package com.northcoders.RecordShopAPI.DTO;

import java.lang.reflect.Field;

public class ObjectMerger {
    public static <T> T merge(T original, T updates) {
        Field[] fields = original.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(updates);
                if (value != null) {
                    field.set(original, value);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return original;
    }
}
