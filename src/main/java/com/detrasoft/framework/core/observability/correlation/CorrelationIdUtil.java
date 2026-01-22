package com.detrasoft.framework.core.observability.correlation;

import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public final class CorrelationIdUtil {

    private CorrelationIdUtil() {
    }

    public static String getFromMdc() {
        return MDC.get(CorrelationIdConstants.MDC_KEY_CORRELATION_ID);
    }

    public static String getOrCreateAndPutInMdc(String candidate) {
        String correlationId = candidate;
        if (!StringUtils.hasText(correlationId)) {
            correlationId = getFromMdc();
        }
        if (!StringUtils.hasText(correlationId)) {
            correlationId = generate();
        }
        MDC.put(CorrelationIdConstants.MDC_KEY_CORRELATION_ID, correlationId);
        return correlationId;
    }

    public static String generate() {
        return UUID.randomUUID().toString();
    }
}

