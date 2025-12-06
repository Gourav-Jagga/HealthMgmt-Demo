package com.gjdev.healthcare.config;

import com.gjdev.healthcare.config.properties.GatewayGlobalCorsProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.List;
import java.util.Map;

@Configuration
public class RefreshableGatewayCorsConfig {

    @Autowired
    private GatewayGlobalCorsProperties corsProperties;

    @Bean
    @RefreshScope
    public CorsWebFilter corsWebFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        Map<String, GatewayGlobalCorsProperties.CorsEntry> cfgMap = corsProperties.getCorsConfigurations();
        cfgMap.forEach((patternKey, entry) -> {
            String pattern = normalizePattern(patternKey);
            CorsConfiguration cfg = new CorsConfiguration();
            List<String> origins = entry.getAllowedOrigins();
            if (origins != null && !origins.isEmpty()) {
                origins.forEach(cfg::addAllowedOrigin);
            }
            List<String> methods = entry.getAllowedMethods();
            if (methods != null && !methods.isEmpty()) {
                methods.forEach(cfg::addAllowedMethod);
            } else {
                cfg.addAllowedMethod("*");
            }
            Object allowedHeaders = entry.getAllowedHeaders();
            if (allowedHeaders instanceof List) {
                ((List<?>) allowedHeaders).forEach(h -> cfg.addAllowedHeader(h.toString()));
            } else if (allowedHeaders instanceof String) {
                cfg.addAllowedHeader(allowedHeaders.toString());
            } else {
                cfg.addAllowedHeader("*");
            }
            Boolean allowCreds = entry.getAllowCredentials();
            cfg.setAllowCredentials(Boolean.TRUE.equals(allowCreds));

            source.registerCorsConfiguration(pattern, cfg);
        });
        return new CorsWebFilter(source);
    }
    private String normalizePattern(String key) {

        if (key == null) return "/**";
        String k = key.trim();
        if ((k.startsWith("'") && k.endsWith("'")) || (k.startsWith("\"") && k.endsWith("\""))) {
            k = k.substring(1, k.length() - 1);
        }
        if (k.startsWith("[") && k.endsWith("]")) {
            k = k.substring(1, k.length() - 1);
        }
        return k;
    }
}
