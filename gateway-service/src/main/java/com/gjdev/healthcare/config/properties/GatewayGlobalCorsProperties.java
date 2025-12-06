package com.gjdev.healthcare.config.properties;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
@Component
@RefreshScope
@ConfigurationProperties(prefix = "spring.cloud.gateway.globalcors")
public class GatewayGlobalCorsProperties {
    private Map<String, CorsEntry> corsConfigurations = new HashMap<>();

    public Map<String, CorsEntry> getCorsConfigurations() {
        return corsConfigurations;
    }

    public void setCorsConfigurations(Map<String, CorsEntry> corsConfigurations) {
        this.corsConfigurations = corsConfigurations;
    }

    public static class CorsEntry {
        private List<String> allowedOrigins;
        private List<String> allowedMethods;
        private Object allowedHeaders; // can be List or String "*"
        private Boolean allowCredentials;

        public List<String> getAllowedOrigins() {
            return allowedOrigins;
        }
        public void setAllowedOrigins(List<String> allowedOrigins) {
            this.allowedOrigins = allowedOrigins;
        }

        public List<String> getAllowedMethods() {
            return allowedMethods;
        }
        public void setAllowedMethods(List<String> allowedMethods) {
            this.allowedMethods = allowedMethods;
        }

        public Object getAllowedHeaders() {
            return allowedHeaders;
        }
        public void setAllowedHeaders(Object allowedHeaders) {
            this.allowedHeaders = allowedHeaders;
        }

        public Boolean getAllowCredentials() {
            return allowCredentials;
        }
        public void setAllowCredentials(Boolean allowCredentials) {
            this.allowCredentials = allowCredentials;
        }
    }
}
