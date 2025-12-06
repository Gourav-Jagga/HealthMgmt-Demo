package com.gjdev.healthcare.config.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("customGatewayProperties")
@RefreshScope
@ConfigurationProperties(prefix = "gateway")
public class GatewayProperties {

    private Map<String, ServiceEntry> services = new HashMap<>();
    private List<String> openApiEndpoints;

    public Map<String, ServiceEntry> getServices() {
        return services;
    }

    public void setServices(Map<String, ServiceEntry> services) {
        this.services = services;
    }

    public List<String> getOpenApiEndpoints() {
        return openApiEndpoints;
    }

    public void setOpenApiEndpoints(List<String> openApiEndpoints) {
        this.openApiEndpoints = openApiEndpoints;
    }

    public static class ServiceEntry {
        // NOTE: binding exact key "circuitBreakerCofig" as in your YAML
        private CircuitBreakerCofig circuitBreakerCofig = new CircuitBreakerCofig();
        private RouterConfig router;

        public CircuitBreakerCofig getCircuitBreakerCofig() {
            return circuitBreakerCofig;
        }

        public void setCircuitBreakerCofig(CircuitBreakerCofig circuitBreakerCofig) {
            this.circuitBreakerCofig = circuitBreakerCofig;
        }

        public RouterConfig getRouter() {
            return router;
        }

        public void setRouter(RouterConfig router) {
            this.router = router;
        }
    }

    public static class CircuitBreakerCofig {
        private long timeoutSeconds = 2;
        private float failureRateThreshold = 50f;
        private long waitDurationOpenSeconds = 1;
        private int slidingWindowSize = 20;

        public long getTimeoutSeconds() { return timeoutSeconds; }
        public void setTimeoutSeconds(long timeoutSeconds) { this.timeoutSeconds = timeoutSeconds; }

        public float getFailureRateThreshold() { return failureRateThreshold; }
        public void setFailureRateThreshold(float failureRateThreshold) { this.failureRateThreshold = failureRateThreshold; }

        public long getWaitDurationOpenSeconds() { return waitDurationOpenSeconds; }
        public void setWaitDurationOpenSeconds(long waitDurationOpenSeconds) { this.waitDurationOpenSeconds = waitDurationOpenSeconds; }

        public int getSlidingWindowSize() { return slidingWindowSize; }
        public void setSlidingWindowSize(int slidingWindowSize) { this.slidingWindowSize = slidingWindowSize; }
    }

    public static class RouterConfig {
        private String path;
        private String fallbackUri;
        private String uri;
        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }

        public String getFallbackUri() { return fallbackUri; }
        public void setFallbackUri(String fallbackUri) { this.fallbackUri = fallbackUri; }

        public String getUri() { return uri; }
        public void setUri(String uri) { this.uri = uri; }
    }
}

