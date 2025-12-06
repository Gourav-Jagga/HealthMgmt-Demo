package com.gjdev.healthcare.config;
import java.time.Duration;
import java.util.Map;

import com.gjdev.healthcare.config.properties.GatewayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class CircuitBreakerConfiguration {

    @Autowired
    private GatewayProperties gatewayProperties;

    @Bean
    @RefreshScope
    public Customizer<Resilience4JCircuitBreakerFactory> perServiceCustomizer() {
        return factory -> {
            GatewayProperties.ServiceEntry defaultEntry = gatewayProperties.getServices().get("default");
            GatewayProperties.CircuitBreakerCofig defaultCfg =
                    (defaultEntry != null) ? defaultEntry.getCircuitBreakerCofig() : new GatewayProperties.CircuitBreakerCofig();
            factory.configureDefault(id -> buildConfig(id, defaultCfg).build());
            for (Map.Entry<String, GatewayProperties.ServiceEntry> e : gatewayProperties.getServices().entrySet()) {
                String serviceId = e.getKey();
                if ("default".equals(serviceId)) continue;
                GatewayProperties.CircuitBreakerCofig cfg = e.getValue().getCircuitBreakerCofig();
                if (cfg != null) {
                    factory.configure(builder -> buildConfig(serviceId, cfg), serviceId);
                }
            }
        };
    }

    private Resilience4JConfigBuilder buildConfig(String id, GatewayProperties.CircuitBreakerCofig cfg) {
        TimeLimiterConfig tlc = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(cfg.getTimeoutSeconds()))
                .build();

        CircuitBreakerConfig cbc = CircuitBreakerConfig.custom()
                .failureRateThreshold(cfg.getFailureRateThreshold())
                .waitDurationInOpenState(Duration.ofSeconds(cfg.getWaitDurationOpenSeconds()))
                .slidingWindowSize(cfg.getSlidingWindowSize())
                .build();

        return new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(tlc)
                .circuitBreakerConfig(cbc);
    }
}