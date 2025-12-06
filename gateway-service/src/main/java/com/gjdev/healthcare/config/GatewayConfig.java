package com.gjdev.healthcare.config;


import com.gjdev.healthcare.config.properties.GatewayProperties;
import com.gjdev.healthcare.filter.AuthenticationFilter;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Configuration
public class GatewayConfig {

    private final AuthenticationFilter authenticationFilter;
    private final GatewayProperties gatewayProperties;

    public GatewayConfig(AuthenticationFilter authenticationFilter,
                         GatewayProperties gatewayProperties) {
        this.authenticationFilter = authenticationFilter;
        this.gatewayProperties = gatewayProperties;
    }
    @Bean
    @RefreshScope
    public RouteLocator dynamicRoutes(RouteLocatorBuilder builder) {
        RouteLocatorBuilder.Builder routes = builder.routes();

        Map<String, GatewayProperties.ServiceEntry> services = gatewayProperties.getServices();

        services.forEach((serviceName, cfg) -> {
            if(cfg.getRouter()==null){
                return;
            }
            routes.route(serviceName, r -> r.path(cfg.getRouter().getPath())
                    .filters(f -> {
                        f.circuitBreaker(c -> c
                                .setName(serviceName + "CircuitBreaker")
                                .setFallbackUri("forward:" + cfg.getRouter().getFallbackUri())
                        );
                        f.filter(new RemoveDuplicateHeadersFilter());
                        f.filter((exchange, chain) -> {
                            if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS) {
                                exchange.getResponse().setStatusCode(HttpStatus.OK);
                                return exchange.getResponse().setComplete();
                            }
                            return chain.filter(exchange);
                        });
                        return f;
                    })
                    .uri(cfg.getRouter().getUri())
            );
        });
        return routes.build();
    }
}
