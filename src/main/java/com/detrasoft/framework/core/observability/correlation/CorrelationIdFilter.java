package com.detrasoft.framework.core.observability.correlation;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Gera/propaga correlationId no início de cada request HTTP e garante limpeza do MDC.
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CorrelationIdFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String incoming = request.getHeader(CorrelationIdConstants.CORRELATION_ID_HEADER);
        String correlationId = CorrelationIdUtil.getOrCreateAndPutInMdc(incoming);

        // útil para o caller enxergar/propagar também (e para troubleshooting)
        response.setHeader(CorrelationIdConstants.CORRELATION_ID_HEADER, correlationId);

        try {
            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(CorrelationIdConstants.MDC_KEY_CORRELATION_ID);
        }
    }
}

