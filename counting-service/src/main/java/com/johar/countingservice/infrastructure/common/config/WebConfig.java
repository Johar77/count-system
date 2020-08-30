package com.johar.countingservice.infrastructure.common.config;

import com.johar.countingservice.infrastructure.common.converter.StringToEventTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: WebConfig
 * @Description: TODO
 * @Author: Johar
 * @Date: 2020-08-30 17:09
 * @Since: 1.0.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToEventTypeConverter());
    }
}
