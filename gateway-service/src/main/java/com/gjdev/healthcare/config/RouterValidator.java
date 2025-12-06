package com.gjdev.healthcare.config;

import com.gjdev.healthcare.config.properties.GatewayProperties;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;

@Component
public class RouterValidator {

    GatewayProperties gatewayProperties;
    RouterValidator(GatewayProperties gatewayProperties){
        this.gatewayProperties = gatewayProperties;
    }
    public Predicate<ServerHttpRequest> isSecured = request ->
            gatewayProperties.getOpenApiEndpoints().stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
}
