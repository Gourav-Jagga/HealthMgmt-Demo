package com.gjdev.healthcare.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class LoggingFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String requestId = UUID.randomUUID().toString();
        exchange.getAttributes().put("REQUEST_ID", requestId);
        logger.info("[{}] Incoming Request: {} {}",
                requestId,
                exchange.getRequest().getMethod(),
                exchange.getRequest().getURI()
        );
        return chain.filter(exchange)
                .doOnSuccess(done -> {
                    logger.info("[{}] Response Status: {}",
                            requestId,
                            exchange.getResponse().getStatusCode());
                })
                .doOnError(error -> {
                    logger.error("[{}] Error occurred: {}", requestId, error.getMessage(), error);
                })
                .doFinally(signalType -> {
                    logger.debug("[{}] Completed with signal: {}", requestId, signalType);
                });
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
