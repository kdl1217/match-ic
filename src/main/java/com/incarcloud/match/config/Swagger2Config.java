package com.incarcloud.match.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 项目Swagger2配置
 *
 * @author Aaric, created on 2020-02-28T11:46.
 * @version 0.1.0-SNAPSHOT
 */
@EnableSwagger2
@Configuration
public class Swagger2Config extends AbstractSwagger2ConfigSupport {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .globalOperationParameters(globalOperationParameters())
                /*.groupName("v1")*/
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.incarcloud"))
                //.paths(PathSelectors.regex("/hproj/.*"))
                .paths(PathSelectors.any())
                .build();
    }
}
