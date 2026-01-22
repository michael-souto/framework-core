package com.detrasoft.framework.core.observability.correlation;

/**
 * Centraliza nomes de headers e chaves de MDC para facilitar extração
 * desta funcionalidade para um Spring Boot Starter compartilhado.
 */
public final class CorrelationIdConstants {

    private CorrelationIdConstants() {
    }

    public static final String CORRELATION_ID_HEADER = "X-Correlation-Id";
    public static final String MDC_KEY_CORRELATION_ID = "correlationId";
}

