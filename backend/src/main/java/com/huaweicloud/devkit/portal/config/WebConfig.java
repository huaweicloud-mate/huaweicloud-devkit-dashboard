package com.huaweicloud.devkit.portal.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final String ingestToken;

    public WebConfig(@Value("${app.sync.ingest-token:hcdevkit-portal-ingest-token-v1}") String ingestToken) {
        this.ingestToken = ingestToken;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(ingestToken))
                .addPathPatterns("/rest/developer/server/hcdevkitportal/v1/telemetry")
                .addPathPatterns("/rest/developer/server/hcdevkitportal/v1/scenarios/**")
                .addPathPatterns("/rest/developer/server/hcdevkitportal/v1/vod/**")
                .excludePathPatterns("/rest/developer/server/hcdevkitportal/v1/metrics/**");
    }
}
