package io.jpe.starter.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.MappedInterceptor;

@Configuration
public class InterceptorConfig  {
    @Bean
    @Autowired
    public MappedInterceptor mappedInterceptor(HandlerInterceptor executionTimeInterceptor) {
        return new MappedInterceptor(new String[]{"/**"}, executionTimeInterceptor);
    }
}