package com.incarcloud.sys.config;

import com.incarcloud.sys.config.converter.DateConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by bzheng on 2019/4/8.
 * 资源映射路径
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new DateConverter());
    }

}
