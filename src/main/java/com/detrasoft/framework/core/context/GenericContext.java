package com.detrasoft.framework.core.context;

import java.util.HashMap;
import java.util.Map;

public class GenericContext {
    private static final ThreadLocal<Map<String, String>> contexts = new ThreadLocal<>();

    public static String getContexts(String key) {
        try {
            var context = contexts.get();
            if (context == null) {
                context = new HashMap<>();
                contexts.set(context);
            }
            return contexts.get().get(key);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Descarta o contexto da thread atual. Threads de servlet sao reaproveitadas:
     * sem isto a proxima requisicao (inclusive anonima) herdava detrasoftId/userId
     * do usuario anterior.
     */
    public static void clear() {
        contexts.remove();
    }

    public static void setContexts(String key, String value) {
        var context = contexts.get();
        if (context == null) {
            context = new HashMap<>();
        }
        context.put(key, value);
        contexts.set(context);
    }
}
